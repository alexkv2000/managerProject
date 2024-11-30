package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class libStepProjectController implements CommandLineRunner {
    //    private final JdbcTemplate jdbcTemplate;
    private final libStepProjectRepository libStepProjectRepo;

    public libStepProjectController(libStepProjectRepository libStepProjectRepo) {
//        this.jdbcTemplate = jdbcTemplate;
        this.libStepProjectRepo = libStepProjectRepo;
    }

    @GetMapping("/stepproject")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<libStepProject> rows = libStepProjectRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " step project");
        } else {
            System.out.println("No step project retrieved");
        }
//        model.addAttribute("stepproject", rows);
        model.addAttribute("stepprojects", libStepProjectRepo.findAll(pageable));
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/stepproject/mainstepproject";
    }

//    @GetMapping(path="/")
//    public String HomePage(Model model){
//        return "index";
//    }

    @GetMapping(path = "/addstepproject")
    public String addNewStepProject(Model model) {
        libStepProject stepproject = new libStepProject();
        model.addAttribute("stepproject", stepproject);
        return "/stepproject/newstepproject";
    }

    @PostMapping(path = "/savestepproject")
    public String saveStepProject(@ModelAttribute("stepproject") libStepProject stepproject) {
        if (!stepproject.getNameStepProject().isEmpty() || stepproject.getNameStepProject().trim() != "" || !stepproject.getNameStepProject().isBlank()) {
            libStepProjectRepo.saveAndFlush(stepproject);
        }
        return "redirect:/stepproject";
    }

    @GetMapping(path ="/showstepproject/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        libStepProject stepproject = libStepProjectRepo.getById(id);
        model.addAttribute("stepproject", stepproject);
        return "/stepproject/showstepproject";
    }

    @GetMapping("/deletestepproject/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        libStepProjectRepo.deleteById(id);
        return "redirect:/stepproject";

    }

    @Override
    public void run(String... args) throws Exception {

    }
}
