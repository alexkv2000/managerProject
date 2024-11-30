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
            cellDivision.setCellValue(docPlanPayProjects.get(i).getLibDivisionByProjectId().getNamedivision());
            Cell cellComment = row.createCell(j++);
            cellComment.setCellValue(docPlanPayProjects.get(i).getComment());
            Cell cellPlanPay = row.createCell(j++);
            cellPlanPay.setCellValue(docPlanPayProjects.get(i).getDocProjectsListByPlanProject().getName());
//            Cell cellPlan = row.createCell(j++);
//            cellPlan.setCellValue(docPlanPayProjects.get(i).getDocProjectsListByPlanProject().toString());
            j = 0;
        }

        Sheet factPay = workbook.createSheet("Project");

        // Пример данных для заполнения таблицы
        String[] headersFakt = {"№ п/п", "Name", "Data_start", "Date_end", "Opex", "Opex_NDS", "Capex", "Capex_nds", "Dogovor_numbers", "Head_manager", "Summa_project", "Link_dogovor", "Closed", "Сomment"};
        // Заполнение заголовков
        Row headerRowFact = factPay.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRowFact.createCell(i);
            cell.setCellValue(headers[i]);
        }
//TODO вывод factPay данных
        int ipp = Math.toIntExact(fileId);
        int i=0;
        j = 0;
        // Заполнение данными
        if (!docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getName().isEmpty()) {
            Row row = factPay.createRow(1);

            Cell cellId = row.createCell(j++);
            cellId.setCellValue(i + 1);

            Cell cellPlanYear = row.createCell(j++);
            cellPlanYear.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getName());

            Cell cellDataPlaning = row.createCell(j++);
            cellDataPlaning.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getDateStart());
            Cell cellOpex = row.createCell(j++);
            cellOpex.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getDateEnd());
            Cell cellOpexNds = row.createCell(j++);
            cellOpexNds.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getOpex());
            Cell cellCapex = row.createCell(j++);
            cellCapex.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getOpexNds());
            Cell cellCapexNds = row.createCell(j++);
            cellCapexNds.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getCapex());
            Cell cellStepProject = row.createCell(j++);
            cellStepProject.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getCapexNds());
            Cell cellDuration = row.createCell(j++);
            cellDuration.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getDogovorNumber());
            Cell cellInterval = row.createCell(j++);
            cellInterval.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getHeadManager());
            Cell cellCompleted = row.createCell(j++);
            cellCompleted.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getSummaProject());
            Cell cellPaymentOnTime = row.createCell(j++);
            cellPaymentOnTime.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getLinkDogovor());
            Cell cellDivision = row.createCell(j++);
            cellDivision.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().isClosed());
            Cell cellComment = row.createCell(j++);
            cellComment.setCellValue(docPlanPayProjects.get(ipp).getDocProjectsListByPlanProject().getComment());
        }

        // Запись в ответ
        workbook.write(response.getOutputStream());
        workbook.close();

        return ResponseEntity.ok().build();
    }
}