package Services;

import Beans.Admin;
import Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Transactional
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Transactional
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with id: " + id);
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
}