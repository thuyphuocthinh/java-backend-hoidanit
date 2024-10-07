package vn.hoidanit.laptopshop.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;

@RestController
public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @GetMapping("/")
    public String getHomePage() {
        return "HomePage" + " " + userService.handleHello();
    }
}
