package ru.motrichkin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.ProductSpecification;
import ru.motrichkin.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Integer DEFAULT_SIZE = 1000;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/filter")
    public String getAllProducts(@RequestParam("minPrice") final Integer minPrice,
                              @RequestParam("maxPrice") final Integer maxPrice,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
                              Model model) {
        Integer correctedSize = size > 0 ? size : DEFAULT_SIZE;
        ProductSpecification productSpecification =
                ProductSpecification.newBuilder().setMinPrice(minPrice).setMaxPrice(maxPrice).build();

        model.addAttribute("products", productService.getAllProducts(productSpecification, PageRequest.of(page - 1, correctedSize)));
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("page", page);
        model.addAttribute("size", correctedSize);
        model.addAttribute("activePage", "Список товаров");
        return "products";
    }

    @GetMapping()
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts(PageRequest.of(0, 1000)));
        model.addAttribute("minPrice", 0);
        model.addAttribute("maxPrice", null);
        model.addAttribute("page", 1);
        model.addAttribute("size", DEFAULT_SIZE);
        return "products";
    }

    @GetMapping("/form")
    public String getFormProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @RequestMapping("/id")
    public String getProductById(@RequestParam("id") Long id, Model model) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product";
        }
        return "products";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product_form";
    }

    @PostMapping("/form")
    public String newProduct(Product product) {
        productService.insert(product);
        return "redirect:/products";
    }

}
