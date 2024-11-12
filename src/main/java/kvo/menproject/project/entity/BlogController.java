package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class BlogController implements CommandLineRunner {
    private final BlogRepository blogRepo;

    public BlogController(BlogRepository blogRepo) {
        this.blogRepo = blogRepo;
    }

    @GetMapping("/blog")
    public String getBlogPage(Model model) {
        model.addAttribute("blogs", blogRepo.findAllByOrderByDcreateDesc());
//        model.addAttribute("title", "Заголовок");
//        model.addAttribute("content", "Это пример параграфа текста для вашего блога.");
        return "blog/blog";
    }

    @PostMapping("/saveBlogPost")
    public String saveBlogPost(@ModelAttribute("blog") Blog blog) {
        // Сохраните заголовок и контент в базе данных

            if (blog.getDcreate() == null) {
                blog.setDcreate(new Date());
            }
            if (blog.getTitle() == null) {
                blog.setTitle("Заголовок блога");
            }
            if(blog.getShow() == null){
                blog.setShow(true);
            }


        blogRepo.saveAndFlush(blog);
        return "redirect:/blog";
    }
    @GetMapping("/deleteblog/{id}")
    public String deleteTBlogId(@PathVariable(value = "id") long id) {
        blogRepo.deleteById(id);
        return "redirect:/blog";
    }
    @Override
    public void run(String... args) throws Exception {

    }
}