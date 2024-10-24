package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibJobPlaceController implements CommandLineRunner {
    private final LibJobPlaceRepository LibJobPlaceRepo;
    private final libEmployeeRepository libEmployeeRepo;
    public LibJobPlaceController(LibJobPlaceRepository LibJobPlaceRepo, libEmployeeRepository libEmployeeRepo) {
        this.LibJobPlaceRepo = LibJobPlaceRepo;
        this.libEmployeeRepo = libEmployeeRepo;
    }

    @GetMapping("/jobplace")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LibJobPlace> rows = LibJobPlaceRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Получены " + rows.getContent().size() + " рабочих мест.");
        } else {
            System.out.println("Данные по рабочим местам сотрудников");
        }
//        model.addAttribute("entity", rows);
        System.out.println("Total elements: " + rows.getTotalElements()); // Вывод количества элементов
        System.out.println("Content: " + rows.getContent()); // Вывод содержимого
        model.addAttribute("employees", libEmployeeRepo.findAll());
        model.addAttribute("jobplaces", LibJobPlaceRepo.findAll(pageable));
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/jobplace/mainjobplace";
    }

//    @GetMapping(path="/")
//    public String HomePage(Model model){
//        return "index";
//    }

    @GetMapping(path = "/addjobplace")
    public String addNewJobPlace(Model model) {
        LibJobPlace jobplace = new LibJobPlace();
        model.addAttribute("employees", libEmployeeRepo.findAll());
        model.addAttribute("jobplace", jobplace);
        return "/jobplace/newjobplace";
    }

    @PostMapping(path = "/savejobplace")
    public String saveJobPlace(@ModelAttribute("jobplace") LibJobPlace jobplace) {
        if (!jobplace.getNumberplace().isEmpty() || jobplace.getNumberplace().trim() != "" || !jobplace.getNumberplace().isBlank()) {
            LibJobPlaceRepo.saveAndFlush(jobplace);
        }
        return "redirect:/jobplace";
    }

    @GetMapping(path ="/showjobplace/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        LibJobPlace jobplace = LibJobPlaceRepo.getById(id);
        model.addAttribute("employees", libEmployeeRepo.findAllByActiveIsTrue());
        model.addAttribute("jobplace", jobplace);
        return "/jobplace/showjobplace";
    }

    @GetMapping("/deletejobplace/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        LibJobPlaceRepo.deleteById(id);
        return "redirect:/jobplace";

    }
    @Override
    public void run(String... args) throws Exception {
    }
}
