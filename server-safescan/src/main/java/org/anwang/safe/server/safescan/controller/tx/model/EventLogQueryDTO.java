package org.anwang.safe.server.safescan.controller.tx.model;

public class EventLogQueryDTO {

    private String transactionHash;

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }
}
