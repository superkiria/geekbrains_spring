package ru.motrichkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/filter")
    public String allProducts(@RequestParam("minPrice") Integer minPrice,
                              @RequestParam("maxPrice") Integer maxPrice,
                              Model model) {
        if (minPrice == null) {
            minPrice = 0;
        }
        if (maxPrice == null) {
            model.addAttribute("products", productService.getAllProducts(minPrice));
        } else {
            model.addAttribute("products", productService.getAllProducts(minPrice, maxPrice));
        }
        return "products";
    }

    @GetMapping()
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/form")
    public String formProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @RequestMapping("/id")
    public String productById(@RequestParam("id") Long id, Model model) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product";
        }
        return "products";
    }

    @PostMapping("/form")
    public String newProduct(Product product) {
        productService.insert(product);
        return "redirect:/products";
    }

}
