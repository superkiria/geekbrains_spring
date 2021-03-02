package ru.motrichkin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.service.ProductService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public String allProducts(@RequestParam("minPrice") final Integer minPrice,
                              @RequestParam("maxPrice") final Integer maxPrice,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
                              Model model) {

        Specification<Product> productSpecification =
                (Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                        -> criteriaBuilder.between(root.get("cost"), minPrice != null ? minPrice : 0, maxPrice != null ? maxPrice : Integer.MAX_VALUE);

        model.addAttribute("products", productService.getAllProducts(productSpecification, PageRequest.of(page - 1, size)));
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("activePage", "Список товаров");
        return "products";
    }

    @GetMapping()
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts(0, PageRequest.of(0, 5)));
        model.addAttribute("minPrice", 0);
        model.addAttribute("maxPrice", null);
        model.addAttribute("page", 1);
        model.addAttribute("size", 5);
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
