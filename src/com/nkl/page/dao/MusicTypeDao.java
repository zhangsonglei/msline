package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.page.domain.MusicType;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class MusicTypeDao {

	public int addMusicType(MusicType musicType, Connection conn){
		String sql = "INSERT INTO music_type(music_type_id,music_type_name) values(null,?)";
		Object[] params = new Object[] {
			musicType.getMusic_type_name()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delMusicType(String music_type_id, Connection conn){
		String sql = "DELETE FROM music_type WHERE music_type_id=?";

		Object[] params = new Object[] { new Integer(music_type_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delMusicTypes(String[] music_type_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <music_type_ids.length; i++) {
			sBuilder.append("?");
			if (i !=music_type_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM music_type WHERE music_type_id IN(" +sBuilder.toString()+")";

		Object[] params = music_type_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateMusicType(MusicType musicType, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE music_type SET music_type_id = " + musicType.getMusic_type_id() +" ");
		if (!StringUtil.isEmptyString(musicType.getMusic_type_name())) {
			sBuilder.append(" ,music_type_name = '" + musicType.getMusic_type_name() +"' ");
		}
		sBuilder.append(" where music_type_id = " + musicType.getMusic_type_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public MusicType getMusicType(MusicType musicType, Connection conn){
		MusicType _musicType=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM music_type WHERE 1=1");
		if (musicType.getMusic_type_id()!=0) {
			sBuilder.append(" and music_type_id = " + musicType.getMusic_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(musicType.getMusic_type_name())) {
			sBuilder.append(" and music_type_name = '" + musicType.getMusic_type_name() +"' ");
		}

		List<Object> list = BaseDao.executeQuery(MusicType.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _musicType = (MusicType)list.get(0);
		}
		return _musicType;
	}

	public List<MusicType>  listMusicTypes(MusicType musicType, Connection conn){
		List<MusicType> musicTypes = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM music_type WHERE 1=1");

		if (musicType.getMusic_type_id()!=0) {
			sBuilder.append(" and music_type_id = " + musicType.getMusic_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(musicType.getMusic_type_name())) {
			sBuilder.append(" and music_type_name like '%" + musicType.getMusic_type_name() +"%' ");
		}
		sBuilder.append(" order by music_type_id asc) t");

		if (musicType.getStart() != -1) {
			sBuilder.append(" limit " + musicType.getStart() + "," + musicType.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(MusicType.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			musicTypes = new ArrayList<MusicType>();
			for (Object object : list) {
				musicTypes.add((MusicType)object);
			}
		}
		return musicTypes;
	}

	public int  listMusicTypesCount(MusicType musicType, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM music_type WHERE 1=1");

		if (musicType.getMusic_type_id()!=0) {
			sBuilder.append(" and music_type_id = " + musicType.getMusic_type_id() +" ");
		}
		if (!StringUtil.isEmptyString(musicType.getMusic_type_name())) {
			sBuilder.append(" and music_type_name like '%" + musicType.getMusic_type_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
