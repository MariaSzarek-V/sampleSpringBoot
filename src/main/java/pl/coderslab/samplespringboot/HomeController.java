package pl.coderslab.samplespringboot;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class HomeController {


    private final CartoonRepository cartoonRepository;


    private String courseName;
    private TaboretService taboretService;


    public HomeController(CartoonRepository cartoonRepository, TaboretService taboretService) {
        this.cartoonRepository = cartoonRepository;
        this.taboretService = taboretService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/taborets")
    public List<Taboret> taborets() {
        return taboretService.findAll();
    }

    //response entity
    @GetMapping("/taborets/all")
    public ResponseEntity<List<Taboret>> taboretsAll() {
        List<Taboret> all = taboretService.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(all); // 200
    }

//    @GetMapping("/taborets/{id}")
//    public ResponseEntity<Taboret> getById(@PathVariable Long id) {
//        return ResponseEntity.of(taboretService.findById(id)); // Optional<Taboret>
//    }

    @GetMapping("/taborets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Taboret getById(@PathVariable Long id) {
        return taboretService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Taboret " + id + " not found"));
    }

    @PostMapping("/s1")
    public ResponseEntity<String> statusWithResponseEntity9() {
        throw new RuntimeException("some exception");
    }

    @GetMapping("/cartoon/{id}")
    public Cartoon find(@PathVariable Long id) {
        return cartoonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("cartoon %d not found", id)));
    }

    @GetMapping("/cartoon-err/{id}")
    public Cartoon findCartoon(@PathVariable Long id) {
        return cartoonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }


    @GetMapping("/cartoon-err-code/{id}")
    public Cartoon findCartoonCode(@PathVariable Long id) {
        return cartoonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found", "MY_ERROR_CODE"));


    }
}
