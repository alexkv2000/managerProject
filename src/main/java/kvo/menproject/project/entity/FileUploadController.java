package kvo.menproject.project.entity;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Optional;

@Controller
public class FileUploadController {
    public binStorageRepository binStorageRepo;
    public docFaсtPaymentRepository faсtPaymentRepo;
    private final FileDataService fileDataService; // Сервис для работы с FileData

    public FileUploadController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
//        this.docSchemaDocRepo = docSchemaDocRepo;
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @ModelAttribute("schemaId") docSchemaDoc schemdoc, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Вы не выбрали файл!");
            return "redirect:/schemadoc";
        }
        try {
            // Создаем объект FileData и заполняем его данными
            FileData fileData = new FileData();
            //TODO schemaID
            fileData.setId_Data(schemdoc.getId());
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

    @PostMapping("/uploadbinstorage")
    public String handleFileUploadBinStorage(@RequestParam("file") MultipartFile file, @ModelAttribute("binstorageId") binStorage binstorage, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Вы не выбрали файл!");
            return "redirect:/binstorage";
        }
//        boolean x = typeCorrect(file);
        if (!typeCorrect(file)) return "redirect:/binstorage";
        try {
            // Создаем объект FileData и заполняем его данными
            FileData fileData = new FileData();
            //TODO schemaID
            fileData.setId_Data(binstorage.getId());
            fileData.setTypeDoc("binStorage");
            fileData.setName(file.getOriginalFilename());
            fileData.setSizeFile(String.valueOf(file.getSize())); // Размер файла в байтах
            fileData.setData(file.getBytes()); // Содержимое файла в виде массива байтов
            // Сохраняем файл в базе данных
            fileDataService.saveFileData(fileData);

            redirectAttributes.addFlashAttribute("message", "Файл загружен успешно: " + file.getOriginalFilename());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при загрузке файла: " + e.getMessage());
        }
        return "redirect:/binstorage";
    }

    @Nullable
    private static boolean typeCorrect(MultipartFile file) {
        String ext;
        ext = FilenameUtils.getExtension(file.getOriginalFilename());
        if ("jpg.jpeg.png.gif.doc.docx.xls.xlsx.dot.xlst.txt.srv.reg.pdf".indexOf(ext) < 0) {
            return false;
        } else {
            return true;
        }
    }

    @PostMapping("/uploadfactpayment")
    public String handleFileUploadBinStorageFactpayment(@RequestParam("file") MultipartFile file, @ModelAttribute("factpaymentId") docFaсtPayment factPayment, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Вы не выбрали файл!");
            return "redirect:/factpayment";
        }
        String ext;
        ext = FilenameUtils.getExtension(file.getOriginalFilename());
        if ("jpg.jpeg.png.gif.doc.docx.xls.xlsx.dot.xlst.txt.srv.reg.pdf".indexOf(ext) < 0)
            return "redirect:/factpayment";

        try {
            // Создаем объект FileData и заполняем его данными
            FileData fileData = new FileData();
            //TODO schemaID
            fileData.setId_Data(factPayment.getId());
            fileData.setTypeDoc("factPayment");
            fileData.setName(file.getOriginalFilename());
            fileData.setSizeFile(String.valueOf(file.getSize())); // Размер файла в байтах
            fileData.setData(file.getBytes()); // Содержимое файла в виде массива байтов
            // Сохраняем файл в базе данных
            fileDataService.saveFileData(fileData);

            redirectAttributes.addFlashAttribute("message", "Файл загружен успешно: " + file.getOriginalFilename());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при загрузке файла: " + e.getMessage());
        }
        return "redirect:/factpayment";
    }
}
