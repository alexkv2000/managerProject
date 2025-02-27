package kvo.menproject.project.entity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class docProjectsListController implements CommandLineRunner {
    //    private final JdbcTemplate jdbcTemplate;
    private final docProjectsListRepository docProjectsListRepo;
    private libDivisionRepository libDivisionRepo;
    private libEmployeeRepository libEmployeeRepo;

    public docProjectsListController(docProjectsListRepository docProjectsListRepo, libDivisionRepository libDivisionRepo, libEmployeeRepository libEmployeeRepo) {
//        this.jdbcTemplate = jdbcTemplate;
        this.docProjectsListRepo = docProjectsListRepo;
        this.libDivisionRepo = libDivisionRepo;
        this.libEmployeeRepo = libEmployeeRepo;
    }

    @GetMapping("/projectlist")
    public String viewHomePage(@RequestParam(required = false, defaultValue = "0") Long idDivision, HttpServletRequest request, Model model) {
//    public String viewHomePage(Model model,
//                               @RequestParam(defaultValue = "0") int page,
//                               @RequestParam(defaultValue = "5") int size) {
//        List<serLibDivision> rows = jdbcTemplate.query("select id, \"nameDivision\", organisation, active from library.\"libDivision\" where active = true order by id", BeanPropertyRowMapper.newInstance(serLibDivision.class));
//        for (serLibDivision q: rows) {
//            System.out.println(q.toString());
//        }
//        Pageable pageable = PageRequest.of(page, size);
//        Page<docProjectsList> rows = docProjectsListRepo.findAllByClosedIsFalseSortedByName(pageable);
//        if (rows != null) {
//            System.out.println("Retrieved " + rows.getContent().size() + " projects");
//        } else {
//            System.out.println("No projects retrieved");
//        }
//        System.out.println("Total elements: " + rows.getTotalElements()); // Вывод количества элементов
//        System.out.println("Content: " + rows.getContent()); // Вывод содержимого
//        if(idDivision>0) {
//            model.addAttribute("idDivisions", docProjectsListRepo.findAllByLibDivisionByIdDivision_Id(idDivision));
//        } else {
//            model.addAttribute("idDivisions", libDivisionRepo.findAll());
//        }
        model.addAttribute("libbivisions", libDivisionRepo.findAll());
        if (idDivision != 0) {
            model.addAttribute("libbivision", libDivisionRepo.findById(idDivision).get().getId());
        }
        model.addAttribute("projectlists", docProjectsListRepo.findAllByLibDivisionByIdDivision_Id(idDivision));
        model.addAttribute("request", request);
//        model.addAttribute("projectlists", docProjectsListRepo.findAllByClosedIsFalse(pageable));
//        model.addAttribute("totalPages", rows.getTotalPages());
//        model.addAttribute("currentPage", page);
        return "/projectlist/mainprojectlist";
    }


    @GetMapping(path = "/addprojectlist")
    public String addNewProject(Model model) {
//        List<libDivision> division = libDivisionRepo.findAllByActiveIsTrue();
//        List<libEmployee> employee = libEmployeeRepo.findAllByActiveIsTrue();

        model.addAttribute("project", new docProjectsList());
        model.addAttribute("divisions", libDivisionRepo.findAllByActiveIsTrue());
        model.addAttribute("employees", libEmployeeRepo.findAllByActiveIsTrue());
        return "/projectlist/newprojectlist";
    }

    @PostMapping(path = "/saveprojectlist")
    public String saveProject(@ModelAttribute("newprojectlist") docProjectsList list) {
        if (list.getDateCreate() == null) {
            list.setDateCreate(new Date());
        }
        if (list.getDateStart() == null) {
            list.setDateStart(new Date());
        }
        if (list.getDateEnd() == null) {
            list.setDateEnd(new Date());
        }
        if (list.getLibDivisionByIdDivision() == null || list.getLibDivisionByIdDivision() == null) {
            System.out.println("error");
            return "redirect:/projectlist";
        }

        if (list.getLibJobEmployeeByOwner() == null || list.getLibJobEmployeeByOwner() == null) {
            System.out.println("error");
            return "redirect:/projectlist";
        }
        System.out.println("Division ID: " + list.getLibDivisionByIdDivision().getId()); // Проверка ID
        System.out.println("Employee ID: " + list.getLibJobEmployeeByOwner().getId()); // Проверка ID
        docProjectsListRepo.saveAndFlush(list);
        return "redirect:/projectlist";
    }

    @GetMapping(path = "/showprojectlist/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        docProjectsList projectlist = docProjectsListRepo.getById(id);
        model.addAttribute("projectlist", projectlist);
        model.addAttribute("divisions", libDivisionRepo.findAllByActiveIsTrue());
        model.addAttribute("employees", libEmployeeRepo.findAllByActiveIsTrue());
        return "/projectlist/showprojectlist";
    }

    @GetMapping("/deleteprojectlist/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        docProjectsListRepo.deleteById(id);
        return "redirect:/projectlist";

    }

    @PostMapping(path = "/updateprojectlist")
    public String updateProject(@ModelAttribute("showprojectlist") docProjectsList list) {
        if (list.getDateCreate() == null) {
            list.setDateCreate(new Date());
        }
        if (list.getDateStart() == null) {
            list.setDateStart(new Date());
        }
        if (list.getDateEnd() == null) {
            list.setDateEnd(new Date());
        }


        System.out.println("Division ID: " + list.getId_division()); // Проверка ID
        System.out.println("Project ID: " + list.getId_owner());
        docProjectsListRepo.saveAndFlush(list);
        return "redirect:/projectlist";
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
