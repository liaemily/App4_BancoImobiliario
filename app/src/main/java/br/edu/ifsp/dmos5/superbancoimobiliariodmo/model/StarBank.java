package br.edu.ifsp.dmos5.superbancoimobiliariodmo.model;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;

public class StarBank {
    private static StarBank instance = null;
    private ArrayList<CreditCard> cards = new ArrayList<CreditCard>();
    private StarBank() {
    }
    public static StarBank getInstance() {
        if (instance == null) {
            instance = new StarBank();
            instance.startCreditCards();
        }
        return instance;
    }
    public void startCreditCards() {

        for (int count = 1; count <= 6; count++) {
            CreditCard card = new CreditCard();
            cards.add(card);
        }
    }
    public boolean transfer(int payer, int receiver, double value) {
        CreditCard p = null;
        CreditCard r = null;
        boolean transfer = true;

        for (int count = 0; count <= 5; count++) {

            if (cards.get(count).getIdentificador() == payer) {
                p = cards.get(count);
            }

            if (cards.get(count).getIdentificador() == receiver) {
                r = cards.get(count);
            }
        }

        try {
            p.debitValue(value);
        } catch (Exception e) {
            transfer = false;
        }

        if (transfer == true) {
            r.creditValue(value);
        }

        return transfer;
    }
    public void receive(int receiver, double value) {

        CreditCard card = null;

        for (int count = 0; count <= 5; count++) {
            if (cards.get(count).getIdentificador() == receiver) {
                card = cards.get(count);
            }
        }

        card.creditValue(value);
    }
    public boolean pay(int pay, double value) {
        boolean p = true;

        CreditCard card = null;

        for (int count = 0; count <= 5; count++) {

            if (cards.get(count).getIdentificador() == pay) {
                card = cards.get(count);
            }
        }

        try {
            card.debitValue(value);
        } catch (Exception e) {
            p = false;
        }

        return p;
    }
    public void roundCompleted(int card, double value) {
        receive(card, value);
    }

}
