package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAllByShowIsTrue(Pageable pageable);
    Page<Blog> findAllByShowIsFalse(Pageable pageable);
    List<Blog> findAllByOrderByDcreateDesc();
    Page<Blog> findAll(Pageable pageable);

    //    Page<docFaсtPayment> findAllByPaidIsFalseOrderByProjectsListByProjectIdAscPlanPayProjectByStepProjectIdAsc(Pageable pageable);
//    Page<docFaсtPayment> findAllByPaidIsTrueOrderByProjectsListByProjectIdAscPlanPayProjectByStepProjectIdAsc(Pageable pageable);
    void deleteById(long id);
}