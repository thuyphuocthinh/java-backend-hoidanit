package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;

@Controller
public class UserController {

    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("TPT", "Thuy Phuoc Thinh");
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
        return "hello";
    }
}
