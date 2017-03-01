package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Music extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int music_id; // 
	private int music_type_id; // 
	private String music_name; // 
	private String music_name2;
	private String lrc_name;
	private String music_author;
	private int music_click; // 
	private int music_down; // 
	private double music_price; // 
	private String music_date; // 
	private String music_desc; // 

	private String music_type_name; // 
	private int top_flag; // 
	private String ids;
	
	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public int getMusic_type_id() {
		return music_type_id;
	}

	public void setMusic_type_id(int music_type_id) {
		this.music_type_id = music_type_id;
	}

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}

	public String getMusic_descShow(){
		if (!StringUtil.isEmptyString(music_desc)) {
			return Transcode.htmlDiscode(music_desc);
		}
		return music_desc;
	}
	
	public String getMusic_desc() {
		return music_desc;
	}

	public void setMusic_desc(String music_desc) {
		this.music_desc = music_desc;
	}

	public String getMusic_type_name() {
		return music_type_name;
	}

	public void setMusic_type_name(String music_type_name) {
		this.music_type_name = music_type_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMusic_date() {
		return music_date;
	}

	public void setMusic_date(String music_date) {
		this.music_date = music_date;
	}

	public String getMusic_name2() {
		return music_name2;
	}
	
	public String getLrc_name() {
		return lrc_name;
	}

	public String getMusic_author() {
		return music_author;
	}

	public int getMusic_click() {
		return music_click;
	}

	public void setMusic_name2(String music_name2) {
		this.music_name2 = music_name2;
	}

	public void setLrc_name(String lrc_name) {
		this.lrc_name = lrc_name;
	}

	public void setMusic_author(String music_author) {
		this.music_author = music_author;
	}

	public void setMusic_click(int music_click) {
		this.music_click = music_click;
	}

	public int getTop_flag() {
		return top_flag;
	}

	public void setTop_flag(int top_flag) {
		this.top_flag = top_flag;
	}

	public int getMusic_down() {
		return music_down;
	}

	public double getMusic_price() {
		return music_price;
	}

	public void setMusic_down(int music_down) {
		this.music_down = music_down;
	}

	public void setMusic_price(double music_price) {
		this.music_price = music_price;
	}

}
