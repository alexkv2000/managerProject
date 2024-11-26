package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public String viewHomePage(@RequestParam(required = false, defaultValue = "0") Long idProject, Model model) {


        List<docProjectsList> projectsLists = docProjectsListRepo.findAll();
        model.addAttribute("projectslists", projectsLists);
        if (idProject != 0) {
            model.addAttribute("project", docProjectsListRepo.findById(idProject).get().getId());
        }

        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAllByDocProjectsListByPlanProject_Id(idProject));

        return "/planpayproject/mainplanpayproject";
    }

    @GetMapping(path = "/addplanpayproject")
    public String addNewProject(Model model) {

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


//        Если сумм NULL запоняем 0.00
        changeNullToZero(list);

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


        changeNullToZero(list);

        docPlanPayProjectRepo.saveAndFlush(list);
        return "redirect:/planpayproject";
    }

    private static void changeNullToZero(docPlanPayProject list) {
        if (list.getOpex() == null) {
            list.setOpex(BigDecimal.valueOf(0));
        }
        if (list.getOpexNds() == null) {
            list.setOpexNds(BigDecimal.valueOf(0));
        }
        if (list.getCapex() == null) {
            list.setCapex(BigDecimal.valueOf(0));
        }
        if (list.getCapexNds() == null) {
            list.setCapexNds(BigDecimal.valueOf(0));
        }
    }

    @Override
    public void run(String... args) throws Exception {
    }
}