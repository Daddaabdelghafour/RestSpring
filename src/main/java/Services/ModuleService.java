package Services;

import Beans.Module;
import Repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module getModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found with id: " + id));
    }

    @Transactional
    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }

    @Transactional
    public Module updateModule(Long id, Module updatedModule) {
        if (!moduleRepository.existsById(id)) {
            throw new RuntimeException("Module not found with id: " + id);
        }
        updatedModule.setId(id);
        return moduleRepository.save(updatedModule);
    }

    @Transactional
    public void deleteModule(Long id) {
        if (!moduleRepository.existsById(id)) {
            throw new RuntimeException("Module not found with id: " + id);
        }
        moduleRepository.deleteById(id);
    }

    public List<Module> findModulesByName(String name) {
        return moduleRepository.findByName(name);
    }

    public List<Module> findModulesByProfessor(Long profId) {
        return moduleRepository.findByProfId(profId);
    }
}