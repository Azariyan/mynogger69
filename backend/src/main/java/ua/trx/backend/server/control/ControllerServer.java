package ua.trx.backend.server.control;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.trx.backend.Service.UserServiceDB;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/clicker")

public class ControllerServer {

    @Autowired
    private UserServiceDB userService;

    @ResponseBody
    @GetMapping("")
    public String main(Model model, HttpSession session) {
        model.addAttribute("loggedIn", session.getAttribute("username") != null);
        return "main";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        if (userService.authenticateUser(username, password)) {
            session.setAttribute("username", username);
            redirectAttributes.addFlashAttribute("loggedIn", true);
            return "redirect:/clicker";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            model.addAttribute("showLoginModal", true);
            model.addAttribute("loggedIn", false);
            return "main";
        }
    }

    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        if (userService.register(username, password)) {
            session.setAttribute("username", username);
            redirectAttributes.addFlashAttribute("message", "Registration successful.");
            return "redirect:/clicker";
        } else {
            model.addAttribute("error", "Username already exists.");
            model.addAttribute("loggedIn", false);
            return "main";
        }
    }
}
