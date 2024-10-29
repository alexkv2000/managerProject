package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "lib_step_project", schema = "library", catalog = "dev")
@Data
public class libStepProject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name_step_project", nullable = true, length = -1)
    private String nameStepProject;
    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    private String comment;
    @OneToMany(mappedBy = "libStepProjectByStepProject")
    private Collection<binStorage> binProjectsById;

    public Collection<binStorage> getBinProjectsById() {
        return binProjectsById;
    }

    public void setBinProjectsById(Collection<binStorage> binProjectsById) {
        this.binProjectsById = binProjectsById;
    }

    public String getNameStepProject() {
        return nameStepProject;
    }

    public void setNameStepProject(String nameStepProject) {
        this.nameStepProject = nameStepProject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

        libStepProject that = (libStepProject) o;

        if (id != that.id) return false;
        if (nameStepProject != null ? !nameStepProject.equals(that.nameStepProject) : that.nameStepProject != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameStepProject != null ? nameStepProject.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
