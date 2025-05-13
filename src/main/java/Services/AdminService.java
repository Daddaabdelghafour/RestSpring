package Services;

import Beans.Admin;
import Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Transactional
    public Admin addAdmin(Admin admin) {
        // Encode le mot de passe avant de sauvegarder
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Transactional
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with id: " + id);
        }

        Admin existingAdmin = adminRepository.findById(id).orElseThrow();

        // Si un nouveau mot de passe est fourni, l'encoder
        if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
            updatedAdmin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
        } else {
            updatedAdmin.setPassword(existingAdmin.getPassword());
        }

        updatedAdmin.setId(id);
        return adminRepository.save(updatedAdmin);
    }

    @Transactional
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }

    public Admin findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Admin findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public boolean verifyPassword(Admin admin, String rawPassword) {
        return passwordEncoder.matches(rawPassword, admin.getPassword());
    }
}