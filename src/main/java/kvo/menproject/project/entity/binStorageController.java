package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class binStorageController implements CommandLineRunner {
    //    private final JdbcTemplate jdbcTemplate;
    private libDivisionRepository libDivisionRepo;
    private final binStorageRepository binStorageRepo;
    private docProjectsListRepository docProjectsListRepo;
    private libStepProjectRepository libStepProjectRepo;
    private FileDataRepository fileDataRepo;

    public binStorageController(binStorageRepository binStorageRepo, docProjectsListRepository docProjectsListRepo, FileDataRepository fileDataRepo, libStepProjectRepository libStepProjectRepo, libDivisionRepository libDivisionRepo) {
        this.binStorageRepo = binStorageRepo;
        this.docProjectsListRepo = docProjectsListRepo;
        this.fileDataRepo = fileDataRepo;
        this.libStepProjectRepo = libStepProjectRepo;
        this.libDivisionRepo = libDivisionRepo;
    }

    @Transactional(readOnly = true)
    @GetMapping("/binstorage")
    public String viewHomePage(@RequestParam(required = false, defaultValue = "0") Long idProject, Model model){

        if (idProject != 0) {
            model.addAttribute("project", docProjectsListRepo.findById(idProject).get().getId());
            model.addAttribute("binstorages", binStorageRepo.findAllByNameProject(idProject));
        } else {
            model.addAttribute("binstorages", binStorageRepo.findAll());
        }
        model.addAttribute("fileData", fileDataRepo.findAllByTypeDoc("binStorage"));
        model.addAttribute("projectslists", docProjectsListRepo.findAll());
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());

        return "/binstorage/mainbinstorage";
    }

    @GetMapping(path = "/addbinstorage")
    public String addNewProject(@RequestParam(required = false, defaultValue = "0") Long idProject, Model model) {

        model.addAttribute("idProject", idProject);
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("projectslists", docProjectsListRepo.findAllById(idProject));
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

        model.addAttribute("projectslists", docProjectsListRepo.findAll());
        model.addAttribute("stepprojects", libStepProjectRepo.findAll());
        model.addAttribute("binstorage", binStorage);
        return "/binstorage/showbinstorage";
    }

    @Transactional
    @GetMapping("/deletebinstorage/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        List<FileData> fatd = fileDataRepo.findAllByTypeDocAndIdData("binStorage", id);
        for (FileData fileData : fatd) {
            long idDataDelete = fileData.getId();
            fileDataRepo.deleteById(idDataDelete);
        }
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
