package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "lib_job_place", schema = "library", catalog = "dev")
@Data
public class LibJobPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "number_place", nullable = true, length = -1)
    private String numberplace;
    @Basic
    @Column(name = "coments", nullable = true, length = -1)
    private String comment;
    @Basic
    @Column(name = "place_employee", nullable = true, insertable = false, updatable = false)
    private Long placeEmployee;
    @ManyToOne
    @JoinColumn(name = "place_employee", referencedColumnName = "id")
    private libEmployee libJobEmployeeByPlaceEmployee;

    public LibJobPlace() {
    }

    public LibJobPlace(long id, String numberplace, String comment, Long placeEmployee, libEmployee libJobEmployeeByPlaceEmployee) {
        this.id = id;
        this.numberplace = numberplace;
        this.comment = comment;
        this.placeEmployee = placeEmployee;
        this.libJobEmployeeByPlaceEmployee = libJobEmployeeByPlaceEmployee;
    }

    @Override
    public String toString() {
        return "LibJobPlace{" +
                "id=" + id +
                ", numberplace='" + numberplace + '\'' +
                ", comment='" + comment + '\'' +
                ", placeEmployee=" + placeEmployee +
                ", libJobEmployeeByPlaceEmployee=" + libJobEmployeeByPlaceEmployee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibJobPlace that = (LibJobPlace) o;

        if (id != that.id) return false;
        if (!Objects.equals(numberplace, that.numberplace)) return false;
        if (!Objects.equals(comment, that.comment)) return false;
        if (!Objects.equals(placeEmployee, that.placeEmployee))
            return false;
        return Objects.equals(libJobEmployeeByPlaceEmployee, that.libJobEmployeeByPlaceEmployee);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (numberplace != null ? numberplace.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (placeEmployee != null ? placeEmployee.hashCode() : 0);
        result = 31 * result + (libJobEmployeeByPlaceEmployee != null ? libJobEmployeeByPlaceEmployee.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumberplace() {
        return numberplace;
    }

    public void setNumberplace(String numberplace) {
        this.numberplace = numberplace;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPlaceEmployee() {
        return placeEmployee;
    }

    public void setPlaceEmployee(Long placeEmployee) {
        this.placeEmployee = placeEmployee;
    }

    public libEmployee getLibJobEmployeeByPlaceEmployee() {
        return libJobEmployeeByPlaceEmployee;
    }

    public void setLibJobEmployeeByPlaceEmployee(libEmployee libJobEmployeeByPlaceEmployee) {
        this.libJobEmployeeByPlaceEmployee = libJobEmployeeByPlaceEmployee;
    }
}
