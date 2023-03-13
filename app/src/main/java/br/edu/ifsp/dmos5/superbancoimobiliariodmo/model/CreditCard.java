package br.edu.ifsp.dmos5.superbancoimobiliariodmo.model;

public class CreditCard {
    private static int LAST_CARD_ID = 0;
    private int id;
    private double balance;
    public CreditCard() {
        this.id = ++LAST_CARD_ID;
        this.balance = 15000;
        LAST_CARD_ID = id;
    }
    public void creditValue(double value) {
        this.balance += value;
    }
    public void debitValue(double value) throws Exception {
        if (value > this.balance) {
            throw new Exception("Saldo insuficiente");
        } else {
            this.balance -= value;
        }
    }
    public int getIdentificador() {
        return id;
    }


}
