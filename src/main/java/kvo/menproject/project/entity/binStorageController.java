package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
@Controller
public class binStorageController implements CommandLineRunner {
    //    private final JdbcTemplate jdbcTemplate;
    private final binStorageRepository binStorageRepo;
    private docProjectsListRepository docProjectsListRepo;
    private libStepProjectRepository libStepProjectRepo;
    private FileDataRepository fileDataRepo;

    public binStorageController(binStorageRepository binStorageRepo, docProjectsListRepository docProjectsListRepo, FileDataRepository fileDataRepo, libStepProjectRepository libStepProjectRepo) {
        this.binStorageRepo = binStorageRepo;
        this.docProjectsListRepo = docProjectsListRepo;
        this.fileDataRepo = fileDataRepo;
        this.libStepProjectRepo=libStepProjectRepo;
    }

    @GetMapping("/binstorage")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<binStorage> rows = binStorageRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " plan pay project");
        } else {
            System.out.println("No plan pay project retrieved");
        }
        System.out.println("Total elements: " + rows.getTotalElements()); // Вывод количества элементов
        System.out.println("Content: " + rows.getContent()); // Вывод содержимого
        model.addAttribute("fileData", fileDataRepo.findAllByTypeDoc("binStorage"));
        model.addAttribute("projectslists", docProjectsListRepo.findAllByClosedIsFalse());
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("binstorages", binStorageRepo.findAll(pageable));
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/binstorage/mainbinstorage";
    }


    @GetMapping(path = "/addbinstorage")
    public String addNewProject(Model model) {
//        List<libDivision> division = libDivisionRepo.findAllByActiveIsTrue();
//        List<libEmployee> employee = libEmployeeRepo.findAllByActiveIsTrue();

        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("projectslists", docProjectsListRepo.findAllByClosedIsFalse());
        model.addAttribute("binstorage", new binStorage());
        return "/binstorage/newbinstorage";
    }

    @PostMapping(path = "/savebinstorage")
    public String saveProject(@ModelAttribute("binstorage") binStorage list) {
        if (list.getDateCreate() == null) {
            list.setDateCreate(new Date());
        }

        list.setNameProject(list.getProjectsListByNameProject().getId());
        list.setStepProject(list.getLibStepProjectByStepProject().getId());
        if (list.getNameProject() == 0 || list.getStepProject() == null) {
            System.out.println("error: Step Project or Name Project not entry");
            return "redirect:/binstorage";
        }


        System.out.println("Step Project required to fill in: " + list.getStepProject()); // Проверка ID
        binStorageRepo.saveAndFlush(list);
        return "redirect:/binstorage";
    }

    @GetMapping(path = "/showbinstorage/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        binStorage binStorage = binStorageRepo.getById(id);
//        model.addAttribute("fileData", fileDataRepo.findAllByTypeDoc("binStorage"));
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("projectslists", docProjectsListRepo.findAllByClosedIsFalse());
        model.addAttribute("binstorage", binStorage);
        return "/binstorage/showbinstorage";
    }

    @GetMapping("/deletebinstorage/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        binStorageRepo.deleteById(id);
        return "redirect:/binstorage";

    }
    @PostMapping(path = "/updatebinstorage")
    public String updateProject(@ModelAttribute("showbinstorage") binStorage list) {
        if (list.getDateCreate() == null) {
            list.setDateCreate(new Date());
        }
//TODO проверить на заполения step_project и name_project


        System.out.println("Name Project required to fill in: " + list.getNameProject()); // Проверка ID Division

        binStorageRepo.saveAndFlush(list);
        return "redirect:/binstorage";
    }
    @Override
    public void run(String... args) throws Exception {
    }
}
