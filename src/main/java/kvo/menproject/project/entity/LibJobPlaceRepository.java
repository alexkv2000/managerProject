package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibJobPlaceRepository extends JpaRepository<LibJobPlace, Long> {
    LibJobPlace findById(long id);

    Page<LibJobPlace> findAll(Pageable pageable);
    void deleteById(long id);
}