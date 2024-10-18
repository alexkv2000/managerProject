package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "doc_schema_doc", schema = "library", catalog = "dev")
public class docSchemaDoc {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "current", nullable = true)
    private Boolean current;
    //    @Basic
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_create", nullable = false)
    private Date dateCreate;
    @Column(name = "bin_files", nullable = true, insertable=false, updatable=false)
    private long binFiles;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id_division", nullable = true, insertable = false, updatable = false)
    private Long idDivision;

    @ManyToOne
    @JoinColumn(name = "id_division", referencedColumnName = "id")
    private libDivision libDivisionByIdDivision;
    @ManyToOne
    @JoinColumn(name = "bin_files", referencedColumnName = "id")
    private binfile binFilesByBinFiles;


    public binfile getBinFilesByBinFiles() {
        return binFilesByBinFiles;
    }

    public void setBinFilesByBinFiles(binfile binFilesByBinFiles) {
        this.binFilesByBinFiles = binFilesByBinFiles;
    }

    public long getBinFiles() {
        return binFiles;
    }

    public void setBinFiles(long binFiles) {
        this.binFiles = binFiles;
    }

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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
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

    @Override
    public String toString() {
        return "docSchemaDoc{" +
                "name='" + name + '\'' +
                ", current=" + current +
                ", dateCreate=" + dateCreate +
                ", binFiles=" + binFiles +
                ", id=" + id +
                ", idDivision=" + idDivision +
                ", libDivisionByIdDivision=" + libDivisionByIdDivision +
                '}';
    }

    public libDivision getLibDivisionByIdDivision() {
        return libDivisionByIdDivision;
    }

    public void setLibDivisionByIdDivision(libDivision libDivisionByIdDivision) {
        this.libDivisionByIdDivision = libDivisionByIdDivision;
    }

}
