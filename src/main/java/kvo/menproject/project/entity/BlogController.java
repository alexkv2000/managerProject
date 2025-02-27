package kvo.menproject.project.entity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BlogController implements CommandLineRunner {
    private final BlogRepository blogRepo;

    public BlogController(BlogRepository blogRepo) {
        this.blogRepo = blogRepo;
    }

    @GetMapping("/blog")
    public String getBlogPage(HttpServletRequest request, Model model) throws ParseException {
        if (blogRepo.findAllByShowIsTrueOrderByDcreateDesc().isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            Date dateFormate = format.parse(String.valueOf(date));

            Blog map = new Blog();
            map.setTitle("Заголовок от " + dateFormate);
            map.setDcreate(date);
            model.addAttribute("blog", map);
            return "blog/newblog";
        }
        model.addAttribute("blogs", blogRepo.findAllByShowIsTrueOrderByDcreateDesc());
        model.addAttribute("request", request);
//        model.addAttribute("title", "Заголовок");
//        model.addAttribute("content", "Это пример параграфа текста для вашего блога.");
        return "blog/blog";
    }
    @GetMapping("/blogall")
    public String getBlogPageAll(HttpServletRequest request, Model model) throws ParseException {
        if (blogRepo.findAll().isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            Date dateFormate = format.parse(String.valueOf(date));

            Blog map = new Blog();
            map.setTitle("Заголовок от " + dateFormate);
            map.setDcreate(date);
            model.addAttribute("blog", map);
//            model.addAttribute("request", request);
            return "blog/newblog";
        }
        model.addAttribute("blogs", blogRepo.findByOrderByIdDesc());
//        model.addAttribute("title", "Заголовок");
//        model.addAttribute("content", "Это пример параграфа текста для вашего блога.");
        model.addAttribute("request", request);
        return "blog/blogall";
    }
    @GetMapping("/newblog")
    public String getNewBlogPage(HttpServletRequest request, Model model) throws ParseException {
        Blog blog = new Blog();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dateFormate = format.format(date);


        blog.setTitle("Заголовок от " + dateFormate);
        blog.setDcreate(date);
        model.addAttribute("blog", blog);
        model.addAttribute("request", request);

        return "blog/newblog";
    }
    @GetMapping(path = "/showblog/{id}")
    public String updateForm(@PathVariable(value = "id") long id, HttpServletRequest request, Model model) {
        Blog blog = blogRepo.getById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("request", request);
        return "/blog/showblog";
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
//        if (blog.getShow() == null) {
            blog.setShow(false);
//        }


        blogRepo.saveAndFlush(blog);
        return "redirect:/blog";
    }
    @PostMapping("/updateBlogPost")
    public String updateBlogPost(@ModelAttribute("showblog") Blog blog) {
        if (blog.getDcreate() == null) {
            blog.setDcreate(new Date());
        }
//        if (blog.getStatusFact().startsWith("ОПЛАЧЕН")) {
//            blog.setPaid(true);
//        }
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