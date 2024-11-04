package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Data
@Entity
@Table(name = "doc_schema_doc", schema = "library", catalog = "dev")
public class docSchemaDoc {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "current", nullable = true)
    private Boolean current = true;
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreate;
    @Basic
    @Column(name = "id_division", nullable = true, insertable = false, updatable = false)
    private Long idDivision;

//    @OneToMany(mappedBy = "linkDataDocSchemaDocById", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Collection<FileData> binFilesById;

    @ManyToOne
    @JoinColumn(name = "id_division", referencedColumnName = "id")
    private libDivision linkDivisionByIdDivision;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(Long idDivision) {
        this.idDivision = idDivision;
    }

    public libDivision getLibDivisionByIdDivision() {
        return linkDivisionByIdDivision;
    }

    public void setLibDivisionByIdDivision(libDivision libDivisionByIdDivision) {
        this.linkDivisionByIdDivision = libDivisionByIdDivision;
    }

    @Override
    public String toString() {
        return "docSchemaDoc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", current=" + current +
                ", dateCreate=" + dateCreate +
                ", idDivision=" + idDivision +
                '}';
    }
}
