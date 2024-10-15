package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.domain.Product;

@Controller
public class ProductController {
    @GetMapping(value = "/admin/product")
    public String getProductPage() {
        return "/admin/product/index";
    }

    @GetMapping(value = "/admin/product/create") 
    public String getProductCreate(Model model) {
        model.addAttribute("newProduct", new Product());
        return  "/admin/product/create";
    }
}
