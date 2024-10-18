package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lib_job_place", schema = "library", catalog = "dev")
@Data
public class LibJobPlace {
    @Basic
    @Column(name = "number_place", nullable = true, length = -1)
    private String numberplace;
    @Basic
    @Column(name = "coments", nullable = true, length = -1)
    private String comment;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    public String getNumberplace() {
        return numberplace;
    }

    public void setNumberplace(String numberPlace) {
        this.numberplace = numberPlace;
    }

    public String getComent() {
        return comment;
    }

    public void setComent(String coments) {
        this.comment = coments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibJobPlace that = (LibJobPlace) o;

        if (id != that.id) return false;
        if (numberplace != null ? !numberplace.equals(that.numberplace) : that.numberplace != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numberplace != null ? numberplace.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
