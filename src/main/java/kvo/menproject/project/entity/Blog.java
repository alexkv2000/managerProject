package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data

@Entity
@Table(name = "doc_blog", schema = "public", catalog = "dev")
public class Blog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "title", nullable = true, length = -1)
    private String title;
    @Basic
    @Column(name = "content", nullable = true, length = -1)
    private String content;
    //    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_create", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dcreate;
    @Basic
    @Column(name = "show", nullable = false)
    private Boolean show;

    public Blog() {
    }

}
