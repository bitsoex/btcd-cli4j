package com.neemre.btcdcli4j.core.grpc;

import com.bitso.model.BtcdServiceGrpc;
import com.bitso.model.BtcdServiceProto;
import com.neemre.btcdcli4j.core.grpc.config.BtcdGrpcServiceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Class for handling grpc clients for bitcoin network nodes
 */
@Slf4j
@RequiredArgsConstructor
public class BtcdGrpcClient {

    // grpc client for node
    private final BtcdServiceGrpc.BtcdServiceBlockingStub grcpClient;

    /**
     * Initiate gRPC client with host and port
     * @param host host
     * @param port port
     */
    public BtcdGrpcClient(final String host, final int port){
        grcpClient = new BtcdGrpcServiceConfig().createStub(host, port);
    }

    /**
     * @see <a href="https://developer.bitcoin.org/reference/rpc/getrawtransaction.html">getrawtransaction</a>
     * @param request get raw transaction request
     * @return Optional BtcdServiceProto.GetRawTransactionResponse get raw transaction response
     */
    public Optional<BtcdServiceProto.GetRawTransactionResponse> getRawTransaction(
            final BtcdServiceProto.GetRawTransactionRequest request){
        
        try {
            return Optional.of(grcpClient.getRawTransaction(request));
        }
        catch (Exception exception){
            log.error("Error when tried 'getRawTransaction' for request: {}", request.toString());
            return Optional.empty();
        }
    }
    
}
