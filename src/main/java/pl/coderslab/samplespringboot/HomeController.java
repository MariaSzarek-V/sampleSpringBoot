package pl.coderslab.samplespringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private TaboretService  taboretService;

    public HomeController(TaboretService taboretService) {
        this.taboretService = taboretService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/taborets")
    public List<Taboret> taborets(){
        return taboretService.findAll();
    }
}