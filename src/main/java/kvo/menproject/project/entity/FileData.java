package kvo.menproject.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;

@Data
@Entity
@Table(name = "bin_files", catalog = "dev")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "type_doc")
    private String typeDoc;
    @Basic
    private String name;
    @Basic
    @Column(name = "size_file")
    private String sizeFile;
    @Lob
    private byte[] data;
    @Basic
    @Column(name = "id_data", insertable = true, updatable = true)
    private Long idData;
    @OneToMany(mappedBy = "binFilesByBinfilesId")
    private Collection<docFaсtPayment> docFaсtPaymentsById;

    public Collection<docFaсtPayment> getDocFaсtPaymentsById() {
        return docFaсtPaymentsById;
    }
    public void setDocFaсtPaymentsById(Collection<docFaсtPayment> docFaсtPaymentsById) {
        this.docFaсtPaymentsById = docFaсtPaymentsById;
    }
    public String getSizeFile() {
        return sizeFile;
    }
    public void setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
    }
    public Long getId_Data() {
        return idData;
    }
    public void setId_Data(Long id_Data) {
        this.idData = id_Data;
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
    @Override
    public String toString() {
        return "FileData{" +
                "id=" + id +
                ", typeDoc='" + typeDoc + '\'' +
                ", name='" + name + '\'' +
                ", sizeFile='" + sizeFile + '\'' +
                ", data=" + Arrays.toString(data) +
                ", id_Data=" + idData +
                '}';
    }
}
