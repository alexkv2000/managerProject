package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lib_entity", schema = "library", catalog = "dev")
@Data
public class LibEntity {
    @Basic
    @Column(name = "nameentity", nullable = true, length = -1)
    private String nameentity;
    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    private String comment;
    @Basic
    @Column(name = "active", nullable = true)
    private Boolean active;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    public String getNameentity() {
        return nameentity;
    }

    public void setNameentity(String nameentity) {
        this.nameentity = nameentity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

        LibEntity that = (LibEntity) o;

        if (id != that.id) return false;
        if (nameentity != null ? !nameentity.equals(that.nameentity) : that.nameentity != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameentity != null ? nameentity.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
