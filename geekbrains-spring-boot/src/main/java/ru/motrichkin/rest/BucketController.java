package ru.motrichkin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.motrichkin.service.ProductService;
import ru.motrichkin.session.Bucket;
import ru.motrichkin.websocket.Message;
import ru.motrichkin.websocket.OutputMessage;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    private Bucket bucket;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String bucket(Model model) {
        model.addAttribute("count", bucket.getProducts().getTotalElements());
        model.addAttribute("products", bucket.getProducts());
        return "bucket";
    }

    @PostMapping("/put")
    public String putProductInBucket(@RequestParam("id") Long productId) {
        productService.getById(productId).ifPresent(value -> bucket.addProduct(value));
        return "redirect:/products";
    }
}
