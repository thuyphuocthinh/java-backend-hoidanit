package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserService() {
        
    }
    public String handleHello() {
        return "Hello from service";
    }
}
