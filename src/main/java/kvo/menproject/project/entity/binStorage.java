package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "bin_storage", schema = "public", catalog = "dev")
public class binStorage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name_project", nullable = false, insertable=false, updatable=false)
    private long nameProject;
    @Basic
    @Column(name = "step_project", nullable = true, insertable=false, updatable=false)
    private long stepProject;
    @Basic
    @Column(name = "name_file", nullable = true, length = 255)
    private String nameFile;
    @Basic
    @Column(name = "size_file", nullable = true, length = 255)
    private String sizeFile;

    @Column(name = "date_create", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    private String comments;
    @ManyToOne
    @JoinColumn(name = "name_project", referencedColumnName = "id", nullable = false)
    private docProjectsList projectsListByNameProject;
    @ManyToOne
    @JoinColumn(name = "step_project", referencedColumnName = "id")
    private libStepProject libStepProjectByStepProject;
    @OneToMany(mappedBy = "binStorageByIdData")
    private Collection<FileData> binFilesById;

    public Collection<FileData> getBinFilesById() {
        return binFilesById;
    }

    public void setBinFilesById(Collection<FileData> binFilesById) {
        this.binFilesById = binFilesById;
    }

    public docProjectsList getProjectsListByNameProject() {
        return projectsListByNameProject;
    }

    public void setProjectsListByNameProject(docProjectsList projectsListByNameProject) {
        this.projectsListByNameProject = projectsListByNameProject;
    }

    public libStepProject getLibStepProjectByStepProject() {
        return libStepProjectByStepProject;
    }

    public void setLibStepProjectByStepProject(libStepProject libStepProjectByStepProject) {
        this.libStepProjectByStepProject = libStepProjectByStepProject;
    }

    public binStorage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNameProject() {
        return nameProject;
    }

    public void setNameProject(long nameProject) {
        this.nameProject = nameProject;
    }

    public Long getStepProject() {
        return stepProject;
    }

    public void setStepProject(Long stepProject) {
        this.stepProject = stepProject;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



}
