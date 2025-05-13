package Services;

import Beans.Prof;
import Repositories.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfService {

    @Autowired
    private ProfRepository profRepository;

    public List<Prof> getAllProfs() {
        return profRepository.findAll();
    }

    public Prof getProfById(Long id) {
        return profRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found with id: " + id));
    }

    @Transactional
    public Prof addProf(Prof prof) {
        return profRepository.save(prof);
    }

    @Transactional
    public Prof updateProf(Long id, Prof updatedProf) {
        if (!profRepository.existsById(id)) {
            throw new RuntimeException("Professor not found with id: " + id);
        }
        updatedProf.setId(id);
        return profRepository.save(updatedProf);
    }

    @Transactional
    public void deleteProf(Long id) {
        if (!profRepository.existsById(id)) {
            throw new RuntimeException("Professor not found with id: " + id);
        }
        profRepository.deleteById(id);
    }

    public Prof findProfByEmail(String email) {
        return profRepository.findByEmail(email);
    }

    public List<Prof> findProfsByFirstName(String firstName) {
        return profRepository.findByFirstName(firstName);
    }

    public List<Prof> findProfsByLastName(String lastName) {
        return profRepository.findByLastName(lastName);
    }
}