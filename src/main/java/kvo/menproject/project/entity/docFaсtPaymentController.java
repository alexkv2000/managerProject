package kvo.menproject.project.entity;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Controller
public class docFaсtPaymentController implements CommandLineRunner {
    private final docFaсtPaymentRepository docFaсtPaymentRepo;
    private docPlanPayProjectRepository docPlanPayProjectRepo;
    private docProjectsListRepository docProjectsListRepo;
    private libStepProjectRepository libStepProjectRepo;
    private FileDataRepository fileDataRepo;

    public docFaсtPaymentController(docFaсtPaymentRepository docFaсtPaymentRepo, docPlanPayProjectRepository docPlanPayProjectRepo, docProjectsListRepository docProjectsListRepo, libStepProjectRepository libStepProjectRepo, FileDataRepository fileDataRepo) {
        this.docFaсtPaymentRepo = docFaсtPaymentRepo;
        this.docPlanPayProjectRepo = docPlanPayProjectRepo;
        this.docProjectsListRepo = docProjectsListRepo;
        this.libStepProjectRepo = libStepProjectRepo;
        this.fileDataRepo = fileDataRepo;
    }

    public enum Status {
        НА_СОГЛАСОВАНИИ, ПЕРЕДАН_НА_ОПЛАТУ, ОПЛАЧЕН, ОТКАЗАНО;
    }
    @Transactional
    @GetMapping("/factpayment")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<docFaсtPayment> rows = docFaсtPaymentRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " fact pay project");
        } else {
            System.out.println("No fact pay project retrieved");
        }
        System.out.println("Total elements: " + rows.getTotalElements()); // Вывод количества элементов
        System.out.println("Content: " + rows.getContent()); // Вывод содержимого
        model.addAttribute("fileData", fileDataRepo.findAllByTypeDoc("factPayment"));
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("projectslists", docProjectsListRepo.findAll());

        model.addAttribute("factpayments", docFaсtPaymentRepo.findAll(pageable));
        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAll());

        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/factpayment/mainfactpayment";
    }


    @GetMapping(path = "/addfactpayment")
    public String addNewProject(Model model) {
        model.addAttribute("statuss", Status.values());
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("projectslists", docProjectsListRepo.findAll());
        model.addAttribute("factpayments", new docFaсtPayment());
        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAll());

        return "/factpayment/newfactpayment";
    }

    @PostMapping(path = "/savefactpayment")
    public String saveProject(@ModelAttribute("newfactpayment") docFaсtPayment list) {
        if (list.getDataPayDoc() == null) {
            list.setDataPayDoc(new Date());
        }
        System.out.println(list.getStatusFact());
        String ее = list.getStatusFact();
        if (list.getPaid()) {
            list.setStatusFact(Status.ОПЛАЧЕН.toString());
        } else if (list.getStatusFact().isBlank()) {
            list.setStatusFact(Status.НА_СОГЛАСОВАНИИ.toString());
        }
//TODO проверка на обязательность
//        if (list.getLibDivisionByProjectId() == null || list.getLibDivisionByProjectId() == null) {
//            System.out.println("error");
//            return "redirect:/planpayproject";
//        }
//        if (list.getStatus() != null) {
//            list.setStatus(list.getStatus().toString());
//        }

//        System.out.println(" ID: " + list.getId()); // Проверка ID
        changeNullToZero(list);
        docFaсtPaymentRepo.saveAndFlush(list);
        return "redirect:/factpayment";
    }

    @GetMapping(path = "/showfactpayment/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        docFaсtPayment factpayment = docFaсtPaymentRepo.getById(id);
        model.addAttribute("statuss", Status.values());
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("projectslists", docProjectsListRepo.findAllByClosedIsFalse());

        model.addAttribute("factpayments", factpayment);
        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAll());
        return "/factpayment/showfactpayment";
    }
    @Transactional
    @GetMapping("/deletefactpayment/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        List<FileData> fakt = fileDataRepo.findAllByTypeDocAndIdData("factPayment", id);
        for (FileData fileData : fakt) {
            long idDataDelete = fileData.getId();
            fileDataRepo.deleteById(idDataDelete);
        }
        docFaсtPaymentRepo.deleteById(id);
        return "redirect:/factpayment";

    }

    @PostMapping(path = "/updatefactpayment")
    public String updateProject(@ModelAttribute("showfactpayment") docFaсtPayment list) {
        if (list.getDataPayDoc() == null) {
            list.setDataPayDoc(new Date());
        }
        System.out.println(" ID: " + list.getStatusFact()); // Проверка ID Division
        if (list.getPaid()) {
            list.setStatusFact(Status.ОПЛАЧЕН.toString());
        }
        if (list.getStatusFact().startsWith("ОПЛАЧЕН")) {
            list.setPaid(true);
        }
        changeNullToZero(list);
        docFaсtPaymentRepo.saveAndFlush(list);
        return "redirect:/factpayment";
    }
    private static void changeNullToZero(docFaсtPayment list) {
        if (list.getSumOpex() == null){
            list.setSumOpex(BigDecimal.valueOf(0));}
        if (list.getSumOpexNds() == null){
            list.setSumOpexNds(BigDecimal.valueOf(0));}
        if (list.getSumCapex() == null){
            list.setSumCapex(BigDecimal.valueOf(0));}
        if (list.getSumCapexNds() == null){
            list.setSumCapexNds(BigDecimal.valueOf(0));}
    }
    @Override
    public void run(String... args) throws Exception {
    }
}