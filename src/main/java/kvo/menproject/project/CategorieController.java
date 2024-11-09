package kvo.menproject.project;

import org.springframework.boot.CommandLineRunner;
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

    public CategorieController(CategorieRepository categorieRepo) {
        this.categorieRepo = categorieRepo;
    }

    @GetMapping
    public String showForm(Model model) {

        List<Object[]> tt = categorieRepo.getAllByIdSub();
        System.out.println(tt.get(0).toString() + ' ' + tt.get(1).toString());
        model.addAttribute("categories", tt);

        return "index1";

    }


    @GetMapping("/subcategories")
    @ResponseBody
    public List<Categorie> getSubcategories(@RequestParam Long categoryId) {

        return categorieRepo.findAllByIdSub(categoryId);
//        return categorieRepo.findAll();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
