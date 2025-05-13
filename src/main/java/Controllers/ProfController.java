package Controllers;

import Beans.Prof;
import Services.ProfService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profs")
public class ProfController {

    @Autowired
    private ProfService profService;

    @GetMapping
    public List<Prof> getAllProfs() {
        return profService.getAllProfs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prof> getProfById(@PathVariable("id") Long id) {
        try {
            Prof prof = profService.getProfById(id);
            return ResponseEntity.ok(prof);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Prof> getProfByEmail(@PathVariable("email") String email) {
        Prof prof = profService.findProfByEmail(email);
        if (prof != null) {
            return ResponseEntity.ok(prof);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Prof> addProf(@Valid @RequestBody Prof prof, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Prof createdProf = profService.addProf(prof);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prof> updateProf(@PathVariable("id") Long id, @Valid @RequestBody Prof prof) {
        try {
            Prof updatedProf = profService.updateProf(id, prof);
            return ResponseEntity.ok(updatedProf);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProf(@PathVariable("id") Long id) {
        try {
            profService.deleteProf(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}