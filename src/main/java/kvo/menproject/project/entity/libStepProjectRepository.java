package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface libStepProjectRepository extends JpaRepository<libStepProject, Long> {
//    Page<libStepProject> findAllByActiveIsTrue(Pageable pageable);

    Page<libStepProject> findAll(Pageable pageable);
    void deleteById(long id);


}