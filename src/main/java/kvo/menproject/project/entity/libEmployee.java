package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "lib_job_employee", schema = "library", catalog = "dev")
//@Data
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

    @OneToMany(mappedBy = "libJobEmployeeByPlaceEmployee")
    private Collection<LibJobPlace> libJobPlacesById;

    public libEmployee() {
    }

    public libEmployee(long id, String fio, String position, String coments, Boolean active, Collection<docProjectsList> projectsListsById, Collection<LibJobPlace> libJobPlacesById) {
        this.id = id;
        this.fio = fio;
        this.position = position;
        this.coments = coments;
        this.active = active;
        this.projectsListsById = projectsListsById;
        this.libJobPlacesById = libJobPlacesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        libEmployee employee = (libEmployee) o;

        if (id != employee.id) return false;
        if (!Objects.equals(fio, employee.fio)) return false;
        if (!Objects.equals(position, employee.position)) return false;
        if (!Objects.equals(coments, employee.coments)) return false;
        if (!Objects.equals(active, employee.active)) return false;
        if (!Objects.equals(projectsListsById, employee.projectsListsById))
            return false;
        return Objects.equals(libJobPlacesById, employee.libJobPlacesById);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (coments != null ? coments.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (projectsListsById != null ? projectsListsById.hashCode() : 0);
        result = 31 * result + (libJobPlacesById != null ? libJobPlacesById.hashCode() : 0);
        return result;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "libEmployee{" +
                "fio='" + fio + '\'' +
                ", position='" + position + '\'' +
                ", coments='" + coments + '\'' +
                ", active=" + active +
                '}';
    }

    public Collection<LibJobPlace> getLibJobPlacesById() {
        return libJobPlacesById;
    }

    public void setLibJobPlacesById(Collection<LibJobPlace> libJobPlacesById) {
        this.libJobPlacesById = libJobPlacesById;}

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

}
