package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
@Data
@Entity
@Table(name = "plan_pay_project", schema = "library", catalog = "dev")
public class docPlanPayProject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
//    @Basic
    @Column(name = "plan_year", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date planYear;
//    @Basic
//    @Column(name = "project_id", nullable = true, insertable=false, updatable=false)
//    private Long projectId;
//    @Basic
    @Column(name = "data_planing", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataPlaning;
    @Basic
    @Column(name = "opex", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal opex;
    @Basic
    @Column(name = "opex_nds", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal opexNds;
    @Basic
    @Column(name = "capex", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal capex;
    @Basic
    @Column(name = "capex_nds", nullable = true, columnDefinition = "DECIMAL(15, 2)")
    private BigDecimal capexNds;
    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    private String comment;
    @Basic
    @Column(name = "step_project", nullable = true, length = -1)
    private String stepProject;
    @Basic
    @Column(name = "duration", nullable = true)
    private Long duration;
    @Basic
    @Column(name = "interval", nullable = true, length = -1)
    private String interval;
    @Basic
    @Column(name = "completed", nullable = true)
    private Boolean completed;
    @Basic
    @Column(name = "payment_on_time", nullable = true)
    private Boolean paymentOnTime;
    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    private libDivision libDivisionByProjectId;
    @ManyToOne
    @JoinColumn(name = "plan_project", referencedColumnName = "id")
    private docProjectsList docProjectsListByPlanProject;
    @OneToMany(mappedBy = "planPayProjectById")
    private Collection<docFaсtPayment> docFaсtPaymentsById;

    public Collection<docFaсtPayment> getDocFaсtPaymentsById() {
        return docFaсtPaymentsById;
    }
    public void setDocFaсtPaymentsById(Collection<docFaсtPayment> docFaсtPaymentsById) {
        this.docFaсtPaymentsById = docFaсtPaymentsById;
    }
    @Override
    public String toString() {
        return "docPlanPayProject{" +
                "id=" + id +
                ", planYear=" + planYear +
//                ", projectId=" + projectId +
                ", dataPlaning=" + dataPlaning +
                ", opex=" + opex +
                ", opexNds=" + opexNds +
                ", capex=" + capex +
                ", capexNds=" + capexNds +
                ", comment='" + comment + '\'' +
                ", stepProject='" + stepProject + '\'' +
                ", duration=" + duration +
                ", interval='" + interval + '\'' +
                ", completed=" + completed +
                ", paymentOnTime=" + paymentOnTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Date planYear) {
        this.planYear = planYear;
    }

//    public Long getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(Long projectId) {
//        this.projectId = projectId;
//    }

    public Date getDataPlaning() {
        return dataPlaning;
    }

    public void setDataPlaning(Date dataPlaning) {
        this.dataPlaning = dataPlaning;
    }

    public BigDecimal getOpex() {
        return opex;
    }

    public void setOpex(BigDecimal opex) {
        this.opex = opex;
    }

    public BigDecimal getOpexNds() {
        return opexNds;
    }

    public void setOpexNds(BigDecimal opexNds) {
        this.opexNds = opexNds;
    }

    public BigDecimal getCapex() {
        return capex;
    }

    public void setCapex(BigDecimal capex) {
        this.capex = capex;
    }

    public BigDecimal getCapexNds() {
        return capexNds;
    }

    public void setCapexNds(BigDecimal capexNds) {
        this.capexNds = capexNds;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStepProject() {
        return stepProject;
    }

    public void setStepProject(String stepProject) {
        this.stepProject = stepProject;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getPaymentOnTime() {
        return paymentOnTime;
    }

    public void setPaymentOnTime(Boolean paymentOnTime) {
        this.paymentOnTime = paymentOnTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        docPlanPayProject that = (docPlanPayProject) o;

        if (id != that.id) return false;
        if (planYear != null ? !planYear.equals(that.planYear) : that.planYear != null) return false;
//        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (dataPlaning != null ? !dataPlaning.equals(that.dataPlaning) : that.dataPlaning != null) return false;
        if (opex != null ? !opex.equals(that.opex) : that.opex != null) return false;
        if (opexNds != null ? !opexNds.equals(that.opexNds) : that.opexNds != null) return false;
        if (capex != null ? !capex.equals(that.capex) : that.capex != null) return false;
        if (capexNds != null ? !capexNds.equals(that.capexNds) : that.capexNds != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (stepProject != null ? !stepProject.equals(that.stepProject) : that.stepProject != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (interval != null ? !interval.equals(that.interval) : that.interval != null) return false;
        if (completed != null ? !completed.equals(that.completed) : that.completed != null) return false;
        if (paymentOnTime != null ? !paymentOnTime.equals(that.paymentOnTime) : that.paymentOnTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (planYear != null ? planYear.hashCode() : 0);
//        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (dataPlaning != null ? dataPlaning.hashCode() : 0);
        result = 31 * result + (opex != null ? opex.hashCode() : 0);
        result = 31 * result + (opexNds != null ? opexNds.hashCode() : 0);
        result = 31 * result + (capex != null ? capex.hashCode() : 0);
        result = 31 * result + (capexNds != null ? capexNds.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (stepProject != null ? stepProject.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (interval != null ? interval.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (paymentOnTime != null ? paymentOnTime.hashCode() : 0);
        return result;
    }

    public libDivision getLibDivisionByProjectId() {
        return libDivisionByProjectId;
    }

    public void setLibDivisionByProjectId(libDivision libDivisionByProjectId) {
        this.libDivisionByProjectId = libDivisionByProjectId;
    }
}
