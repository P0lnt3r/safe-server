package org.anwang.safe.server.syncprice.scheduler;

import java.math.BigDecimal;

public class GateData {

    private BigDecimal price;

    private BigDecimal gas_price_gwei;

    private BigDecimal safe_fee;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGas_price_gwei() {
        return gas_price_gwei;
    }

    public void setGas_price_gwei(BigDecimal gas_price_gwei) {
        this.gas_price_gwei = gas_price_gwei;
    }

    public BigDecimal getSafe_fee() {
        return safe_fee;
    }

    public void setSafe_fee(BigDecimal safe_fee) {
        this.safe_fee = safe_fee;
    }
}
