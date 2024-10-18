package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface libEmployeeRepository extends JpaRepository<libEmployee, Long> {
        Page<libEmployee> findAllByActiveIsTrue(Pageable pageable);

        List<libEmployee> findAllByActiveIsTrue();

        Page<libEmployee> findAll(Pageable pageable);
        void deleteById(long id);
}