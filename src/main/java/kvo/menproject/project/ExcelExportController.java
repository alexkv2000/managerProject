package kvo.menproject.project;

import kvo.menproject.project.entity.docPlanPayProject;
import kvo.menproject.project.entity.docPlanPayProjectRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jaxen.function.StringFunction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExcelExportController {
    private final docPlanPayProjectRepository docPlanPayProjectRepo;

    public ExcelExportController(docPlanPayProjectRepository docPlanPayProjectRepo) {
        this.docPlanPayProjectRepo = docPlanPayProjectRepo;
    }

    @GetMapping("/planpayproject/{id}")
    public ResponseEntity<Void> exportToExcel(@PathVariable("id") Long fileId, HttpServletResponse response) throws IOException {
        // Установка заголовка для Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=planPlayProject.xlsx");

        // Создание рабочей книги и листа
        Workbook workbook = new XSSFWorkbook();
        Sheet planPay = workbook.createSheet("PlanPay");

        CellStyle style = workbook.createCellStyle(); //Create new style
        style.setWrapText(true); //Set wordwrap
        planPay.setColumnWidth(0, 870);
        planPay.setColumnWidth(7, 18000);
        planPay.setColumnWidth(12, 18000);
        planPay.setColumnWidth(13, 18000);
        planPay.setColumnWidth(14, 18000);
        List<docPlanPayProject> docPlanPayProjects = docPlanPayProjectRepo.findAllByDocProjectsListByPlanProject_Id(fileId);

        // Пример данных для заполнения таблицы
        String[] headers = {"Number", "plan_year", "data_planing", "opex", "opex_nds", "capex", "capex_nds", "step_project", "duration", "interval", "completed", "payment_on_time", "division_id", "comment", "name_project"};
        // Заполнение заголовков
        Row headerRow = planPay.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int j = 0;
        // Заполнение данными
        for (int i = 0; i < docPlanPayProjects.size(); i++) {
            Row row = planPay.createRow(i + 1);

            Cell cellId = row.createCell(j++);
            cellId.setCellValue(i + 1);
            Cell cellPlanYear = row.createCell(j++);
            cellPlanYear.setCellValue(docPlanPayProjects.get(i).getPlanYear());
            Cell cellDataPlaning = row.createCell(j++);
            cellDataPlaning.setCellValue(docPlanPayProjects.get(i).getDataPlaning());
            Cell cellOpex = row.createCell(j++);
            cellOpex.setCellValue(docPlanPayProjects.get(i).getOpex().toString());
            Cell cellOpexNds = row.createCell(j++);
            cellOpexNds.setCellValue(docPlanPayProjects.get(i).getOpexNds().toString());
            Cell cellCapex = row.createCell(j++);
            cellCapex.setCellValue(docPlanPayProjects.get(i).getCapex().toString());
            Cell cellCapexNds = row.createCell(j++);
            cellCapexNds.setCellValue(docPlanPayProjects.get(i).getCapexNds().toString());
            Cell cellStepProject = row.createCell(j++);
            cellStepProject.setCellStyle(style); //Apply style to cell
            cellStepProject.setCellValue(docPlanPayProjects.get(i).getStepProject());
            Cell cellDuration = row.createCell(j++);
            cellDuration.setCellValue(docPlanPayProjects.get(i).getDuration());
            Cell cellInterval = row.createCell(j++);
            cellInterval.setCellValue(docPlanPayProjects.get(i).getInterval());
            Cell cellCompleted = row.createCell(j++);
            cellCompleted.setCellValue(docPlanPayProjects.get(i).getCompleted().toString());
            Cell cellPaymentOnTime = row.createCell(j++);
            cellPaymentOnTime.setCellValue(docPlanPayProjects.get(i).getPaymentOnTime().toString());
            Cell cellDivision = row.createCell(j++);
            cellDivision.setCellStyle(style); //Apply style to cell
            cellDivision.setCellValue(docPlanPayProjects.get(i).getLibDivisionByProjectId().getNamedivision());
            Cell cellComment = row.createCell(j++);
            cellComment.setCellStyle(style); //Apply style to cell
            cellComment.setCellValue(docPlanPayProjects.get(i).getComment());
            Cell cellPlanPay = row.createCell(j++);
            cellPlanYear.setCellStyle(style); //Apply style to cell
            cellPlanPay.setCellValue(docPlanPayProjects.get(i).getDocProjectsListByPlanProject().getName());
//            Cell cellPlan = row.createCell(j++);
//            cellPlan.setCellValue(docPlanPayProjects.get(i).getDocProjectsListByPlanProject().toString());
            j = 0;
        }



        Sheet factPay = workbook.createSheet("Project");
        factPay.setColumnWidth(0, 870);
        factPay.setColumnWidth(1, 18000);
        factPay.setColumnWidth(8, 18000);
        factPay.setColumnWidth(9, 18000);
        factPay.setColumnWidth(11, 18000);
        factPay.setColumnWidth(13, 18000);

        // Пример данных для заполнения таблицы
        String[] headersFakt = {"№ п/п", "Name", "Data_start", "Date_end", "Opex", "Opex_NDS", "Capex", "Capex_nds", "Dogovor_numbers", "Head_manager", "Summa_project", "Link_dogovor", "Closed", "Сomment"};
        // Заполнение заголовков
        Row headerRowFact = factPay.createRow(0);
        for (int i = 0; i < headersFakt.length; i++) {
            Cell cell = headerRowFact.createCell(i);
            cell.setCellValue(headersFakt[i]);
        }
//TODO вывод factPay данных
        fileId.longValue();
        long ipp_long = docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getId();
        int ipp = (int) ipp_long;
        int i=0;
        j = 0;
        // Заполнение данными
        if (!docPlanPayProjects.isEmpty()) {
            Row row = factPay.createRow(1);

            Cell cellId = row.createCell(j++);
            cellId.setCellValue(i + 1);

            Cell cellPlanYear = row.createCell(j++);
            cellPlanYear.setCellStyle(style); //Apply style to cell
            cellPlanYear.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getName());
            Cell cellDataPlaning = row.createCell(j++);
            cellDataPlaning.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getDateStart());
            Cell cellOpex = row.createCell(j++);
            cellOpex.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getDateEnd());
            Cell cellOpexNds = row.createCell(j++);
            cellOpexNds.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getOpex());
            Cell cellCapex = row.createCell(j++);
            cellCapex.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getOpexNds());
            Cell cellCapexNds = row.createCell(j++);
            cellCapexNds.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getCapex());
            Cell cellStepProject = row.createCell(j++);
            cellStepProject.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getCapexNds());
            Cell cellDuration = row.createCell(j++);
            cellDuration.setCellStyle(style); //Apply style to cell
            cellDuration.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getDogovorNumber());
            Cell cellInterval = row.createCell(j++);
            cellInterval.setCellStyle(style); //Apply style to cell
            cellInterval.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getHeadManager());
            Cell cellCompleted = row.createCell(j++);
            cellCompleted.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getSummaProject());
            Cell cellPaymentOnTime = row.createCell(j++);
            cellPaymentOnTime.setCellStyle(style); //Apply style to cell
            cellPaymentOnTime.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getLinkDogovor());
            Cell cellDivision = row.createCell(j++);
            cellDivision.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().isClosed());
            Cell cellComment = row.createCell(j++);
            cellComment.setCellStyle(style); //Apply style to cell
            cellComment.setCellValue(docPlanPayProjects.get(0).getDocProjectsListByPlanProject().getComment());
        }

        // Запись в ответ
        workbook.write(response.getOutputStream());
        workbook.close();

        return ResponseEntity.ok().build();
    }
}