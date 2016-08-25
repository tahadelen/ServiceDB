package com.tesa.model;

public class Product {

    private int _id;
    private String _productName;
    private String _productCat;
    private byte[] _img;
    private int _price;

    public Product(String _productName, String _productCat, byte[] _img, int _price) {
        this._productName = _productName;
        this._productCat = _productCat;
        this._img = _img;
        this._price = _price;
    }

    public Product (){
        this._productName = "";
        this._productCat = "";
        this._img = null;
        this._price = 0;
    }

    public Product(int _id, String _productName, String _productCat, byte[] _img, int _price) {
		this._id = _id;
		this._productName = _productName;
		this._productCat = _productCat;
		this._img = _img;
		this._price = _price;
	}

	public int get_id() {
        return _id;
    }

    public String get_productName() {
        return _productName;
    }

    public String get_productCat() {
        return _productCat;
    }

    public byte[] get_img() {
        return _img;
    }

    public int get_price() {
        return _price;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public void set_productCat(String _productCat) {
        this._productCat = _productCat;
    }

    public void set_img(byte[] _img) {
        this._img = _img;
    }

    public void set_price(int _price) {
        this._price = _price;
    }
}
