package kvo.menproject.project.entity;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface libDivisionRepository extends JpaRepository<libDivision, Long> {
    Page<libDivision> findAllByActiveIsTrue(Pageable pageable);

    <S extends libDivision> List<S> findAllByActiveIsTrue();
    libDivision findById(long id);

    Page<libDivision> findAll(Pageable pageable);
    void deleteById(long id);


}