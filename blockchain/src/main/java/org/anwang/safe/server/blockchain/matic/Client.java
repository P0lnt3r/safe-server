package org.anwang.safe.server.blockchain.matic;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

public class Client {

    public static void main(String[] args) throws Exception {
        String endpoint = "https://polygon-mainnet.g.alchemy.com/v2/VIr4KxtvN3rLSuGjprtv-U3fnWWxFr1C";
        HttpService service = new HttpService(endpoint);
        Web3j web3j = Web3j.build(service);

        BigInteger blockNumber = web3j.ethBlockNumber().send().getBlockNumber();
        System.out.println(blockNumber);
    }

}
