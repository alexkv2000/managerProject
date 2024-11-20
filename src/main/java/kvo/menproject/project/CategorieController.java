package kvo.menproject.project;

import kvo.menproject.project.entity.*;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

@RequestMapping("/form")
public class CategorieController implements CommandLineRunner {
    private final CategorieRepository categorieRepo;
    private final docProjectsListRepository docProjectsListRepo;
    private final docPlanPayProjectRepository docPlanPayProjectRepo;
    private libDivisionRepository libDivisionRepo;

    public CategorieController(CategorieRepository categorieRepo,docProjectsListRepository docProjectsListRepo, docPlanPayProjectRepository docPlanPayProjectRepo, libDivisionRepository libDivisionRepo) {
        this.categorieRepo = categorieRepo;
        this.docProjectsListRepo = docProjectsListRepo;
        this.docPlanPayProjectRepo =docPlanPayProjectRepo;
        this.libDivisionRepo = libDivisionRepo;
    }

    @GetMapping
    public String showForm(Model model) {

        List<Object[]> tt = categorieRepo.getAllByIdSub();
//        System.out.println(tt.get(0).toString() + ' ' + tt.get(1).toString());
        model.addAttribute("categories", tt);

        return "index1";

    }


    @GetMapping("/subcategories")
    @ResponseBody
    public List<Categorie> getSubcategories(@RequestParam Long categoryId) {

        return categorieRepo.findAllByIdSub(categoryId);
//        return categorieRepo.findAll();
    }
    @GetMapping("/forms")
    public String getFormSort(Model model) {
//        public String getFormSort(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<docPlanPayProject> rows = docPlanPayProjectRepo.findAll(pageable);
//        if (rows != null) {
//            System.out.println("Retrieved " + rows.getContent().size() + " plan pay project");
//        } else {
//            System.out.println("No plan pay project retrieved");
//        }
//        System.out.println("Total elements: " + rows.getTotalElements()); // Вывод количества элементов
//        System.out.println("Content: " + rows.getContent()); // Вывод содержимого
//        model.addAttribute("divisions", libDivisionRepo.findAll());
//        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAll(pageable));
        model.addAttribute("planpayprojects", docPlanPayProjectRepo.findAll());
//        model.addAttribute("totalPages", rows.getTotalPages());
//        model.addAttribute("currentPage", page);
        return "/indexSort";
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
