package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface binStorageRepository extends JpaRepository<binStorage, Long> {
    List<binStorage> findAllById(long idProject);
    List<binStorage> findAllByNameProject(long idProject);
    List<binStorage> findAll();

    void deleteById(long id);
}