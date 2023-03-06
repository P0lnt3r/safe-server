package org.anwang.safe.server.safescan.repository;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.anwang.safe.server.AutoGenerateIDEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;

@TableName("erc20_transfer")
public class ERC20TransferEntity extends AutoGenerateIDEntity {

    @TableField("transaction_hash")
    private String transactionHash;

    @TableField("timestamp")
    private BigInteger timestamp;

    @TableField("time")
    private LocalDateTime time;

    @TableField("_from")
    private String from;

    @TableField("_to")
    private String to;

    @TableField("val")
    private String value;

    @TableField("token")
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
