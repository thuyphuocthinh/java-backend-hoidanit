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
        return "Thuy Phuoc Thinh TPT TPT";
    }
}

/*
 * Spring MVC
 * Spring Hibernate/JPA
 * Sprint security
 * Spring Boot
 * ....
 * Hoc Spring => hoc he sinh thai Spring de viet web
 * Maven => build a java spring project
 * Webpack => build a reactjs/vue project
 * Vite => build a react/vue project
 * node => build an express server
 * ....
 * Build: source => target
 * POM.xml => metadata about the project configuration => which depedencies, how to build
 * - Compile
 * - Build
 * - Run
 */

/**
 * Spring Architecture
 * 1. Inversion of Control (theory) => Dependency Injection (a way to implement IoC)
 * - Reuse instances of classes => instead of creating multiple similar instances
 * - Improve performance
 * - Improve memory allocation
 * 2. How Spring container manages Beans
 * - Components: a web is composed of many components, each has its own function
 * + Components are managed by Spring container
 * - Component scan => marked by @Component ~ an instance ~ registerred to container
 * - Component
 * + Beans
 * + Services
 * + ...
 * + Defined ourselves
 * 3. Tightly coupled and loosely coupled
 */