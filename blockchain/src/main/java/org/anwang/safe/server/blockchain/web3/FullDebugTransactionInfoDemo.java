package org.anwang.safe.server.blockchain.web3;

import org.web3j.protocol.besu.JsonRpc2_0Besu;
import org.web3j.protocol.besu.response.FullDebugTraceInfo;
import org.web3j.protocol.http.HttpService;

import java.util.HashMap;
import java.util.Map;

public class FullDebugTransactionInfoDemo {

    public static void main(String[] args) throws Exception {
        HttpService service = new HttpService("https://node.onekey.so/bsc");
        JsonRpc2_0Besu web3j = new JsonRpc2_0Besu(service);

        Map<String,Boolean> options = new HashMap<>();
        options.put("disableStorage" , true);
        options.put("disableStack" , true);
        options.put("disableMemory" , true);

        FullDebugTraceInfo fullDebugTraceInfo = web3j.debugTraceTransaction(
                "0x5210103ed97acafefe56a59b1df83239c5dd658736b319653388d6ed9dbabb4c",
                options
        ).send().getFullDebugTraceInfo();
        System.out.println(fullDebugTraceInfo);
    }
}
