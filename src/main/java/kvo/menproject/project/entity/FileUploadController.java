package kvo.menproject.project.entity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;

@Controller
public class FileUploadController {
//    public docSchemaDocRepository docSchemaDocRepo;
    private final FileDataService fileDataService; // Сервис для работы с FileData

    public FileUploadController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
//        this.docSchemaDocRepo = docSchemaDocRepo;
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @ModelAttribute("schemaId") docSchemaDoc schemdoc, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Вы не выбрали файл!");
            return "redirect:/uploadStatus";
        }

        try {
            // Создаем объект FileData и заполняем его данными
            FileData fileData = new FileData();
            //TODO schemaID

            fileData.setLinkDataDocSchemaDocById(schemdoc);
            fileData.setTypeDoc("docSchemaDoc");
            fileData.setName(file.getOriginalFilename());
            fileData.setSizeFile(String.valueOf(file.getSize())); // Размер файла в байтах
            fileData.setData(file.getBytes()); // Содержимое файла в виде массива байтов

            // Сохраняем файл в базе данных
            fileDataService.saveFileData(fileData);

            redirectAttributes.addFlashAttribute("message", "Файл загружен успешно: " + file.getOriginalFilename());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при загрузке файла: " + e.getMessage());
        }

        return "redirect:/schemadoc";
    }

}
