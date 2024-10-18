package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibEntityRepository extends JpaRepository<LibEntity, Long> {
//    Page<LibEntity> findAllByActiveIsTrue(Pageable pageable);

    Page<LibEntity> findAll(Pageable pageable);
    void deleteById(long id);

}
