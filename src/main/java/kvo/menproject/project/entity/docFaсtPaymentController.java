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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


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
    public String viewHomePage(@RequestParam(name = "idProject", required = false, defaultValue = "0") Long idProject, Model model) {

        List<docFaсtPayment> rows = docFaсtPaymentRepo.findAllByProjectId(idProject);

        model.addAttribute("fileData", fileDataRepo.findAllByTypeDoc("factPayment"));
//        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("idProject", idProject);
        model.addAttribute("projectslists", docProjectsListRepo.findAll());

        model.addAttribute("factpayments", rows);
        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAll());

        return "/factpayment/mainfactpayment";
    }


    @GetMapping(path = "/addfactpayment")
    public String addNewProject(@RequestParam(required = false, defaultValue = "0") Long idProject, Model model) {
        List<docPlanPayProject> allPlanPayProjects = docPlanPayProjectRepo.findAll();
        List<docPlanPayProject> filteredPlanPayProjects = allPlanPayProjects.stream()
                .filter(docPlanPayProject -> docPlanPayProject.getDocProjectsListByPlanProject().getId() == idProject)
                .collect(Collectors.toList());
        if (filteredPlanPayProjects==null) {
            filteredPlanPayProjects.add(new docPlanPayProject());
        }
        model.addAttribute("idProject", idProject);
        model.addAttribute("statuss", Status.values());
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("projectslists", docProjectsListRepo.findById(idProject));
        model.addAttribute("factpayments", new docFaсtPayment());
        model.addAttribute("planpayprojects", filteredPlanPayProjects);

        return "/factpayment/newfactpayment";
    }

    @PostMapping(path = "/savefactpayment")
    public String saveProject(@RequestParam("projectsListByProjectId") Long idProject, @ModelAttribute("newfactpayment") docFaсtPayment list,
                              RedirectAttributes redirectAttributes) {
        if (list.getDataPayDoc() == null) {
            list.setDataPayDoc(new Date());
        }
        System.out.println("Project ID: " + list.getProjectId()); // отладочный вывод
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
        redirectAttributes.addAttribute("idProject", idProject);
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
    public String deleteThroughId(@PathVariable(value = "id") long id,
                                  RedirectAttributes redirectAttributes) {
        List<FileData> fakt = fileDataRepo.findAllByTypeDocAndIdData("factPayment", id);
        for (FileData fileData : fakt) {
            long idDataDelete = fileData.getId();
            fileDataRepo.deleteById(idDataDelete);
        }
        Long idProject = docFaсtPaymentRepo.findById(id).get().getProjectId();
        docFaсtPaymentRepo.deleteById(id);
        redirectAttributes.addAttribute("idProject", idProject);

        return "redirect:/factpayment";

    }

    @PostMapping(path = "/updatefactpayment")
    public String updateProject(@ModelAttribute("showfactpayment") docFaсtPayment list,
                                RedirectAttributes redirectAttributes) {
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
        long idProject = list.getProjectsListByProjectId().getId();
        list.setProjectId(idProject);
        docFaсtPaymentRepo.saveAndFlush(list);
        redirectAttributes.addAttribute("idProject", idProject);
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