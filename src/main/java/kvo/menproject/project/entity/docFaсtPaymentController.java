package kvo.menproject.project.entity;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
public class docFaсtPaymentController implements CommandLineRunner {
    private final docFaсtPaymentRepository docFaсtPaymentRepo;
    private docPlanPayProjectRepository docPlanPayProjectRepo;
    private docProjectsListRepository docProjectsListRepo;
    private libStepProjectRepository libStepProjectRepo;

    public docFaсtPaymentController(docFaсtPaymentRepository docFaсtPaymentRepo, docPlanPayProjectRepository docPlanPayProjectRepo, docProjectsListRepository docProjectsListRepo, libStepProjectRepository libStepProjectRepo) {
        this.docFaсtPaymentRepo = docFaсtPaymentRepo;
        this.docPlanPayProjectRepo = docPlanPayProjectRepo;
        this.docProjectsListRepo = docProjectsListRepo;
        this.libStepProjectRepo = libStepProjectRepo;
    }

    public enum Status {
        НА_СОГЛАСОВАНИИ, ПЕРЕДАН_НА_ОПЛАТУ, ОПЛАЧЕН, ОТКАЗАНО;
    }

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

        System.out.println(" ID: " + list.getId()); // Проверка ID
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

    @GetMapping("/deletefactpayment/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
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
        docFaсtPaymentRepo.saveAndFlush(list);
        return "redirect:/factpayment";
    }

    @Override
    public void run(String... args) throws Exception {
    }
}