package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface binStorageRepository extends JpaRepository<binStorage, Long> {
    Page<binStorage> findAllByStepProjectOrderByStepProjectAsc(String stepProject, Pageable pageable);
    Page<binStorage> findAllByNameProjectOrderByStepProjectAsc(String nameProject, Pageable pageable);
    Page<binStorage> findAll(Pageable pageable);

    void deleteById(long id);
}