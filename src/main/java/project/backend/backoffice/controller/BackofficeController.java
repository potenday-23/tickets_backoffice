// package
package project.backend.backoffice.controller;

// Modules
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


// Main Section
@Controller
@EnableWebMvc
@RequestMapping("/backoffice")
@RequiredArgsConstructor
public class BackofficeController {
    @GetMapping
    public String home() {
        return "home/home";
    }

}
