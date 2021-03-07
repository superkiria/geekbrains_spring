package ru.motrichkin.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductGrpcServer {

    @Autowired
    private ProductServiceGrpcImpl productServiceGrpc;

    public void run() throws IOException, InterruptedException {
        System.out.println("Hello gRPC");

        final Server server = ServerBuilder.forPort(50051)
                .addService(productServiceGrpc)
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }
}
