package kvo.menproject.project.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

import static java.lang.String.valueOf;

@Controller
public class docSchemaDocController implements CommandLineRunner {
    public docSchemaDocRepository docSchemaDocRepo;
    public libDivisionRepository libDivisionRepo;
    public FileDataRepository fileDataRepo;


    public docSchemaDocController(docSchemaDocRepository docSchemaDocRepo, libDivisionRepository libDivisionRepo, FileDataRepository fileDataRepo) {
        this.docSchemaDocRepo = docSchemaDocRepo;
        this.libDivisionRepo = libDivisionRepo;
        this.fileDataRepo = fileDataRepo;
    }

    @GetMapping(path = "/schemadoc")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<docSchemaDoc> rows = docSchemaDocRepo.findAll(pageable);
//        Page<docSchemaDoc> rows = docSchemaDocRepo.findAllByIdAndBinFilesById(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " Schema Documents");
        } else {
            System.out.println("No schema Document retrieved");
        }

        model.addAttribute("schemadocs", rows);
        model.addAttribute("divisions", libDivisionRepo.findAll());
        //TODO потом изменить на конкретные ID (а не все из БД)
        model.addAttribute("fileData", fileDataRepo.findAll());
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/schemadoc/mainschemadoc";
    }

    @GetMapping(path = "/addschemadoc")
    public String addNewDivision(Model model) {
        docSchemaDoc docSchemaDoc = new docSchemaDoc();
        model.addAttribute("libdivisions", libDivisionRepo.findAll());
        model.addAttribute("schemadoc", docSchemaDoc);
//        model.addAttribute("binfile",binfileRepo);
        return "/schemadoc/newschemadoc";
    }

    @PostMapping(path = "/saveschemadoc")
    public String saveDivision(@ModelAttribute("schemadoc") docSchemaDoc schemadoc,
                               Model model) throws IOException {

        if (!schemadoc.getName().isEmpty() || schemadoc.getName().trim() != "" || !schemadoc.getName().isBlank()) {
//            SimpleDateFormat dateTemp = new SimpleDateFormat("dd-MM-yyyy");
//            Date date = dateTemp.parse(String.valueOf(schemadoc.getDateCreate().toLocalDate()));
            if (schemadoc.getLinkDivisionByIdDivision().getId() > 0) {
                schemadoc.setIdDivision(schemadoc.getLinkDivisionByIdDivision().getId());
            } else schemadoc.setIdDivision(1L);
//            if (schemadoc.getIdDivision().describeConstable().isPresent()) {
//                for (MultipartFile file : binFiles) {
//                    byte[] bytes = file.getBytes(); // Получаем массив байт
//                    // Сохранение файла или другие операции
//                    schemadoc.setBinFiles(bytes);
//                }
//                new FileData().setData(binFiles.getBytes());
            docSchemaDocRepo.saveAndFlush(schemadoc);
//            }
        }
        model.addAttribute("libdivisions", libDivisionRepo.findAll());
        model.addAttribute("schemadoc", schemadoc);
        System.out.println(schemadoc.getDateCreate());
        return "redirect:/schemadoc";
    }

    @GetMapping(path = "/showschemadoc/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        docSchemaDoc schemadoc = docSchemaDocRepo.findById(id);
        model.addAttribute("libdivisions", libDivisionRepo.findAll());
        model.addAttribute("schemadoc", schemadoc);
        return "/schemadoc/showschemadoc";
    }

    @GetMapping("/deleteschemadoc/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        docSchemaDocRepo.deleteById(id);
        return "redirect:/schemadoc";

    }

    @PostMapping("/upload2")
    public String handleFileUploadButton(@ModelAttribute("schemadoc") docSchemaDoc
                                                 schemadoc, @RequestParam("binFiles") List<MultipartFile> files,
                                         Model model,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size) throws IOException {
        Pageable pageable = PageRequest.of(page, size);
        Page<docSchemaDoc> rows = docSchemaDocRepo.findAll(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " Schema Documents");
        } else {
            System.out.println("No schema Document retrieved");
        }
        if (!files.isEmpty()) {

            // Логика обработки файла

            model.addAttribute("message", "Файл успешно загружен.");

        } else {
            for (MultipartFile file : files) {
                FileData fileData = new FileData();

                fileData.setData(file.getBytes()); // Assuming data is of byte array type

                fileData.setSizeFile(valueOf(file.getSize())); // Associate with the entity
                fileData.setId_Data(schemadoc.getId());
                fileData.setName(file.getOriginalFilename()); // Associate with the entity
                fileDataRepo.saveAndFlush(fileData);


                model.addAttribute("schemadocs", rows);
                model.addAttribute("divisions", libDivisionRepo.findAll());
                //TODO потом изменить на конкретные ID (а не все из БД)
                model.addAttribute("fileData", fileDataRepo.findAll());
                model.addAttribute("totalPages", rows.getTotalPages());
                model.addAttribute("currentPage", page);
                model.addAttribute("message", "Пожалуйста, выберите файл для загрузки.");

            }
        }
        return "redirect:/mainschemadoc";
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
