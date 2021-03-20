package ru.motrichkin.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.websocket.OutputMessage;
import ru.motrichkin.websocket.WebSocketController;

import java.util.LinkedList;
import java.util.List;

@Component
@Scope(value = "session",
        proxyMode = ScopedProxyMode.INTERFACES)
public class BucketImpl implements Bucket {
    private List<Product> products = new LinkedList<>();

    @Autowired
    WebSocketController webSocketController;

    @Override
    public Page<Product> getProducts() {
        return new PageImpl<>(products);
    }

    @Override
    public int getProductsAmount() {
        return products.size();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
        webSocketController.sendMessage("/secured/user/queue/greetings", new OutputMessage("В корзине " + products.size() + " товаров"));
    }
}
