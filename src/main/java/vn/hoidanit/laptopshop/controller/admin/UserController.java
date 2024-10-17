package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/admin/user")
    public String getHomePage(Model model) {
        List<User> listUsers = this.userService.getAllUsers();
        model.addAttribute("TPT", "Thuy Phuoc Thinh");
        model.addAttribute("listUsers", listUsers);
        return "admin/user/index";
    }

    @GetMapping("/admin/user/create")
    public String getCreatePage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/post")
    public String postCreateUser(Model model,
            @ModelAttribute("newUser") @Valid User newUser,
            BindingResult bindingResult,
            @RequestParam("avatarFile") MultipartFile file) {
        // validation
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage() + " - " + error.getRejectedValue());
            }
            return "admin/user/create";
        }
        // upload, hash
        String avatar = uploadService.handleSaveFileUpload(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(newUser.getPassword());
        // auto map => role => role_id in database
        newUser.setRole(this.userService.getRoleByName(newUser.getRole().getName()));
        newUser.setAvatar(avatar);
        newUser.setPassword(hashPassword);
        this.userService.handleSaveUser(newUser);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/view/{id}")
    public String getUserById(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("userName", user.getFullName());
        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/user/delete/{id}")
    public String deleteUserById(Model model, @PathVariable long id) {
        this.userService.deleteUserByid(id);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/edit/{id}")
    public String getEditUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        System.out.println("user " + user);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping(value = "/admin/user/edit")
    public String editUser(Model model, @ModelAttribute("user") User user) {
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            this.userService.handleSaveUser(currentUser);
            return "redirect:/admin/user";
        }
        return "redirect:/error";
    }
}
