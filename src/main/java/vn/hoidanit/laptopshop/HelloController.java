package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello world";
    }
    @GetMapping("/tpt")
    public String getTPT() {
        return "Thuy Phuoc Thinh";
    }
}