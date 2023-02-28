package org.anwang.safe.server.syncprice.controller;

import org.anwang.safe.server.syncprice.business.IGatePropService;
import org.anwang.safe.server.syncprice.repository.GatePropEntity;
import org.anwang.safe.server.syncprice.scheduler.UpdatePriceScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/gate")
public class GateController {

    @Autowired
    IGatePropService gatePropService;

    Map<String,Object> mainnet = new HashMap<>();

    Map<String,Object> testnet = new HashMap<>();

    @GetMapping("mainnet")
    public ApiReturnData mainnet() throws Exception{
        List<GatePropEntity> list = gatePropService.list();
        list.stream().filter( entity -> {
            return entity.getPropKey().startsWith("mainnet");
        }).forEach( gatePropEntity -> {
            mainnet.put(
                    gatePropEntity.getPropKey().substring( gatePropEntity.getPropKey().indexOf("_") + 1 ),
                    gatePropEntity.getPropVal()
            );
        });
        ApiReturnData data = new ApiReturnData();
        data.setSafe_usdt(UpdatePriceScheduler.mainnetPriceMap.get("safeusdt"));
        data.setMinamount(new BigDecimal(mainnet.get("minamount").toString()));

        ApiReturnData.ETHGateData eth = new ApiReturnData.ETHGateData();
        eth.setGas_price_gwei(UpdatePriceScheduler.eth.getGas_price_gwei());
        eth.setPrice(UpdatePriceScheduler.eth.getPrice());
        eth.setSafe_fee(UpdatePriceScheduler.eth.getSafe_fee());
        eth.setSafe2eth("1".equals(mainnet.get("safe2eth")));
        eth.setEth2safe("1".equals(mainnet.get("eth2safe")));
        data.setEth(eth);

        ApiReturnData.BSCGateData bsc = new ApiReturnData.BSCGateData();
        bsc.setGas_price_gwei(UpdatePriceScheduler.bsc.getGas_price_gwei());
        bsc.setPrice(UpdatePriceScheduler.bsc.getPrice());
        bsc.setSafe_fee(UpdatePriceScheduler.bsc.getSafe_fee());
        bsc.setSafe2bsc("1".equals(mainnet.get("safe2bsc")));
        bsc.setBsc2safe("1".equals(mainnet.get("bsc2safe")));
        data.setBsc(bsc);

        ApiReturnData.MATICGateData matic = new ApiReturnData.MATICGateData();
        matic.setGas_price_gwei(UpdatePriceScheduler.matic.getGas_price_gwei());
        matic.setPrice(UpdatePriceScheduler.matic.getPrice());
        matic.setSafe_fee(UpdatePriceScheduler.matic.getSafe_fee());
        matic.setSafe2matic("1".equals(mainnet.get("safe2matic")));
        matic.setMatic2safe("1".equals(mainnet.get("matic2safe")));
        data.setMatic(matic);

        return data;
    }

    @GetMapping("testnet")
    public ApiReturnData testnet() throws Exception{
        List<GatePropEntity> list = gatePropService.list();
        list.stream().filter( entity -> {
            return entity.getPropKey().startsWith("testnet");
        }).forEach( gatePropEntity -> {
            testnet.put(
                    gatePropEntity.getPropKey().substring( gatePropEntity.getPropKey().indexOf("_") + 1 ),
                    gatePropEntity.getPropVal()
            );
        });
        ApiReturnData data = new ApiReturnData();
        data.setSafe_usdt(UpdatePriceScheduler.mainnetPriceMap.get("safeusdt"));
        data.setMinamount(new BigDecimal(testnet.get("minamount").toString()));

        ApiReturnData.ETHGateData eth = new ApiReturnData.ETHGateData();
        eth.setGas_price_gwei(UpdatePriceScheduler.testnet_eth.getGas_price_gwei());
        eth.setPrice(UpdatePriceScheduler.testnet_eth.getPrice());
        eth.setSafe_fee(UpdatePriceScheduler.testnet_eth.getSafe_fee());
        eth.setSafe2eth("1".equals(testnet.get("safe2eth")));
        eth.setEth2safe("1".equals(testnet.get("eth2safe")));
        data.setEth(eth);

        ApiReturnData.BSCGateData bsc = new ApiReturnData.BSCGateData();
        bsc.setGas_price_gwei(UpdatePriceScheduler.testnet_bsc.getGas_price_gwei());
        bsc.setPrice(UpdatePriceScheduler.testnet_bsc.getPrice());
        bsc.setSafe_fee(UpdatePriceScheduler.testnet_bsc.getSafe_fee());
        bsc.setSafe2bsc("1".equals(testnet.get("safe2bsc")));
        bsc.setBsc2safe("1".equals(testnet.get("bsc2safe")));
        data.setBsc(bsc);

        ApiReturnData.MATICGateData matic = new ApiReturnData.MATICGateData();
        matic.setGas_price_gwei(UpdatePriceScheduler.testnet_matic.getGas_price_gwei());
        matic.setPrice(UpdatePriceScheduler.testnet_matic.getPrice());
        matic.setSafe_fee(UpdatePriceScheduler.testnet_matic.getSafe_fee());
        matic.setSafe2matic("1".equals(testnet.get("safe2matic")));
        matic.setMatic2safe("1".equals(testnet.get("matic2safe")));
        data.setMatic(matic);

        return data;
    }

}
