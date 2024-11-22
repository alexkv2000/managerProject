package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface docProjectsListRepository extends JpaRepository<docProjectsList, Long> {
    Page<docProjectsList> findAllByClosedIsFalse(Pageable pageable);
    List<docProjectsList> findAllByClosedIsFalse();
    docProjectsList findAllById(long idProject);
    @Query("SELECT e.name, e.headManager, e.responsibleFromBusiness, e.curator, e.closed, e.dogovorNumber, e.linkDogovor," +
            "e.dateCreate, e.dateEnd, e.dateStart, e.summaProject, e.opex, e.opexNds, e.capex, e.capexNds, e.comment, d.namedivision, emp.fio " +
            "FROM docProjectsList e " +
            "JOIN libDivision d ON e.libDivisionByIdDivision.id = d.id " +
            "JOIN libEmployee emp ON e.libJobEmployeeByOwner.id = emp.id " +
            "where not e.closed ORDER BY e.name ASC")

    Page<docProjectsList> findAllByClosedIsFalseSortedByName(Pageable pageable);

    Page<docProjectsList> findAll(Pageable pageable);
    void deleteById(long id);

}