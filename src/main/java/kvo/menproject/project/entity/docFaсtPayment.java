package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data

@Entity
@Table(name = "doc_faсt_payment", schema = "public", catalog = "dev")
public class docFaсtPayment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "commetns", nullable = true, length = -1)
    private String commetns;
//    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_pay_doc", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataPayDoc;
    @Basic
    @Column(name = "paid", nullable = true)
    private Boolean paid;
    @Basic
    @Column(name = "project_id", nullable = true, insertable=false, updatable=false)
    private Long projectId;
    @Basic
    @Column(name = "step_project_id", nullable = true, insertable=false, updatable=false)
    private Long stepProjectId;
    @Basic
    @Column(name = "sum_capex", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal sumCapex;
    @Basic
    @Column(name = "sum_capex_nds", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal sumCapexNds;
    @Basic
    @Column(name = "sum_opex", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal sumOpex;
    @Basic
    @Column(name = "sum_opex_nds", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal sumOpexNds;
    @Basic
    @Column(name = "binfiles_id", nullable = true, insertable=false, updatable=false)
    private Long binfilesId;
    @Basic
    @Column(name = "status", length = 18, nullable = false)
    private String statusFact;
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private docProjectsList projectsListByProjectId;
    @ManyToOne
    @JoinColumn(name = "step_project_id", referencedColumnName = "id")
    private docPlanPayProject planPayProjectById;
    @ManyToOne
    @JoinColumn(name = "binfiles_id", referencedColumnName = "id")
    private FileData binFilesByBinfilesId;

    @Override
    public String toString() {
        return "docFaсtPayment{" +
                "id=" + id +
                ", commetns='" + commetns + '\'' +
                ", dataPayDoc=" + dataPayDoc +
                ", paid=" + paid +
                ", projectId=" + projectId +
                ", stepProjectId=" + stepProjectId +
                ", sumCapex=" + sumCapex +
                ", sumCapexNds=" + sumCapexNds +
                ", sumOpex=" + sumOpex +
                ", sumOpexNds=" + sumOpexNds +
                ", binfilesId=" + binfilesId +
                ", status='" + statusFact + '\'' +
                '}';
    }

    public String getCommetns() {
        return commetns;
    }

    public void setCommetns(String commetns) {
        this.commetns = commetns;
    }

    public Date getDataPayDoc() {
        return dataPayDoc;
    }

    public void setDataPayDoc(Date dataPayDoc) {
        this.dataPayDoc = dataPayDoc;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getStepProjectId() {
        return stepProjectId;
    }

    public void setStepProjectId(Long stepProjectId) {
        this.stepProjectId = stepProjectId;
    }

    public BigDecimal getSumCapex() {
        return sumCapex;
    }

    public void setSumCapex(BigDecimal sumCapex) {
        this.sumCapex = sumCapex;
    }

    public BigDecimal getSumCapexNds() {
        return sumCapexNds;
    }

    public void setSumCapexNds(BigDecimal sumCapexNds) {
        this.sumCapexNds = sumCapexNds;
    }

    public BigDecimal getSumOpex() {
        return sumOpex;
    }

    public void setSumOpex(BigDecimal sumOpex) {
        this.sumOpex = sumOpex;
    }

    public BigDecimal getSumOpexNds() {
        return sumOpexNds;
    }

    public void setSumOpexNds(BigDecimal sumOpexNds) {
        this.sumOpexNds = sumOpexNds;
    }

    public Long getBinfilesId() {
        return binfilesId;
    }

    public void setBinfilesId(Long binfilesId) {
        this.binfilesId = binfilesId;
    }

    public String getStatusFact() {
        return statusFact;
    }

    public String setStatusFact(String statusFact) {
        this.statusFact = statusFact;
        return statusFact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        docFaсtPayment that = (docFaсtPayment) o;

        if (id != that.id) return false;
        if (commetns != null ? !commetns.equals(that.commetns) : that.commetns != null) return false;
        if (dataPayDoc != null ? !dataPayDoc.equals(that.dataPayDoc) : that.dataPayDoc != null) return false;
        if (paid != null ? !paid.equals(that.paid) : that.paid != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (stepProjectId != null ? !stepProjectId.equals(that.stepProjectId) : that.stepProjectId != null)
            return false;
        if (sumCapex != null ? !sumCapex.equals(that.sumCapex) : that.sumCapex != null) return false;
        if (sumCapexNds != null ? !sumCapexNds.equals(that.sumCapexNds) : that.sumCapexNds != null) return false;
        if (sumOpex != null ? !sumOpex.equals(that.sumOpex) : that.sumOpex != null) return false;
        if (sumOpexNds != null ? !sumOpexNds.equals(that.sumOpexNds) : that.sumOpexNds != null) return false;
        if (binfilesId != null ? !binfilesId.equals(that.binfilesId) : that.binfilesId != null) return false;
        if (statusFact != null ? !statusFact.equals(that.statusFact) : that.statusFact != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commetns != null ? commetns.hashCode() : 0;
        result = 31 * result + (dataPayDoc != null ? dataPayDoc.hashCode() : 0);
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (stepProjectId != null ? stepProjectId.hashCode() : 0);
        result = 31 * result + (sumCapex != null ? sumCapex.hashCode() : 0);
        result = 31 * result + (sumCapexNds != null ? sumCapexNds.hashCode() : 0);
        result = 31 * result + (sumOpex != null ? sumOpex.hashCode() : 0);
        result = 31 * result + (sumOpexNds != null ? sumOpexNds.hashCode() : 0);
        result = 31 * result + (binfilesId != null ? binfilesId.hashCode() : 0);
        result = 31 * result + (statusFact != null ? statusFact.hashCode() : 0);
        return result;
    }

    public docProjectsList getProjectsListByProjectId() {
        return projectsListByProjectId;
    }

    public void setProjectsListByProjectId(docProjectsList projectsListByProjectId) {
        this.projectsListByProjectId = projectsListByProjectId;
    }


    public FileData getBinFilesByBinfilesId() {
        return binFilesByBinfilesId;
    }

    public void setBinFilesByBinfilesId(FileData binFilesByBinfilesId) {
        this.binFilesByBinfilesId = binFilesByBinfilesId;
    }
}
