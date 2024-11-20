package kvo.menproject.project.entity;

import kvo.menproject.project.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface docPlanPayProjectRepository extends JpaRepository<docPlanPayProject, Long> {
    Page<docPlanPayProject> findAllByCompletedIsTrue(Pageable pageable);

    Page<docPlanPayProject> findAllByCompletedIsFalse(Pageable pageable);

    Page<docPlanPayProject> findAll(Pageable pageable);

    Page<docPlanPayProject> findAllByCompletedIsFalseOrderByLibDivisionByProjectIdAsc(Pageable pageable);

    Page<docPlanPayProject> findAllByCompletedIsTrueOrderByLibDivisionByProjectIdAsc(Pageable pageable);

    //    Page<docPlanPayProject> findAllByLibDivisionByProjectId(Pageable pageable);
    List<docPlanPayProject>findAllByDocProjectsListByPlanProject_Id(long idProject);
    @Query("SELECT o.docProjectsListByPlanProject.id, o.docProjectsListByPlanProject.name, COUNT(o) FROM docPlanPayProject o GROUP BY o.docProjectsListByPlanProject.id, o.docProjectsListByPlanProject.name")
    List<Object[]> getAllByIdProject();

    void deleteById(long id);
}