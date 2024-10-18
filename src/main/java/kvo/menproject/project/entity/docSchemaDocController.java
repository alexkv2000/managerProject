package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class docSchemaDocController implements CommandLineRunner {
    public docSchemaDocRepository docSchemaDocRepo;
    public  libDivisionRepository libDivisionRepo;
    public binfileRepository binfileRepo;

    public docSchemaDocController(docSchemaDocRepository docSchemaDocRepo, libDivisionRepository libDivisionRepo, binfileRepository binfileRepo) {
        this.docSchemaDocRepo = docSchemaDocRepo;
        this.libDivisionRepo = libDivisionRepo;
        this.binfileRepo = binfileRepo;
    }

    @GetMapping(path="/schemadoc")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<docSchemaDoc> rows = docSchemaDocRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " Schema Documents");
        } else {
            System.out.println("No schema Document retrieved");
        }

        model.addAttribute("schemadocs", rows);
        model.addAttribute("libdivisions", libDivisionRepo.findAll());
        model.addAttribute("binfiles", binfileRepo.findAll());

        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/schemadoc/mainschemadoc";
    }

    @GetMapping(path = "/addschemadoc")
    public String addNewDivision(Model model) {
        docSchemaDoc docSchemaDoc = new docSchemaDoc();
        model.addAttribute("libdivisions", libDivisionRepo.findAll());
        model.addAttribute("schemadoc", docSchemaDoc);
        return "/schemadoc/newschemadoc";
    }

    @PostMapping(path = "/saveschemadoc")
    public String saveDivision(@ModelAttribute("schemadoc") docSchemaDoc schemadoc) {
        if (!schemadoc.getName().isEmpty() || schemadoc.getName().trim() != "" || !schemadoc.getName().isBlank()) {
            if (!schemadoc.getIdDivision().describeConstable().isEmpty()) {
                schemadoc.setIdDivision(7L);
                schemadoc.setCurrent(true);
                docSchemaDocRepo.saveAndFlush(schemadoc);
            }
        }
        return "redirect:/schemadoc";
    }

    @GetMapping(path ="/showschemadoc/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        docSchemaDoc schemadoc = docSchemaDocRepo.getById(id);
        model.addAttribute("schemadoc", schemadoc);
        return "/schemadoc/showschemadoc";
    }

    @GetMapping("/deleteschemadoc/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        docSchemaDocRepo.deleteById(id);
        return "redirect:/schemadoc";

    }
    @PostMapping("/upload")

    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {

        if (!file.isEmpty()) {

            // Логика обработки файла

            model.addAttribute("message", "Файл успешно загружен: " + file.getOriginalFilename());

        } else {

            model.addAttribute("message", "Пожалуйста, выберите файл для загрузки.");

        }

        return "result";

    }
    @Override
    public void run(String... args) throws Exception {
    }
}
