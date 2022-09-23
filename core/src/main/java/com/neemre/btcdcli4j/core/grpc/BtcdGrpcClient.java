package com.neemre.btcdcli4j.core.grpc;

import com.bitso.model.BtcdServiceGrpc;
import com.bitso.model.BtcdServiceProto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Class for handling grpc clients for bitcoin network nodes
 */
@RequiredArgsConstructor
public class BtcdGrpcClient {

    // grpc client for bitcoin-core node
    private final BtcdServiceGrpc.BtcdServiceBlockingStub bitcoinNodeClient;
    
    // grpc client for bitcoin-cash node
    private final BtcdServiceGrpc.BtcdServiceBlockingStub bitcoinCashNodeClient;
    
    // grpc client for litecoin node
    private final BtcdServiceGrpc.BtcdServiceBlockingStub litecoinNodeClient;

    /**
     * @see <a href="https://developer.bitcoin.org/reference/rpc/getrawtransaction.html">getrawtransaction</a>
     * @param currency for select the correct client
     * @param request get raw transaction request
     * @return Optional BtcdServiceProto.GetRawTransactionResponse get raw transaction response
     */
    public Optional<BtcdServiceProto.GetRawTransactionResponse> getRawTransaction(
            final String currency, BtcdServiceProto.GetRawTransactionRequest request){
        
        return getNodeClient(currency).map(client -> client.getRawTransaction(request));
    }

    /**
     * Select correct node by currency
     * @param currency currency for selection
     * @return Optional<BtcdServiceGrpc.BtcdServiceBlockingStub> node client
     */
    private Optional<BtcdServiceGrpc.BtcdServiceBlockingStub> getNodeClient(
            final String currency){

        if("btc".equals(currency)){
            return Optional.of(bitcoinNodeClient);
        }

        if("bch".equals(currency)){
            return Optional.of(bitcoinCashNodeClient);
        }

        if("ltc".equals(currency)){
            return Optional.of(litecoinNodeClient);
        }

        return Optional.empty();
    }
    
}
