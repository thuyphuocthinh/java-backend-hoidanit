package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> listUsers = this.userService.getAllUsers();
        model.addAttribute("TPT", "Thuy Phuoc Thinh");
        model.addAttribute("listUsers", listUsers);
        System.out.println("listUsers: " + listUsers);
        return "hello";
    }

    @RequestMapping("/test")
    public String getTest() {
        return "test";
    }

    @RequestMapping("/admin/create")
    public String getCreatePage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping(value = "/admin/user/post", method = RequestMethod.POST)
    public String postCreateUser(Model model, @ModelAttribute("newUser") User newUser) {
        System.err.println(newUser.toString());
        this.userService.handleSaveUser(newUser);
        return "redirect:/";
    }
}
