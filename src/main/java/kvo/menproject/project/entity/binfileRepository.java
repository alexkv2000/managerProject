package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface binfileRepository extends JpaRepository <binfile, Long>{

    List<binfile> findAll();
    binfile findById(long id);
    void deleteById(long id);

}
