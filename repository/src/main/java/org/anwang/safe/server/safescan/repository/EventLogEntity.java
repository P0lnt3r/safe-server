package org.anwang.safe.server.safescan.repository;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.anwang.safe.server.AutoGenerateIDEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;

@TableName("eventlogs")
public class EventLogEntity extends AutoGenerateIDEntity {

    @TableField("block_number")
    private BigInteger blockNumber;

    @TableField("transaction_hash")
    private String transactionHash;

    @TableField("timestamp")
    private BigInteger timestamp;

    @TableField("time")
    private LocalDateTime time;

    @TableField("log_index")
    private BigInteger logIndex;

    @TableField("transaction_index")
    private BigInteger transactionIndex;

    @TableField("address")
    private String address;

    @TableField("topic0")
    private String topic0;

    @TableField("topics_arr")
    private String topicsArr;

    @TableField("data")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

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

    public BigInteger getLogIndex() {
        return logIndex;
    }

    public void setLogIndex(BigInteger logIndex) {
        this.logIndex = logIndex;
    }

    public BigInteger getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(BigInteger transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTopic0() {
        return topic0;
    }

    public void setTopic0(String topic0) {
        this.topic0 = topic0;
    }

    public String getTopicsArr() {
        return topicsArr;
    }

    public void setTopicsArr(String topicsArr) {
        this.topicsArr = topicsArr;
    }
}
