package org.anwang.safe.server.safescan.controller.tx.model;

import com.baomidou.mybatisplus.annotation.TableField;
import org.anwang.safe.server.framework.web.model.vo.AbsValueObject;
import org.anwang.safe.server.safescan.repository.ERC20TransferEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class ERC20TransferVO extends AbsValueObject<ERC20TransferEntity> {

    private String transactionHash;

    private BigInteger timestamp;

    private LocalDateTime time;

    private String from;

    private String to;

    private String value;

    private String token;

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
