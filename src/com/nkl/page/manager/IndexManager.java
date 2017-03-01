package com.nkl.page.manager;

import java.sql.Connection;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.MusicDao;
import com.nkl.page.dao.MusicTypeDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Music;
import com.nkl.page.domain.MusicType;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.User;

public class IndexManager {

	UserDao userDao = new UserDao();
	MusicTypeDao musicTypeDao = new MusicTypeDao();
	MusicDao musicDao = new MusicDao();
	OrdersDao ordersDao = new OrdersDao();
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
	 * @Title: listMusics
	 * @Description: 查询音乐信息
	 * @param music
	 * @return List<Music>
	 */
	public List<Music>  listMusics(Music music,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = musicDao.listMusicsCount(music, conn);
		}
		List<Music> musics = musicDao.listMusics(music,conn);
		BaseDao.closeDB(null, null, conn);
		return musics;
	}
	
	public List<Music>  listMusicsTop(Music music){
		Connection conn = BaseDao.getConnection();
		List<Music> musics = musicDao.listMusics(music,conn);
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
		//音乐查询
		Music _music = musicDao.getMusic(music, conn);
		//更新次数
		_music.setMusic_click(_music.getMusic_click()+1);
		musicDao.updateMusicClick(_music, conn);
		
		BaseDao.closeDB(null, null, conn);
		return _music;
	}
	
	/**
	 * @Title: updateMusicDown
	 * @Description: 音乐更新下载次数
	 * @param music
	 * @return Music
	 */
	public Music updateMusicDown(Music music) {
		Connection conn = BaseDao.getConnection();
		//音乐查询
		Music _music = musicDao.getMusic(music, conn);
		//更新次数
		_music.setMusic_down(_music.getMusic_down()+1);
		musicDao.updateMusicDown(_music, conn);
		
		BaseDao.closeDB(null, null, conn);
		return _music;
	}
	
	/**
	 * @Title: queryMusicLrc
	 * @Description: 音乐歌词查询
	 * @param music
	 * @return Music
	 */
	public Music queryMusicLrc(Music music) {
		Connection conn = BaseDao.getConnection();
		Music _music = musicDao.getMusic(music, conn);
		BaseDao.closeDB(null, null, conn);
		return _music;
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
		orders.setOrders_date(DateUtil.getCurDateTime());
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
	
	
	   
}
