package ua.trx.backend.server.control;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.trx.backend.Service.UserGlobalDB;
import ua.trx.backend.Service.UserServiceDB;
import ua.trx.backend.pidor.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // временно, можно ограничить IP фронта
public class ControllerServer {

    private final UserServiceDB userService;

    public ControllerServer(UserServiceDB userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.register(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(Map.of("message", "Registration successful"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (userService.authenticateUser(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
        }
    }
}
