package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping(value = "/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("listProducts", products);
        return "/admin/product/index";
    }

    @GetMapping(value = "/admin/product/create")
    public String getProductCreate(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/admin/product/create";
    }

    @PostMapping(value = "/admin/product/post")
    public String postCreateProduct(Model model,
            @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile file) {
        // validation
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(
                        error.getField() + " - " + error.getDefaultMessage() + " - " + error.getRejectedValue());
            }
            return "/admin/product/create";
        }
        // upload
        String image = uploadService.handleSaveFileUpload(file, "product");
        newProduct.setImage(image);
        productService.handleSaveProduct(newProduct);
        return "redirect:/admin/product";
    }

    @GetMapping(value = "/admin/product/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        this.productService.deleteProductById(id);
        return "redirect:/admin/product";
    }

    @GetMapping(value = "/admin/product/edit/{id}")
    public String getProductEditPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "/admin/product/edit";
    }

    @PostMapping(value = "/admin/product/edit")
    public String editUser(Model model,
            @ModelAttribute("product") @Valid Product product,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "/admin/product/edit";
        }
        Product currentProduct = this.productService.getProductById(product.getId());
        if (currentProduct != null) {
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getDetailDesc());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setQuantity(product.getQuantity());
            if(!file.isEmpty()) {
                String image = uploadService.handleSaveFileUpload(file, "product");
                currentProduct.setImage(image);
            } else {
                currentProduct.setImage(product.getImage());
            }
            this.productService.updateProduct(currentProduct);
            return "redirect:/admin/product";
        }
        return "redirect:/error";
    }

    @GetMapping(value = "/admin/product/view/{id}")
    public String getDetailPage(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("product", currentProduct);
        return "/admin/product/detail";
    }

}
