package kvo.menproject.project.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;
@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {
    List<FileData> findByName(String nameFile);
    List<FileData> findAll();
    List<FileData> findAllByTypeDocAndIdData(String typeDoc, long id);
    List<FileData> findAllByTypeDoc(String typeDoc);
    FileData findById(long id);
    void deleteById(long id);
}