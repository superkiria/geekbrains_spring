package ru.motrichkin.grpc;

import io.grpc.BindableService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.motrichkin.exceptions.NotFoundException;
import ru.motrichkin.grpc.ProductServiceGrpc.ProductServiceImplBase;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceGrpcImpl extends ProductServiceImplBase implements BindableService {

    @Autowired
    private ProductService productService;

    @Override
    public void getAllProducts(ManyProductsRequest request, StreamObserver<ManyProductsResponse> responseObserver) {
        List<ProductGrpc> resultList = productService.getAllProducts().stream()
                .map(product -> ProductGrpc.newBuilder()
                        .setId(product.getId())
                        .setTitle(product.getTitle())
                        .setCost(product.getCost())
                        .build())
                .collect(Collectors.toList());
        ManyProductsResponse response = ManyProductsResponse.newBuilder()
                .addAllProduct(resultList)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductById(ProductByIdRequest request, StreamObserver<ProductResponse> responseObserver) {
        Optional<Product> product = productService.getById(request.getId());
        product.orElseThrow(() -> new NotFoundException("No such product with id " + request.getId()));
        ProductGrpc result = ProductGrpc.newBuilder()
                .setId(product.get().getId())
                .setTitle(product.get().getTitle())
                .setCost(product.get().getCost())
                .build();
        ProductResponse response = ProductResponse.newBuilder()
                .setProduct(result)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
