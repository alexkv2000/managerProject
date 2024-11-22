package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface docFaсtPaymentRepository extends JpaRepository<docFaсtPayment, Long> {
    List<docFaсtPayment> findAllByPaidIsTrue();
    List<docFaсtPayment> findAllByPaidIsFalse();
    List<docFaсtPayment> findAll();
    List<docFaсtPayment> findAllByProjectId(long projectId);
//    Page<docFaсtPayment> findAllByPaidIsFalseOrderByProjectsListByProjectIdAscPlanPayProjectByStepProjectIdAsc(Pageable pageable);
//    Page<docFaсtPayment> findAllByPaidIsTrueOrderByProjectsListByProjectIdAscPlanPayProjectByStepProjectIdAsc(Pageable pageable);
    void deleteById(long id);
}