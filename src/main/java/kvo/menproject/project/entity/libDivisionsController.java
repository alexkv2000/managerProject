package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Controller
public class libDivisionsController implements CommandLineRunner {
//    private final JdbcTemplate jdbcTemplate;
    private final libDivisionRepository libDivisionRepo;

    public libDivisionsController(libDivisionRepository libDivisionRepo) {
//        this.jdbcTemplate = jdbcTemplate;
        this.libDivisionRepo = libDivisionRepo;
    }

    @GetMapping("/division")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<libDivision> rows = libDivisionRepo.findAllByActiveIsTrue(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " divisions");
        } else {
            System.out.println("No divisions retrieved");
        }
        model.addAttribute("divisions", libDivisionRepo.findAllByActiveIsTrue(pageable));
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/division/maindivision";
    }

    @GetMapping(path="/")
    public String HomePage(Model model){
        return "index";
    }

    @GetMapping(path = "/adddivision")
    public String addNewDivision(Model model) {
        libDivision division = new libDivision();
        model.addAttribute("division", division);
        return "/division/newdivision";
    }

    @PostMapping(path = "/savedivision")
    public String saveDivision(@ModelAttribute("division") libDivision division) {
        if (!division.getNamedivision().isEmpty() || division.getNamedivision().trim() != "" || !division.getNamedivision().isBlank()) {
            division.setActive(true);
            libDivisionRepo.saveAndFlush(division);
        }
        division.setActive(division.getActive());
        return "redirect:/division";
    }

    @GetMapping(path ="/showDivision/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        libDivision division = libDivisionRepo.getById(id);
        model.addAttribute("division", division);
        return "/division/showDivision";
    }

    @GetMapping("/deleteDivision/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        libDivisionRepo.deleteById(id);
        return "redirect:/division";

    }
    @Override
    public void run(String... args) throws Exception {
    }
}
