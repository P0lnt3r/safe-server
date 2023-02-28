package org.anwang.safe.server.syncprice.controller;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.anwang.safe.server.syncprice.scheduler.GateData;

import java.math.BigDecimal;

@JsonPropertyOrder(
        {"safe_usdt" , "minamount" , "eth" , "bsc" , "matic"}
)
public class ApiReturnData {

    private BigDecimal safe_usdt;

    private BigDecimal minamount;

    private ETHGateData eth;

    private BSCGateData bsc;

    private MATICGateData matic;

    public BigDecimal getSafe_usdt() {
        return safe_usdt;
    }

    public void setSafe_usdt(BigDecimal safe_usdt) {
        this.safe_usdt = safe_usdt;
    }

    public BigDecimal getMinamount() {
        return minamount;
    }

    public void setMinamount(BigDecimal minamount) {
        this.minamount = minamount;
    }

    public ETHGateData getEth() {
        return eth;
    }

    public void setEth(ETHGateData eth) {
        this.eth = eth;
    }

    public BSCGateData getBsc() {
        return bsc;
    }

    public void setBsc(BSCGateData bsc) {
        this.bsc = bsc;
    }

    public MATICGateData getMatic() {
        return matic;
    }

    public void setMatic(MATICGateData matic) {
        this.matic = matic;
    }

    static class ETHGateData extends GateData {

        private Boolean safe2eth;

        private Boolean eth2safe;

        public Boolean getSafe2eth() {
            return safe2eth;
        }

        public void setSafe2eth(Boolean safe2eth) {
            this.safe2eth = safe2eth;
        }

        public Boolean getEth2safe() {
            return eth2safe;
        }

        public void setEth2safe(Boolean eth2safe) {
            this.eth2safe = eth2safe;
        }
    }

    static class BSCGateData extends GateData {

        private Boolean safe2bsc;

        private Boolean bsc2safe;

        public Boolean getSafe2bsc() {
            return safe2bsc;
        }

        public void setSafe2bsc(Boolean safe2bsc) {
            this.safe2bsc = safe2bsc;
        }

        public Boolean getBsc2safe() {
            return bsc2safe;
        }

        public void setBsc2safe(Boolean bsc2safe) {
            this.bsc2safe = bsc2safe;
        }
    }

    static class MATICGateData extends GateData {
        private Boolean safe2matic;
        private Boolean matic2safe;

        public Boolean getSafe2matic() {
            return safe2matic;
        }

        public void setSafe2matic(Boolean safe2matic) {
            this.safe2matic = safe2matic;
        }

        public Boolean getMatic2safe() {
            return matic2safe;
        }

        public void setMatic2safe(Boolean matic2safe) {
            this.matic2safe = matic2safe;
        }
    }

}
