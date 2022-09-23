package com.neemre.btcdcli4j.core.grpc.config;

import com.bitso.model.BtcdServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Configuration to create a gRPC client for the {@code <currency-node>.bitsocluster<env>.bitsoops.com} service
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BtcdGrpcServiceConfig {

    /**
     * Create a blocking stub for the given host and port
     * @param host host address
     * @param port port address
     * @return BtcdServiceGrpc.BtcdServiceBlockingStub stub
     */
    public BtcdServiceGrpc.BtcdServiceBlockingStub createStub(final String host, final int port) {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        
        return BtcdServiceGrpc.newBlockingStub(channel);
    }
    
}
