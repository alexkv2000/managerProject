package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "lib_job_employee", schema = "library", catalog = "dev")
@Data
public class libEmployee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "fio", nullable = true, length = -1)
    private String fio;
    @Basic
    @Column(name = "position", nullable = true, length = -1)
    private String position;
    @Basic
    @Column(name = "coments", nullable = true, length = -1)
    private String coments;
    @Basic
    @Column(name = "active", nullable = true)
    private Boolean active;
    @OneToMany(mappedBy = "libJobEmployeeByOwner")
    private Collection<docProjectsList> projectsListsById;

    public Collection<docProjectsList> getProjectsListsById() {
        return projectsListsById;
    }

    public void setProjectsListsById(Collection<docProjectsList> projectsListsById) {
        this.projectsListsById = projectsListsById;
    }
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String coments) {
        this.coments = coments;
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

        libEmployee that = (libEmployee) o;

        if (id != that.id) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (coments != null ? !coments.equals(that.coments) : that.coments != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fio != null ? fio.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (coments != null ? coments.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
