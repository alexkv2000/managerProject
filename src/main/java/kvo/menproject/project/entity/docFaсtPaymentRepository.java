package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface docFaсtPaymentRepository extends JpaRepository<docFaсtPayment, Long> {
    Page<docFaсtPayment> findAllByPaidIsTrue(Pageable pageable);
    Page<docFaсtPayment> findAllByPaidIsFalse(Pageable pageable);
    Page<docFaсtPayment> findAll(Pageable pageable);
//    Page<docFaсtPayment> findAllByPaidIsFalseOrderByProjectsListByProjectIdAscPlanPayProjectByStepProjectIdAsc(Pageable pageable);
//    Page<docFaсtPayment> findAllByPaidIsTrueOrderByProjectsListByProjectIdAscPlanPayProjectByStepProjectIdAsc(Pageable pageable);
    void deleteById(long id);
}