package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "projects_list", schema = "library", catalog = "dev")
public class docProjectsList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "id_division", nullable = true, insertable=false, updatable=false)
    private long id_division;
    @Basic
    @Column(name = "head_manager", nullable = true, length = -1)
    private String headManager;
    @Basic
    @Column(name = "responsible_from_business", nullable = true, length = -1)
    private String responsibleFromBusiness;
    @Basic
    @Column(name = "curator", nullable = true, length = -1)
    private String curator;
    @Basic
    @Column(name = "closed", nullable = false)
    private boolean closed;
    @Basic
    @Column(name = "dogovor_number", nullable = true, length = -1)
    private String dogovorNumber;
    @Basic
    @Column(name = "link_dogovor", nullable = true, length = -1)
    private String linkDogovor;
//    @Basic(optional = false)
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateCreate = new Date();
    @DateTimeFormat(pattern = "dd.MM.yyyy")
//    @Basic
    @Column(name = "date_start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateStart = new Date();
    @DateTimeFormat(pattern = "dd.MM.yyyy")
//    @Basic
    @Column(name = "date_end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEnd = new Date();
    @Basic
    @Column(name = "owner", nullable = true, insertable=false, updatable=false)
    private Long id_owner;
    @Basic
    @Column(name = "summa_project", nullable = true)
    private Integer summaProject;
    @Basic
    @Column(name = "opex", nullable = true)
    private Integer opex;
    @Basic
    @Column(name = "opex_nds", nullable = true)
    private Integer opexNds;
    @Basic
    @Column(name = "capex", nullable = true)
    private Integer capex;
    @Basic
    @Column(name = "capex_nds", nullable = true)
    private Integer capexNds;
    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "id_division", referencedColumnName = "id")
    private libDivision libDivisionByIdDivision;
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private libEmployee libJobEmployeeByOwner;
    @OneToMany(mappedBy = "projectsListByNameProject")
    private Collection<binStorage> binProjectsById;

    public Collection<binStorage> getBinProjectsById() {
        return binProjectsById;
    }

    public void setBinProjectsById(Collection<binStorage> binProjectsById) {
        this.binProjectsById = binProjectsById;
    }
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "docProjectsList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_division=" + id_division +
                ", headManager='" + headManager + '\'' +
                ", responsibleFromBusiness='" + responsibleFromBusiness + '\'' +
                ", curator='" + curator + '\'' +
                ", closed=" + closed +
                ", dogovorNumber='" + dogovorNumber + '\'' +
                ", linkDogovor='" + linkDogovor + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", id_owner=" + id_owner +
                ", summaProject=" + summaProject +
                ", opex=" + opex +
                ", opexNds=" + opexNds +
                ", capex=" + capex +
                ", capexNds=" + capexNds +
                ", comment='" + comment + '\'' +
//                ", libDivisionByIdDivision=" + libDivisionByIdDivision +
//                ", libJobEmployeeByOwner=" + libJobEmployeeByOwner +
//                ", binProjectsById=" + binProjectsById +
                '}';
    }
}
