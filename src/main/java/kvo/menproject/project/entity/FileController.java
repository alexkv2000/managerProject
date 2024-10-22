package kvo.menproject.project.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

@Controller
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileDataRepository fileDataRepo;
    public docSchemaDocRepository docSchemaDocRepo;
    public libDivisionRepository libDivisionRepo;

    public FileController(FileDataRepository fileDataRepo, docSchemaDocRepository docSchemaDocRepo, libDivisionRepository libDivisionRepo) {
        this.fileDataRepo = fileDataRepo;
        this.docSchemaDocRepo = docSchemaDocRepo;
        this.libDivisionRepo = libDivisionRepo;
    }

    @PostMapping("/upload1")
    public String handleFileUpload(@ModelAttribute("schemadoc") docSchemaDoc schemadoc, @RequestParam("file") List<MultipartFile> files, RedirectAttributes redirectAttributes, Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) throws IOException {
        Pageable pageable = PageRequest.of(page, size);
        Page<docSchemaDoc> rows = docSchemaDocRepo.findAll(pageable);
        if (files.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Вы не выбрали файл!");
            model.addAttribute("schemadocs", rows);
            model.addAttribute("divisions", libDivisionRepo.findAll());
            //TODO потом изменить на конкретные ID (а не все из БД)
            model.addAttribute("fileData", fileDataRepo.findAll());
            model.addAttribute("totalPages", rows.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("message", "Пожалуйста, выберите файл для загрузки.");
            return "redirect:/schemadoc";
        }
        try {
            // Создаем объект FileData и заполняем его данными
            for (MultipartFile file : files) {
                FileData fileData = new FileData();
                fileData.setName(file.getOriginalFilename());
                fileData.setSizeFile(String.valueOf(file.getSize())); // Размер файла в байтах
                fileData.setData(file.getBytes()); // Содержимое файла в виде массива байтов

                // Сохраняем файл в базе данных
                fileDataRepo.saveAndFlush(fileData);

                redirectAttributes.addFlashAttribute("message", "Файл загружен успешно: " + file.getOriginalFilename());
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при загрузке файла: " + e.getMessage());
        }


////        docSchemaDoc entity = new docSchemaDoc();
////        List<FileData> fileDataList = new ArrayList<>();
//        for (MultipartFile file : files) {
//            FileData fileData = new FileData();
//
//            fileData.setData(file.getBytes()); // Assuming data is of byte array type
//            System.out.println("file.getBytes " + file.getBytes());
//            System.out.println("file.getOriginalFilename " + file.getOriginalFilename());
//            System.out.println("file.getSize " + file.getSize());
////                fileData.setData(file.getBytes()); // If you want to store the filename too
//            fileData.setSizeFile(valueOf(file.getSize())); // Associate with the entity
//            fileData.setId_Data(schemadoc.getId());
//            fileData.setName(file.getOriginalFilename()); // Associate with the entity
//
////            fileData.setDocSchemaDoc(entity);
////            fileDataList.add(fileData);
//            fileDataRepo.saveAndFlush(fileData);
//            // Save the entity and the associated file data
//            // Assuming this method exists in YourEntity to set the file data
////        entity.save(entity); // Save to database
//        }

        return "redirect:/schemadoc";
    }
}