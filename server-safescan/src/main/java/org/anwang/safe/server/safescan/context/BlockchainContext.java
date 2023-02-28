package org.anwang.safe.server.safescan.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Component
public class BlockchainContext implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(BlockchainContext.class);

    @Value("${web3j.endpoint}")
    private String endpoint;

    public static Web3j web3j;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            initializeWeb3jClient();
        }catch (Exception e){
            throw e;
        }
    }

    private void initializeWeb3jClient() throws Exception {
        log.info("初始化 Web3j 客户端 , endpoint = {}" , endpoint);
        HttpService service = new HttpService( endpoint );
        web3j = Web3j.build(service);
        String version = web3j.web3ClientVersion().send().getWeb3ClientVersion();
        log.info("初始化 Web3j 客户端完成 , client-version = {}" , version);
    }

}
