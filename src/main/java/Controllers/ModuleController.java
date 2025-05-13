package Controllers;

import Beans.Module;
import Services.ModuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.getAllModules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable("id") Long id) {
        try {
            Module module = moduleService.getModuleById(id);
            return ResponseEntity.ok(module);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/prof/{profId}")
    public List<Module> getModulesByProfessor(@PathVariable("profId") Long profId) {
        return moduleService.findModulesByProfessor(profId);
    }

    @PostMapping
    public ResponseEntity<Module> addModule(@Valid @RequestBody Module module, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Module createdModule = moduleService.addModule(module);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable("id") Long id, @Valid @RequestBody Module module) {
        try {
            Module updatedModule = moduleService.updateModule(id, module);
            return ResponseEntity.ok(updatedModule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable("id") Long id) {
        try {
            moduleService.deleteModule(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}