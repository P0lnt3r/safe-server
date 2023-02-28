package org.anwang.safe.server.safescan.controller.tx.model;

import org.anwang.safe.server.framework.web.model.vo.AbsValueObject;
import org.anwang.safe.server.safescan.repository.EventLogEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class EventLogVO extends AbsValueObject<EventLogEntity> {

    private BigInteger blockNumber;

    private String transactionHash;

    private BigInteger timestamp;

    private LocalDateTime time;

    private BigInteger logIndex;

    private BigInteger transactionIndex;

    private String address;

    private String topic0;

    private String topicsArr;

    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
