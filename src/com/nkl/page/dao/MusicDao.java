package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Music;

public class MusicDao {

	public int addMusic(Music music, Connection conn){
		String sql = "INSERT INTO music(music_id,music_type_id,music_name,music_name2,lrc_name,music_author,music_click,music_down,music_price,music_date,music_desc) values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			music.getMusic_type_id(),
			music.getMusic_name(),
			music.getMusic_name2(),
			music.getLrc_name(),
			music.getMusic_author(),
			music.getMusic_click(),
			music.getMusic_down(),
			music.getMusic_price(),
			music.getMusic_date(),
			music.getMusic_desc()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delMusic(String music_id, Connection conn){
		String sql = "DELETE FROM music WHERE music_id=?";

		Object[] params = new Object[] { new Integer(music_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delMusics(String[] music_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <music_ids.length; i++) {
			sBuilder.append("?");
			if (i !=music_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM music WHERE music_id IN(" +sBuilder.toString()+")";

		Object[] params = music_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateMusic(Music music, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE music SET music_id = " + music.getMusic_id() +" ");
		if (music.getMusic_type_id()!=0) {
			sBuilder.append(" ,music_type_id = " + music.getMusic_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_name())) {
			sBuilder.append(" ,music_name = '" + music.getMusic_name() +"' ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_name2())) {
			sBuilder.append(" ,music_name2 = '" + music.getMusic_name2() +"' ");
		}
		if (!StringUtil.isEmptyString(music.getLrc_name())) {
			sBuilder.append(" ,lrc_name = '" + music.getLrc_name() +"' ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_author())) {
			sBuilder.append(" ,music_author = '" + music.getMusic_author() +"' ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_desc())) {
			sBuilder.append(" ,music_desc = '" + music.getMusic_desc() +"' ");
		}else {
			sBuilder.append(" ,music_desc = '' ");
		}
		if (music.getMusic_price()!=0) {
			sBuilder.append(" ,music_price = " + music.getMusic_price() +" ");
		}
		
		sBuilder.append(" where music_id = " + music.getMusic_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public int updateMusicClick(Music music, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE music SET music_id = " + music.getMusic_id() +" ");
		if (music.getMusic_click()!=-1) {
			sBuilder.append(" ,music_click = " + music.getMusic_click() +" ");
		}
		sBuilder.append(" where music_id = " + music.getMusic_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public int updateMusicDown(Music music, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE music SET music_id = " + music.getMusic_id() +" ");
		if (music.getMusic_down()!=-1) {
			sBuilder.append(" ,music_down = " + music.getMusic_down() +" ");
		}
		sBuilder.append(" where music_id = " + music.getMusic_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Music getMusic(Music music, Connection conn){
		Music _music=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT b.*,bt.music_type_name FROM music b left join music_type bt on b.music_type_id=bt.music_type_id WHERE 1=1");
		if (music.getMusic_id()!=0) {
			sBuilder.append(" and music_id = " + music.getMusic_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Music.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _music = (Music)list.get(0);
		}
		return _music;
	}

	public List<Music>  listMusics(Music music, Connection conn){
		List<Music> musics = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT b.*,bt.music_type_name FROM music b left join music_type bt on b.music_type_id=bt.music_type_id WHERE 1=1");

		if (music.getMusic_id()!=0) {
			sBuilder.append(" and music_id = " + music.getMusic_id() +" ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_name())) {
			sBuilder.append(" and music_name like '%" + music.getMusic_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_author())) {
			sBuilder.append(" and music_author like '%" + music.getMusic_author() +"%' ");
		}
		if (music.getMusic_type_id()!=0) {
			sBuilder.append(" and b.music_type_id = " + music.getMusic_type_id() +" ");
		}
		if (music.getTop_flag()==1) {
			sBuilder.append(" order by music_click desc,music_id asc) t");
		}else if (music.getTop_flag()==2) {
			sBuilder.append(" order by music_down desc,music_id asc) t");
		}else if (music.getTop_flag()==3) {
			sBuilder.append(" order by music_down desc,music_id asc) t");
		} else{
			sBuilder.append(" order by music_id asc) t");
		}
			
		

		if (music.getStart() != -1) {
			sBuilder.append(" limit " + music.getStart() + "," + music.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Music.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			musics = new ArrayList<Music>();
			for (Object object : list) {
				musics.add((Music)object);
			}
		}
		return musics;
	}

	public int  listMusicsCount(Music music, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM music b WHERE 1=1");

		if (music.getMusic_id()!=0) {
			sBuilder.append(" and music_id = " + music.getMusic_id() +" ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_name())) {
			sBuilder.append(" and music_name like '%" + music.getMusic_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(music.getMusic_author())) {
			sBuilder.append(" and music_author like '%" + music.getMusic_author() +"%' ");
		}
		if (music.getMusic_type_id()!=0) {
			sBuilder.append(" and music_type_id = " + music.getMusic_type_id() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
}
