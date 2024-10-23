package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Data
@Entity
@Table(name = "bin_files", catalog = "dev")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    private String name;
    @Basic
    @Column(name = "size_file")
    private String sizeFile;
    @Lob
    private byte[] data;
    @Basic
    @Column(name = "id_data", insertable = false, updatable = false)
    private Long id_Data;
    @ManyToOne
    @JoinColumn(name = "id_data", referencedColumnName = "id", nullable = true)
    private docSchemaDoc linkDataDocSchemaDocById;

    public String getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
    }

    public Long getId_Data() {
        return id_Data;
    }

    public void setId_Data(Long id_Data) {
        this.id_Data = id_Data;
    }

    public docSchemaDoc getLinkDataDocSchemaDocById() {
        return linkDataDocSchemaDocById;
    }

    public void setLinkDataDocSchemaDocById(docSchemaDoc linkDataDocSchemaDocById) {
        this.linkDataDocSchemaDocById = linkDataDocSchemaDocById;
    }

    public FileData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
