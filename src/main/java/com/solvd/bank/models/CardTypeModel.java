package com.solvd.bank.models;

import java.util.ArrayList;
import java.util.List;

public class CardTypeModel {
    private int id;
    private String type;
    private List<CardModel> cards = new ArrayList<>();

    public CardTypeModel() {
    }

    public CardTypeModel(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CardModel> getCards() {
        return cards;
    }

    public void setCards(List<CardModel> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "CardTypeModel{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", cards=" + cards +
                '}';
    }
}
