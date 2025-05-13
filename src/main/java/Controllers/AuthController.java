package Controllers;

import Beans.Admin;
import Services.AdminService;
import Services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Admin admin = adminService.findAdminByUsername(username);

        if (admin == null || !adminService.verifyPassword(admin, password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Identifiants incorrects");
            return ResponseEntity.status(401).body(response);
        }

        String token = jwtService.generateToken(admin);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("token", token);
        response.put("adminId", admin.getId());
        response.put("username", admin.getUsername());

        return ResponseEntity.ok(response);
    }
}