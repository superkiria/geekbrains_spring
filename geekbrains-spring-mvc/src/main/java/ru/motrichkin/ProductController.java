package ru.motrichkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.ProductRepository;

import java.util.Scanner;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products";
    }

    @GetMapping("/form")
    public String formProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @RequestMapping("/id")
    public String productById(@RequestParam("id") Long id, Model model) {
        Product product = productRepository.getById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product";
        }
        return allProducts(model);
    }

    @PostMapping("/form")
    public String newProduct(Product product) {
        productRepository.insert(product);
        return "redirect:/products";
    }

}
