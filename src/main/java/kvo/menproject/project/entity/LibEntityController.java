package kvo.menproject.project.entity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class LibEntityController implements CommandLineRunner {
    private final LibEntityRepository LibEntityRepo;
    public LibEntityController(LibEntityRepository LibEntityRepo) {
        this.LibEntityRepo = LibEntityRepo;
    }

    @GetMapping("/entity")
    public String viewHomePage(HttpServletRequest request, Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<LibEntity> rows = LibEntityRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Получены " + rows.getContent().size() + " юридических лиц.");
        } else {
            System.out.println("Данные по юридическим лицам отсутствуют");
        }
//        model.addAttribute("entity", rows);
        System.out.println("Total elements: " + rows.getTotalElements()); // Вывод количества элементов
        System.out.println("Content: " + rows.getContent()); // Вывод содержимого
        model.addAttribute("entitys", LibEntityRepo.findAll(pageable));
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("request", request);

        return "/entity/mainentity";
    }

//    @GetMapping(path="/")
//    public String HomePage(Model model){
//        return "index";
//    }

    @GetMapping(path = "/addentity")
    public String addNewEntity(Model model) {
        LibEntity entity = new LibEntity();
        model.addAttribute("entity", entity);
        return "/entity/newentity";
    }

    @PostMapping(path = "/saveentity")
    public String saveEntity(@ModelAttribute("entity") LibEntity entity) {
        if (!entity.getNameentity().isEmpty() || entity.getNameentity().trim() != "" || !entity.getNameentity().isBlank()) {
            entity.setActive(true);
            LibEntityRepo.saveAndFlush(entity);
        }
        entity.setActive(entity.getActive());
        return "redirect:/entity";
    }

    @GetMapping(path ="/showentity/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        LibEntity entity = LibEntityRepo.getById(id);
        model.addAttribute("entity", entity);
        return "/entity/showentity";
    }

    @GetMapping("/deleteentity/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        LibEntityRepo.deleteById(id);
        return "redirect:/entity";

    }
    @Override
    public void run(String... args) throws Exception {
    }
}
