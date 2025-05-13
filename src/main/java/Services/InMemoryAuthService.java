package Services;

import Beans.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InMemoryAuthService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Map qui stocke les informations de session par token
    private final Map<String, AdminSession> sessions = new HashMap<>();

    // Dans votre classe InMemoryAuthService, modifiez AdminSession
    private static class AdminSession {
        private final long adminId;
        private final String username;
        private final long creationTime;

        public AdminSession(long adminId, String username) {
            this.adminId = adminId;
            this.username = username;
            this.creationTime = System.currentTimeMillis();
        }

        public long getCreationTime() {
            return creationTime;
        }
    }

    public Map<String, Object> authenticateAdmin(String username, String plainPassword) {
        Admin admin = adminService.findAdminByUsername(username);
        Map<String, Object> response = new HashMap<>();

        if (admin == null) {
            response.put("success", false);
            response.put("message", "Identifiants incorrects");
            return response;
        }

        // Vérifier le mot de passe sans le récupérer
        if (adminService.verifyPassword(admin, plainPassword)) {
            // Générer un token pour la session
            String token = UUID.randomUUID().toString();

            // Stocker les informations essentielles en mémoire
            sessions.put(token, new AdminSession(admin.getId(), admin.getUsername()));

            response.put("success", true);
            response.put("message", "Connexion réussie");
            response.put("token", token);
            response.put("adminId", admin.getId());
            response.put("username", admin.getUsername());
            return response;
        } else {
            response.put("success", false);
            response.put("message", "Identifiants incorrects");
            return response;
        }
    }

    // Ajouter cette méthode à votre classe InMemoryAuthService
    public boolean validateToken(String token) {
        if (!sessions.containsKey(token)) {
            return false;
        }

        // Vérification de l'expiration (30 minutes)
        long currentTime = System.currentTimeMillis();
        AdminSession session = sessions.get(token);
        long sessionAge = currentTime - session.creationTime;

        // Si la session a plus de 30 minutes
        if (sessionAge > 30 * 60 * 1000) {
            sessions.remove(token);
            return false;
        }

        return true;
    }

    public void logout(String token) {
        sessions.remove(token);
    }

    // Méthode pour récupérer l'ID admin à partir d'un token
    public Long getAdminIdFromToken(String token) {
        AdminSession session = sessions.get(token);
        return session != null ? session.adminId : null;
    }

}