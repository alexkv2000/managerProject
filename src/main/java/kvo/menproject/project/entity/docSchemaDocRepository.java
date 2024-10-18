package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface docSchemaDocRepository extends JpaRepository<docSchemaDoc, Long> {

//    @Query("SELECT s.id, s.name, s.current, s.dateCreate, d.namedivision, d.organisation, d.active, s.binFiles " +
//            "FROM docSchemaDoc s " +
//            "JOIN libDivision d ON s.idDivision = d.id " +
//            "ORDER BY s.name ASC")
    Page<docSchemaDoc> findAll(Pageable pageable);
    void deleteById(long id);
    docSchemaDoc findById(long id);
}