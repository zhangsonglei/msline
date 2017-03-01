package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.MusicDao;
import com.nkl.page.dao.MusicTypeDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Music;
import com.nkl.page.domain.MusicType;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.User;

public class AdminManager {

	UserDao userDao = new UserDao();
	MusicTypeDao musicTypeDao = new MusicTypeDao();
	MusicDao musicDao = new MusicDao();
	OrdersDao ordersDao = new OrdersDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	 
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		Connection conn = BaseDao.getConnection();
		user.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listMusicTypes
	 * @Description: 音乐类型查询
	 * @param musicType
	 * @return List<MusicType>
	 */
	public List<MusicType> listMusicTypes(MusicType musicType, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = musicTypeDao.listMusicTypesCount(musicType, conn);
		}
		List<MusicType> musicTypes = musicTypeDao.listMusicTypes(musicType, conn);

		BaseDao.closeDB(null, null, conn);
		return musicTypes;
	}

	/**
	 * @Title: queryMusicType
	 * @Description: 音乐类型查询
	 * @param musicType
	 * @return MusicType
	 */
	public MusicType queryMusicType(MusicType musicType) {
		Connection conn = BaseDao.getConnection();
		MusicType _musicType = musicTypeDao.getMusicType(musicType, conn);
		BaseDao.closeDB(null, null, conn);
		return _musicType;
	}

	/**
	 * @Title: addMusicType
	 * @Description: 添加音乐类型
	 * @param musicType
	 * @return void
	 */
	public void addMusicType(MusicType musicType) {
		Connection conn = BaseDao.getConnection();
		musicTypeDao.addMusicType(musicType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateMusicType
	 * @Description: 更新音乐类型信息
	 * @param musicType
	 * @return void
	 */
	public void updateMusicType(MusicType musicType) {
		Connection conn = BaseDao.getConnection();
		musicTypeDao.updateMusicType(musicType, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delMusicType
	 * @Description: 删除音乐类型信息
	 * @param musicType
	 * @return void
	 */
	public void delMusicTypes(MusicType musicType) {
		Connection conn = BaseDao.getConnection();
		musicTypeDao.delMusicTypes(musicType.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 音乐订单查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders> listOrderss(Orders orders, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = ordersDao.listOrderssCount(orders, conn);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders, conn);

		BaseDao.closeDB(null, null, conn);
		return orderss;
	}

	/**
	 * @Title: queryOrders
	 * @Description: 音乐订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders queryOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		Orders _orders = ordersDao.getOrders(orders, conn);
		BaseDao.closeDB(null, null, conn);
		return _orders;
	}

	/**
	 * @Title: addOrders
	 * @Description: 添加音乐订单
	 * @param orders
	 * @return void
	 */
	public void addOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		ordersDao.addOrders(orders, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateOrders
	 * @Description: 更新音乐订单信息
	 * @param orders
	 * @return void
	 */
	public void updateOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		ordersDao.updateOrders(orders, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delOrders
	 * @Description: 删除音乐订单信息
	 * @param orders
	 * @return void
	 */
	public void delOrderss(Orders orders) {
		Connection conn = BaseDao.getConnection();
		ordersDao.delOrderss(orders.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listMusics
	 * @Description: 音乐查询
	 * @param music
	 * @return List<Music>
	 */
	public List<Music> listMusics(Music music, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = musicDao.listMusicsCount(music, conn);
		}
		List<Music> musics = musicDao.listMusics(music, conn);

		BaseDao.closeDB(null, null, conn);
		return musics;
	}

	/**
	 * @Title: queryMusic
	 * @Description: 音乐查询
	 * @param music
	 * @return Music
	 */
	public Music queryMusic(Music music) {
		Connection conn = BaseDao.getConnection();
		Music _music = musicDao.getMusic(music, conn);
		BaseDao.closeDB(null, null, conn);
		return _music;
	}

	/**
	 * @Title: addMusic
	 * @Description: 添加音乐
	 * @param music
	 * @return void
	 */
	public void addMusic(Music music) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(music.getMusic_desc())) {
			music.setMusic_desc(Transcode.htmlEncode(music.getMusic_desc()));
		}
		music.setMusic_date(DateUtil.dateToDateString(new Date()));
		musicDao.addMusic(music, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateMusic
	 * @Description: 更新音乐信息
	 * @param music
	 * @return void
	 */
	public void updateMusic(Music music) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(music.getMusic_desc())) {
			music.setMusic_desc(Transcode.htmlEncode(music.getMusic_desc()));
		}
		musicDao.updateMusic(music, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delMusic
	 * @Description: 删除音乐信息
	 * @param music
	 * @return void
	 */
	public void delMusics(Music music) {
		Connection conn = BaseDao.getConnection();
		musicDao.delMusics(music.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
}
