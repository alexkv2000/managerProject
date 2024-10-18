package kvo.menproject.project.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Component
public class binfileController{
    public final binfileRepository binfileRepo;

    public binfileController(binfileRepository binfileRepo) {
        this.binfileRepo = binfileRepo;
    }
    public Optional<binfile> viewbinfile(Long id) {


        Optional<binfile> rows = binfileRepo.findById(id);
        if (rows != null) {
            System.out.println("Retrieved " + rows.stream().count() + " Bin files");
        } else {
            System.out.println("No Bin filess");
        }
        return rows;
    }
}
