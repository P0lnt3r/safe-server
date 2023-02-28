package org.anwang.safe.server.blockchain.matic;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.events.Log;

import java.math.BigInteger;
import java.util.List;

public class EventFilterTest {

    public static void main(String[] args) throws Exception{

        String endpoint = "https://polygon-mainnet.g.alchemy.com/v2/VIr4KxtvN3rLSuGjprtv-U3fnWWxFr1C";
        HttpService service = new HttpService(endpoint);
        Web3j web3j = Web3j.build(service);

        String address = "0xac90c0e8b5aa24594ce05b1c604f0845388ed644";
        String SWAP_TOPIC = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";

        EthFilter filter = new EthFilter(
                DefaultBlockParameter.valueOf(new BigInteger("39535530")),
                DefaultBlockParameter.valueOf(new BigInteger("39535540")),
                address
        );
        filter.addSingleTopic(SWAP_TOPIC);
        BigInteger filterId = web3j.ethNewFilter(filter).send().getFilterId();
        List<EthLog.LogResult> logResultList = web3j.ethGetFilterLogs(filterId).send().getLogs();
        logResultList.forEach( logResult -> {
            EthLog.LogObject log = (EthLog.LogObject )logResult.get();
            System.out.println(log.getTransactionHash());
            log.getTopics().forEach(System.out::println);
        } );



    }

}
