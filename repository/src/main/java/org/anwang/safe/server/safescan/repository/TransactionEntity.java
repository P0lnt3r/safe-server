package org.anwang.safe.server.safescan.repository;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.anwang.safe.server.AutoGenerateIDEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;

@TableName("transactions")
public class TransactionEntity extends AutoGenerateIDEntity {

    @TableField("hash")
    private String hash;

    @TableField("block_number")
    private BigInteger blockNumber;

    @TableField("block_Hash")
    private String blockHash;

    @TableField("transaction_index")
    private BigInteger transactionIndex;

    @TableField("timestamp")
    private BigInteger timestamp;

    @TableField("time")
    private LocalDateTime time;

    @TableField("_from")
    private String from;

    @TableField("_to")
    private String to;

    @TableField("val")
    private BigInteger value;

    @TableField("input")
    private String input;

    @TableField("method_id")
    private String methodId;

    @TableField("gas_price")
    private BigInteger gasPrice;

    @TableField("gas_used")
    private BigInteger gasUsed;

    @TableField("gas")
    private BigInteger gas;

    @TableField("nonce")
    private BigInteger nonce;

    @TableField("status")
    private Integer status;

    @TableField("call_type")
    private Integer callType;

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public BigInteger getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(BigInteger transactionIndex) {
        this.transactionIndex = transactionIndex;
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

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigInteger gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }

    public BigInteger getGas() {
        return gas;
    }

    public void setGas(BigInteger gas) {
        this.gas = gas;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
    }
}
