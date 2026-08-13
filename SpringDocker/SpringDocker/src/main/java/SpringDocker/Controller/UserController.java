package SpringDocker.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String says() {
        return "Hello from Spring Docker";
    }
}docker image ls spring-docker