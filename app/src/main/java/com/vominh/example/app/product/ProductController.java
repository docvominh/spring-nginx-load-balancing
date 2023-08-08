package com.vominh.example.app.product;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public String home(Model model, HttpServletResponse response) throws UnknownHostException {
        model.addAttribute("message", "Hello everybody !!!");
        model.addAttribute("product", new Product());
        model.addAttribute("products", service.findAll());
        response.addHeader("host", InetAddress.getLocalHost().getHostAddress());
        return "home";
    }

    @PostMapping("/product/add")
    public String create(@ModelAttribute("product") Product product, Model model,
        HttpServletResponse response) throws UnknownHostException {
        service.create(product);
        model.addAttribute("products", service.findAll());
        response.addHeader("host", InetAddress.getLocalHost().getHostAddress());
        return "product-table";
    }
}
