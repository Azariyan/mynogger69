package ua.trx.backend;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component

public class Webapp {

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
