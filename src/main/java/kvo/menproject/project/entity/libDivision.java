package kvo.menproject.project.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "lib_division", schema = "library", catalog = "dev")
@Data
public class libDivision {
    public static final String TABLE_NAME = "libDivision";
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name_division", nullable = true, length = -1)
    private String namedivision;
    @Basic
    @Column(name = "organisation", nullable = true, length = -1)
    private String organisation;
    @Basic
    @Column(name = "active", nullable = true)
    private Boolean active;
    @OneToMany(mappedBy = "libDivisionByIdDivision")
    private Collection<docProjectsList> projectsListsById;
    @OneToMany(mappedBy = "linkDivisionByIdDivision")
    private Collection<docSchemaDoc> libSchemaDocsById;
    @OneToMany(mappedBy = "libDivisionByProjectId")
    private Collection<docPlanPayProject> planPayProjectsById;

    public Collection<docPlanPayProject> getPlanPayProjectsById() {
        return planPayProjectsById;
    }

    public void setPlanPayProjectsById(Collection<docPlanPayProject> planPayProjectsById) {
        this.planPayProjectsById = planPayProjectsById;
    }
    public Collection<docSchemaDoc> getLibSchemaDocsById() {
        return libSchemaDocsById;
    }

    public void setLibSchemaDocsById(Collection<docSchemaDoc> libSchemaDocsById) {
        this.libSchemaDocsById = libSchemaDocsById;
    }

    public Collection<docProjectsList> getProjectsListsById() {
        return projectsListsById;
    }

    public void setProjectsListsById(Collection<docProjectsList> projectsListsById) {
        this.projectsListsById = projectsListsById;
    }

    public String getNamedivision() {
        return namedivision;
    }

    public void setNamedivision(String nameDivision) {
        this.namedivision = nameDivision;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
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

        libDivision that = (libDivision) o;

        if (id != that.id) return false;
        if (namedivision != null ? !namedivision.equals(that.namedivision) : that.namedivision != null) return false;
        if (organisation != null ? !organisation.equals(that.organisation) : that.organisation != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = namedivision != null ? namedivision.hashCode() : 0;
        result = 31 * result + (organisation != null ? organisation.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

}
