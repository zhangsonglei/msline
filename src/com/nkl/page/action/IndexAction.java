package com.nkl.page.action;

import java.util.List;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Music;
import com.nkl.page.domain.MusicType;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

public class IndexAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	IndexManager indexManager = new IndexManager();

	//抓取页面参数
	Music paramsMusic;
	MusicType paramsMusicType;
	User paramsUser;
	Orders paramsOrders;
	String tip;
	
	/**
	 * @Title: index
	 * @Description: 展示音乐列表
	 * @return String
	 */
	public String index(){
		try {
			//查询试听排行榜
			Music music = new Music();
			music.setStart(0);
			music.setLimit(8);
			music.setTop_flag(1);
			List<Music> musics = indexManager.listMusicsTop(music);
			Param.setAttribute("musics", musics);
			
			//查询下载排行榜
			music.setTop_flag(2);
			List<Music> musics2 = indexManager.listMusicsTop(music);
			Param.setAttribute("musics2", musics2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "index";
	}
	
	/**
	 * @Title: listMusics
	 * @Description: 展示音乐列表
	 * @return String
	 */
	public String listMusics(){
		try {
			//查询音乐信息集合
			if (paramsMusic==null) {
				paramsMusic = new Music();
			}
			//分页信息设置
			setPagination(paramsMusic);
			int[] sum={0};
			List<Music> musics = indexManager.listMusics(paramsMusic,sum); 
			Param.setAttribute("musics", musics);
			setTotalCount(sum[0]);
			
			//查询音乐类型
			MusicType musicType = new MusicType();
			musicType.setStart(-1);
			List<MusicType> musicTypes = indexManager.listMusicTypes(musicType, null);
			Param.setAttribute("musicTypes", musicTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "music";
	}
	
	/**
	 * @Title: listMusicsNew
	 * @Description: 展示最新歌曲列表
	 * @return String
	 */
	public String listMusicsNew(){
		try {
			//查询音乐信息集合
			if (paramsMusic==null) {
				paramsMusic = new Music();
			}
			//分页信息设置
			setPagination(paramsMusic);
			int[] sum={0};
			//最新歌曲
			paramsMusic.setTop_flag(3);
			List<Music> musics = indexManager.listMusics(paramsMusic,sum); 
			Param.setAttribute("musics", musics);
			setTotalCount(sum[0]);
			
			//查询音乐类型
			MusicType musicType = new MusicType();
			musicType.setStart(-1);
			List<MusicType> musicTypes = indexManager.listMusicTypes(musicType, null);
			Param.setAttribute("musicTypes", musicTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "musicNew";
	}
	
	/**
	 * @Title: queryMusic
	 * @Description: 查询音乐详情
	 * @return String
	 */
	public String queryMusic(){
		try {
			 //得到音乐
			Music music = indexManager.queryMusic(paramsMusic);
			Param.setAttribute("music", music);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "musicDetail";
	}
	 
	/**
	 * @Title: addOrders
	 * @Description: 购买音乐
	 * @return String
	 */
	public String addOrders(){
		try {
			 //查询是否购买过
			Orders orders = indexManager.queryOrders(paramsOrders);
			if (orders!=null) {
				setErrorReason("您已经购买了此音乐，无需重复购买，您可以在【个人中心-我的音乐订单】界面试听下载");
				return "error2";
			}
			//添加音乐订单
			indexManager.addOrders(paramsOrders);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("查询订单失败："+e.getMessage());
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: saveUserFront
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveUserFront(){
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = new User();
			userFront.setUser_id(paramsUser.getUser_id());
			userFront = indexManager.getUser(userFront);
			Param.setSession("userFront", userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userInfo";
		}
		tip = "修改成功";
		return "userInfo";
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveUserFrontPass(){
		try {
			 //保存修改个人密码
			indexManager.updateUser(paramsUser);
			//更新session
			User UserFront = (User)Param.getSession("UserFront");
			if (UserFront!=null) {
				UserFront.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("UserFront", UserFront);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userPwd";
		}
		tip = "修改成功";
		return "userPwd";
	}
	
	/**
	 * @Title: listMyOrderss
	 * @Description: 展示音乐订单列表
	 * @return String
	 */
	public String listMyOrderss(){
		try {
			//查询音乐信息集合
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//分页信息设置
			setPagination(paramsOrders);
			int[] sum={0};
			//用户身份限制
			User userFront = (User)Param.getSession("userFront");
			paramsOrders.setUser_id(userFront.getUser_id());
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myOrdersShow";
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	public String myInfo(){
		return "userInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	public String myPwd(){
		return "userPwd";
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Music getParamsMusic() {
		return paramsMusic;
	}

	public void setParamsMusic(Music paramsMusic) {
		this.paramsMusic = paramsMusic;
	}

	public MusicType getParamsMusicType() {
		return paramsMusicType;
	}

	public void setParamsMusicType(MusicType paramsMusicType) {
		this.paramsMusicType = paramsMusicType;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}


}
