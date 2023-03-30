package com.vominh.example.scalableweb.controller;

import com.vominh.example.scalableweb.entity.ProductEntity;
import com.vominh.example.scalableweb.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello everybody !!!");
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("products", service.findAll());
        return "home";
    }

    @PostMapping("/product/add")
    public String create(@ModelAttribute("product") ProductEntity product, Model model) {
        service.create(product);
        model.addAttribute("products", service.findAll());
        return "product-table";
    }
}
