package kvo.menproject.project.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {
    List<FileData> findByName(String nameFile);
    List<FileData> findAll();
    FileData findById(long id);
    void deleteById(long id);
}