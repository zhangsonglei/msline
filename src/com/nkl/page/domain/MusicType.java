package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class MusicType extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1770185824735782580L;
	private int music_type_id; // 
	private String music_type_name; // 

	private String ids;

	public int getMusic_type_id() {
		return music_type_id;
	}

	public void setMusic_type_id(int music_type_id) {
		this.music_type_id = music_type_id;
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

}
