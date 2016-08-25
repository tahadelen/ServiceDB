package com.tesa.model;

public class Orders {
    private int orderId;
    private Tables table;
    private Product product;
    private int piece;
    private double portion;

    public Orders(int orderId, Tables table, int piece, double portion, Product product) {
        this.orderId = orderId;
        this.table = table;
        this.piece = piece;
        this.portion = portion;
        this.product = product;
    }

    public Orders (){

    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }

    public int getOrderId() {

        return orderId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {

        return product;
    }

    public Tables getTable() {
        return table;
    }

    public int getPiece() {
        return piece;
    }

    public double getPortion() {
        return portion;
    }}
