package kvo.menproject.project.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface docSchemaDocRepository extends JpaRepository<docSchemaDoc, Long> {

//    @Query(" SELECT s.id, s.name, s.current, s.date_create, d.name_division, d.organisation, d.active , bf.name, bf.size_file\n" +
//            "            FROM library.doc_schema_doc s\n" +
//            "            JOIN library.lib_division d ON s.id_division = d.id\n" +
//            "            JOIN bin_files bf on s.id = bf.id_data\n" +
//            "            ORDER BY s.name ASC")
//    Page<docSchemaDoc> findAllByIdAndBinFilesById(Pageable pageable);

    Page<docSchemaDoc> findAll(Pageable pageable);
    void deleteById(long id);

    docSchemaDoc findById(long id);
}