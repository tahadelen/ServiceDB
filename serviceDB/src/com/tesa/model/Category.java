package com.tesa.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category {

    private int _id;
    private String _catName;
    private byte[] _catImg;

    public void set_catImg(byte[] _catImg) {
        this._catImg = _catImg;
    }

    public byte[] get_catImg() {

        return _catImg;
    }

    public Category(String catName, byte[] catImg) {

        this._catName = catName;
        this._catImg = catImg;
    }

    public Category (){

    }

    public void set_id(int id) {
        this._id = id;
    }

    public void set_catName(String catName) {
        this._catName = catName;
    }

    public int get_id() {

        return _id;
    }

    public String get_catName() {
        return _catName;
    }

	public Category(int _id, String _catName, byte[] _catImg) {
		this._id = _id;
		this._catName = _catName;
		this._catImg = _catImg;
	}

}
