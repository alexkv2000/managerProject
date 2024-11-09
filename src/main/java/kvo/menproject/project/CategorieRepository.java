package kvo.menproject.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findAllByIdSub(long idSub);
   List<Categorie> findAll();
   @Query("SELECT o.idSub, COUNT(o) FROM Categorie o GROUP BY o.idSub")
    List<Object[]> getAllByIdSub();
    void deleteById(long id);
}