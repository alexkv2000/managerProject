package kvo.menproject.project.entity;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileDataService {

    private final FileDataRepository fileDataRepo; // Репозиторий для работы с FileData

    public FileDataService(FileDataRepository fileDataRepo) {
        this.fileDataRepo = fileDataRepo;
    }

    public void saveFileData(FileData fileData) {
        fileDataRepo.save(fileData);
    }

    public Optional<FileData> getFileById(Long id) {
        return fileDataRepo.findById(id);
    }
}