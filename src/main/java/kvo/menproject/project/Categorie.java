package kvo.menproject.project;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "сategorie", schema = "public", catalog = "dev")
public class Categorie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "id_sub", nullable = false)
    private long idSub;
    @Basic
    @Column(name = "subcategory", nullable = true, insertable=false, updatable=false)
    private String Subcategory;

}
