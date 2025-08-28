package pl.coderslab.samplespringboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/taborets")
public class TaboretController {
    private final TaboretService taboretService;

    public TaboretController(TaboretService taboretService) {
        this.taboretService = taboretService;
    }

    @GetMapping
    public List<Taboret> taborets(){
        return taboretService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Taboret create(@Valid @RequestBody Taboret toCreate) {
        return taboretService.save(toCreate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taboret> getUser(@PathVariable Long id) {
        Optional<Taboret> byId = taboretService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}