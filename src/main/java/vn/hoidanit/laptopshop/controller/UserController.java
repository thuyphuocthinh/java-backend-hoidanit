package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        this.userService.handleSaveUser(newUser);
        return "redirect:/";
    }

    @RequestMapping(value = "/user/view/{id}")
    public String getUserById(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("userName", user.getFullName());
        return "/admin/user/detail";
    }

    @RequestMapping(value = "/user/delete/{id}")
    public String deleteUserById(Model model, @PathVariable long id) {
        this.userService.deleteUserByid(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/user/edit/{id}")
    public String getEditUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        System.out.println("user " + user);
        model.addAttribute("user", user);
        return "/admin/user/edit";
    }

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.POST)
    public String editUser(Model model, @ModelAttribute("user") User user) {
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            this.userService.handleSaveUser(currentUser);
            return "redirect:/";
        }
        return "redirect:/error";
    }
}
