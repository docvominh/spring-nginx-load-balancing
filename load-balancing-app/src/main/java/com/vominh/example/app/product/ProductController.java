package com.vominh.example.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello everybody !!!");
        model.addAttribute("product", new Product());
        model.addAttribute("products", service.findAll());
        return "home";
    }

    @PostMapping("/product/add")
    public String create(@ModelAttribute("product") Product product, Model model) {
        service.create(product);
        model.addAttribute("products", service.findAll());
        return "product-table";
    }
}
