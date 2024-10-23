package kvo.menproject.project.entity;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RestController
public class FileRestController {
    private FileDataRepository fileDataRepo;
    private ResourceLoader resourceLoader;

    public FileRestController(FileDataRepository fileDataRepo, ResourceLoader resourceLoader) {
        this.fileDataRepo = fileDataRepo;
        this.resourceLoader = resourceLoader;
    }
// Сервис для получения данных о файлах

    @GetMapping("/download/{id}")
    @Transactional(readOnly = true) // Обеспечьте, чтобы операция была внутри транзакции
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long fileId) throws UnsupportedEncodingException {
        // Получение файла из базы данных или файловой системы
        Optional<FileData> fileData = fileDataRepo.findById(fileId);

        if (!fileData.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        FileData file = fileData.get();
        byte[] data = file.getData();
        ByteArrayResource resource = new ByteArrayResource(data);
        // Здесь определите тип содержимого
        String contentType = "application/octet-stream"; // Замените на актуальный тип, если необходимо "Content-Disposition"
        // Укажите заголовки ответа
        HttpHeaders headers = new HttpHeaders();

        String fileName = URLEncoder.encode(file.getName(), "UTF-8"); // имя файла в кодировке "UTF-8"
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(data.length)
                .body(resource);
    }
}