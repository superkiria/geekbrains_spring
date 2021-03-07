package ru.motrichkin.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.motrichkin.grpc.*;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ProductServiceGrpc.ProductServiceBlockingStub stub
                = ProductServiceGrpc.newBlockingStub(channel);
        ManyProductsResponse manyProductsResponse = stub.getAllProducts(ManyProductsRequest.newBuilder().build());
        System.out.println("Response received from server:\n" + manyProductsResponse);
        System.out.println("\n");

        ProductResponse productResponse = stub.getProductById(ProductByIdRequest.newBuilder().setId(7).build());
        System.out.println("Response received from server:\n" + productResponse);

        channel.shutdown();
    }
}
