package library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/librarian")
    public String getLibrarianHomePage() {
        return "forward:/static/librarianHome.html";
    }

    @GetMapping("/reader")
    public String getReaderHomePage() {
        return "forward:/static/readerHome.html";
    }
}
