package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Orders extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int orders_id; // 
	private int user_id; // 
	private int music_id; // 
	private double music_price;
	private String orders_date; // 

	private String music_name; // 
	private String music_type_name; // 
	private String real_name; // 
	private String nick_name; // 
	private String ids;
	
	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public int getOrders_id() {
		return orders_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public double getMusic_price() {
		return music_price;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public String getMusic_name() {
		return music_name;
	}

	public String getMusic_type_name() {
		return music_type_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public String getIds() {
		return ids;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setMusic_price(double music_price) {
		this.music_price = music_price;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}

	public void setMusic_type_name(String music_type_name) {
		this.music_type_name = music_type_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


}
