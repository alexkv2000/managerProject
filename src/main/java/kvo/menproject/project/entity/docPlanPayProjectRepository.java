package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface docPlanPayProjectRepository extends JpaRepository<docPlanPayProject, Long> {
    Page<docPlanPayProject> findAllByCompletedIsTrue(Pageable pageable);
    Page<docPlanPayProject> findAllByCompletedIsFalse(Pageable pageable);
    Page<docPlanPayProject> findAll(Pageable pageable);
    Page<docPlanPayProject> findAllByCompletedIsFalseOrderByProjectIdAscStepProjectAsc(Pageable pageable);
    Page<docPlanPayProject> findAllByCompletedIsTrueOrderByProjectIdAscStepProjectAsc(Pageable pageable);
    Page<docPlanPayProject> findAllByProjectId(Pageable pageable);
    void deleteById(long id);
}