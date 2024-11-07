package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class docPlanPayProjectController implements CommandLineRunner {
    //    private final JdbcTemplate jdbcTemplate;
    private final docPlanPayProjectRepository docPlanPayProjectRepo;
    private libDivisionRepository libDivisionRepo;
    private docProjectsListRepository docProjectsListRepo;

    public docPlanPayProjectController(docPlanPayProjectRepository docPlanPayProjectRepo, libDivisionRepository libDivisionRepo, docProjectsListRepository docProjectsListRepo) {
        this.docPlanPayProjectRepo = docPlanPayProjectRepo;
        this.libDivisionRepo = libDivisionRepo;
        this.docProjectsListRepo = docProjectsListRepo;
    }

    @GetMapping("/planpayproject")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<docPlanPayProject> rows = docPlanPayProjectRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " plan pay project");
        } else {
            System.out.println("No plan pay project retrieved");
        }
        System.out.println("Total elements: " + rows.getTotalElements()); // Вывод количества элементов
        System.out.println("Content: " + rows.getContent()); // Вывод содержимого
        model.addAttribute("divisions", libDivisionRepo.findAll());
        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAll(pageable));
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/planpayproject/mainplanpayproject";
    }


    @GetMapping(path = "/addplanpayproject")
    public String addNewProject(Model model) {
//        List<libDivision> division = libDivisionRepo.findAllByActiveIsTrue();
//        List<libEmployee> employee = libEmployeeRepo.findAllByActiveIsTrue();
        model.addAttribute("projectslists", docProjectsListRepo.findAllByClosedIsFalse());
        model.addAttribute("PlanPayProject", new docPlanPayProject());
        model.addAttribute("divisions", libDivisionRepo.findAllByActiveIsTrue());

        return "/planpayproject/newplanpayproject";
    }

    @PostMapping(path = "/saveplanpayproject")
    public String saveProject(@ModelAttribute("newplanpayproject") docPlanPayProject list) {
        if (list.getDataPlaning() == null) {
            list.setDataPlaning(new Date());
        }
        if (list.getPlanYear() == null) {
            list.setPlanYear(new Date());
        }
        if (list.getLibDivisionByProjectId() == null || list.getLibDivisionByProjectId() == null) {
            System.out.println("error");
            return "redirect:/planpayproject";
        }


        System.out.println("Division ID: " + list.getLibDivisionByProjectId().getId()); // Проверка ID
        docPlanPayProjectRepo.saveAndFlush(list);
        return "redirect:/planpayproject";
    }

    @GetMapping(path = "/showplanpayproject/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        docPlanPayProject planpayproject = docPlanPayProjectRepo.getById(id);
        model.addAttribute("projectslists", docProjectsListRepo.findAll());
        model.addAttribute("planpayproject", planpayproject);
        model.addAttribute("divisions", libDivisionRepo.findAllByActiveIsTrue());
        return "/planpayproject/showplanpayproject";
    }

    @GetMapping("/deleteplanpayproject/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        docPlanPayProjectRepo.deleteById(id);
        return "redirect:/planpayproject";

    }
    @PostMapping(path = "/updateplanpayproject")
    public String updateProject(@ModelAttribute("showplanpayproject") docPlanPayProject list) {
        if (list.getDataPlaning() == null) {
            list.setDataPlaning(new Date());
        }
        if (list.getPlanYear() == null) {
            list.setPlanYear(new Date());
        }


        System.out.println("Division ID: " + list.getId()); // Проверка ID Division

        docPlanPayProjectRepo.saveAndFlush(list);
        return "redirect:/planpayproject";
    }
    @Override
    public void run(String... args) throws Exception {
    }
}