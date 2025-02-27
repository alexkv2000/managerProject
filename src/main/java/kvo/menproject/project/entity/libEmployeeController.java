package kvo.menproject.project.entity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class libEmployeeController implements CommandLineRunner {
    private final libEmployeeRepository libEmployeeRepo;

    public libEmployeeController(libEmployeeRepository libEmployeeRepo) {
        this.libEmployeeRepo = libEmployeeRepo;
    }

    @GetMapping("/employee")
    public String viewHomePage(HttpServletRequest request, Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size) {
//        List<serLibDivision> rows = jdbcTemplate.query("select id, \"nameDivision\", organisation, active from library.\"libDivision\" where active = true order by id", BeanPropertyRowMapper.newInstance(serLibDivision.class));
//        for (serLibDivision q: rows) {
//            System.out.println(q.toString());
//        }
        Pageable pageable = PageRequest.of(page, size);
        Page<libEmployee> rows = libEmployeeRepo.findAllByActiveIsTrue(pageable);
        if (rows != null) {
            System.out.println("Retrieved " + rows.getContent().size() + " employee");
        } else {
            System.out.println("No employee retrieved");
        }
        model.addAttribute("employees", libEmployeeRepo.findAllByActiveIsTrue(pageable));
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("request", request);

        return "/employee/mainemployee";
    }

    @GetMapping(path = "/addemployee")
    public String addNewEmployee(Model model) {
        libEmployee employee = new libEmployee();
        model.addAttribute("employee", employee);
        return "/employee/newemployee";
    }

    @PostMapping(path = "/saveemployee")
    public String saveEmployee (@ModelAttribute("employee") libEmployee employee) {
        if (!employee.getFio().isEmpty() || employee.getFio().trim() != "" || !employee.getFio().isBlank()) {
            if (employee.getActive() == null) employee.setActive(true);
            libEmployeeRepo.saveAndFlush(employee);
        }

        return "redirect:/employee";
    }

    @GetMapping(path ="/showemployee/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        libEmployee employee = libEmployeeRepo.getById(id);
        model.addAttribute("employee", employee);
        return "/employee/showemployee";
    }

    @GetMapping("/deleteemployee/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        libEmployeeRepo.deleteById(id);
        return "redirect:/employee";

    }
    @Override
    public void run(String... args) {
    }
}
