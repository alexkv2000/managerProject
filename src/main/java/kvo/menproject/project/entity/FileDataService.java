package kvo.menproject.project.entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDataService {

    private final FileDataRepository fileDataRepo; // Репозиторий для работы с FileData

    public FileDataService(FileDataRepository fileDataRepo) {
        this.fileDataRepo = fileDataRepo;
    }

    public void saveFileData(FileData fileData) {
        fileDataRepo.save(fileData);
    }
}