package kvo.menproject.project.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Data
@Repository
public class docProjectListDTO {
    private String name;
    private String headManager;
    private String responsibleFromBusiness;
    private String curator;
    private boolean closed;
    private String dogovorNumber;
    private String linkDogovor;
    private LocalDate dateCreate;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private BigDecimal summaProject;
    private BigDecimal opex;
    private BigDecimal opexNds;
    private BigDecimal capex;
    private BigDecimal capexNds;
    private String comment;
    private String namedivision;
    private String fio; // Для libEmployeeByOwner fio


}
