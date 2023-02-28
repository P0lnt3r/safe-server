package org.anwang.safe.server.syncprice.scheduler;

import org.anwang.safe.server.blockchain.web3.uniswap.GetReserves;
import org.anwang.safe.server.syncprice.component.BinancePriceData;
import org.anwang.safe.server.syncprice.component.RequestBinancePriceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UpdatePriceScheduler implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(UpdatePriceScheduler.class);

    public static Map<String , BigDecimal> mainnetPriceMap = new HashMap<>();

    private static final String PANCAKE_SWAP_SAFEUSDT = "0x400db103af7a0403c9ab014b2b73702b89f6b4b7";

//    private static final String ETH_MAINNET_RPC_ENDPOINT = "https://mainnet.infura.io/v3/65deb32db4b144f2aae9846c83ca7b6e";
//    private static final String BSC_MAINNET_RPC_ENDPOINT = "https://node.onekey.so/bsc";
//    private static final String MATIC_MAINNET_RPC_ENDPOINT = "https://polygon-mainnet.g.alchemy.com/v2/VIr4KxtvN3rLSuGjprtv-U3fnWWxFr1C";

    private static final String ETH_MAINNET_RPC_ENDPOINT    = "https://eth-mainnet.nodereal.io/v1/9e5c8cd94c754b6f84550fcece3f7d42";
    private static final String BSC_MAINNET_RPC_ENDPOINT    = "https://bsc-mainnet.nodereal.io/v1/9e5c8cd94c754b6f84550fcece3f7d42";
    private static final String MATIC_MAINNET_RPC_ENDPOINT  = "https://polygon-mainnet.nodereal.io/v1/9e5c8cd94c754b6f84550fcece3f7d42";

    private static final String ETH_TESTNET_RPC_ENDPOINT    = "https://eth-goerli.nodereal.io/v1/9e5c8cd94c754b6f84550fcece3f7d42";
    private static final String BSC_TESTNET_RPC_ENDPOINT    = "https://bsc-testnet.nodereal.io/v1/9e5c8cd94c754b6f84550fcece3f7d42";
    private static final String MATIC_TESTNET_RPC_ENDPOINT  = "https://polygon-mainnet.nodereal.io/v1/9e5c8cd94c754b6f84550fcece3f7d42";

    private Web3j ethMainnetAdmin;

    private Web3j bscMainnetAdmin;

    private Web3j maticMainnetAdmin;

    private Web3j ethTestnetAdmin;

    private Web3j bscTestnetAdmin;

    private Web3j maticTestnetAdmin;

    private RequestBinancePriceApi binancePriceApi;

    public static GateData eth = new GateData();

    public static GateData bsc = new GateData() ;

    public static GateData matic = new GateData();

    public static GateData testnet_eth = new GateData();

    public static GateData testnet_bsc = new GateData() ;

    public static GateData testnet_matic = new GateData();

    @Override
    public void afterPropertiesSet() throws Exception {
        binancePriceApi = new RequestBinancePriceApi();

        ethMainnetAdmin = Web3j.build(new HttpService(ETH_MAINNET_RPC_ENDPOINT));
        log.info("构建 ETH-Web3j-Client : {}" , ETH_MAINNET_RPC_ENDPOINT);
        bscMainnetAdmin = Web3j.build(new HttpService(BSC_MAINNET_RPC_ENDPOINT));
        log.info("构建 BSC-Web3j-Client : {}" , BSC_MAINNET_RPC_ENDPOINT);
        maticMainnetAdmin = Web3j.build(new HttpService(MATIC_MAINNET_RPC_ENDPOINT));
        log.info("构建 MATIC-Web3j-Client : {}" , MATIC_MAINNET_RPC_ENDPOINT);

        ethTestnetAdmin = Web3j.build(new HttpService(ETH_TESTNET_RPC_ENDPOINT));
        log.info("构建 ETHTESTNET-Web3j-Client : {}" , ETH_TESTNET_RPC_ENDPOINT);
        bscTestnetAdmin = Web3j.build(new HttpService(BSC_TESTNET_RPC_ENDPOINT));
        log.info("构建 BSCTESTNET-Web3j-Client : {}" , BSC_TESTNET_RPC_ENDPOINT);
        maticTestnetAdmin = Web3j.build(new HttpService(MATIC_TESTNET_RPC_ENDPOINT));
        log.info("构建 MATICTESTNET-Web3j-Client : {}" , MATIC_TESTNET_RPC_ENDPOINT);

        refreshPrice();
    }

    @Scheduled( cron = "0 0/1 * * * ?" )
    public void loop() throws Exception{
        refreshPrice();
    }

    private void refreshPrice() throws Exception{
        log.info("访问币安价格接口Api获取价格");
        List<BinancePriceData> priceList = binancePriceApi.getPrice("ETHUSDT","BNBUSDT","MATICUSDT");
        BigDecimal ethusdt = new BigDecimal( priceList.get(0).getPrice() );
        BigDecimal bnbusdt = new BigDecimal( priceList.get(1).getPrice() );
        BigDecimal maticusdt = new BigDecimal(priceList.get(2).getPrice());

        log.info("访问pancakeswap获取 SAFE/USDT 价格");
        BigInteger[] reservers = GetReserves.getReserves(bscMainnetAdmin,PANCAKE_SWAP_SAFEUSDT);
        BigDecimal safeusdt = new BigDecimal(reservers[1]).divide(new BigDecimal(reservers[0]),2, RoundingMode.UP);


        log.info("= Price : SAFE={} , ETH={} , BNB={} , MATIC={}" , safeusdt , ethusdt , bnbusdt ,maticusdt);
        mainnetPriceMap.put("safeusdt" , safeusdt);
        mainnetPriceMap.put("ethusdt" , ethusdt);
        mainnetPriceMap.put("bnbusdt" , bnbusdt);
        mainnetPriceMap.put("maticusdt" , maticusdt);

        log.info("============ MainNet =============");
        BigInteger ethGasPrice = ethMainnetAdmin.ethGasPrice().send().getGasPrice();
        BigInteger bscGasPrice = bscMainnetAdmin.ethGasPrice().send().getGasPrice();
        BigInteger maticGasPrice = maticMainnetAdmin.ethGasPrice().send().getGasPrice();
        countCrossSafeFee(safeusdt , ethusdt , ethGasPrice , eth , "eth");
        countCrossSafeFee(safeusdt , bnbusdt , bscGasPrice , bsc , "bnb");
        countCrossSafeFee(safeusdt , maticusdt , maticGasPrice , matic , "matic");

        log.info("============ TestNet =============");
        BigInteger ethTestnetGasPrice = ethTestnetAdmin.ethGasPrice().send().getGasPrice();
        BigInteger bscTestnetGasPrice = bscTestnetAdmin.ethGasPrice().send().getGasPrice();
        BigInteger maticTestnetGasPrice = maticTestnetAdmin.ethGasPrice().send().getGasPrice();
        countCrossSafeFee(safeusdt , ethusdt , ethTestnetGasPrice , testnet_eth , "testnet_eth");
        countCrossSafeFee(safeusdt , bnbusdt , bscTestnetGasPrice , testnet_bsc , "testnet_bsc");
        countCrossSafeFee(safeusdt , maticusdt , maticTestnetGasPrice , testnet_matic , "testnet_matic");
    }

    private static void countCrossSafeFee( BigDecimal safeusdt ,BigDecimal price ,BigInteger gasPrice , GateData data ,String label){
        data.setPrice(price);
        // 1SAFE = 10USDT,1ETH = 150USDT,
        // 1ETH = 15SAFE
        BigDecimal safeprice = price.divide(safeusdt , 4 , RoundingMode.UP);
        BigInteger gasLimit = new BigInteger("60000");
        BigInteger gasFee = gasPrice.multiply(gasLimit);
        BigDecimal nativeFee = Convert.fromWei(new BigDecimal(gasFee.toString()) , Convert.Unit.ETHER);
        BigDecimal safeFee = nativeFee.multiply(safeprice).setScale(4,RoundingMode.UP);
        data.setSafe_fee(safeFee);
        BigDecimal gasPriceGWEI = Convert.fromWei( gasPrice.toString() , Convert.Unit.GWEI).setScale(4,RoundingMode.UP);
        data.setGas_price_gwei( gasPriceGWEI );
        log.info("{} = {} SAFE" ,label , safeprice);
        log.info("{} / gas_price_gwei = {}" , label , gasPriceGWEI);
        log.info("{} / cross_SAFE_FEE = {}" , label , safeFee);
    }


}
