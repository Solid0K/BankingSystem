package com.Krishu.Model;

public class transaction {
    private int transc_id;
    private int sender_account;
    private int receiver_account;
    private float amount;
    private String transaction_type;

    public int getTransc_id() {
        return transc_id;
    }

    public void setTransc_id(int transc_id) {
        this.transc_id = transc_id;
    }

    public int getSender_account() {
        return sender_account;
    }

    public void setSender_account(int sender_account) {
        this.sender_account = sender_account;
    }

    public int getReceiver_account() {
        return receiver_account;
    }

    public void setReceiver_account(int receiver_account) {
        this.receiver_account = receiver_account;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
