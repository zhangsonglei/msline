package com.nkl.admin.action;

import java.util.List;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Music;
import com.nkl.page.domain.MusicType;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.User;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();

	//抓取页面参数
	Music paramsMusic;
	MusicType paramsMusicType;
	User paramsUser;
	Orders paramsOrders;
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);

			setSuccessTip("编辑成功", "modifyInfo.jsp");
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}

			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			paramsUser.setUser_type(1);
			adminManager.addUser(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("添加用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除用户成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listMusicTypes
	 * @Description: 查询音乐类型
	 * @return String
	 */
	public String listMusicTypes(){
		try {
			if (paramsMusicType==null) {
				paramsMusicType = new MusicType();
			}
			
			//设置分页信息
			setPagination(paramsMusicType);
			//总的条数
			int[] sum={0};
			//查询音乐类型列表
			List<MusicType> musicTypes = adminManager.listMusicTypes(paramsMusicType,sum); 
			
			Param.setAttribute("musicTypes", musicTypes);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询音乐类型异常", "main.jsp");
			return "infoTip";
		}
		
		return "musicTypeShow";
	}
	
	/**
	 * @Title: addMusicTypeShow
	 * @Description: 显示添加音乐类型页面
	 * @return String
	 */
	public String addMusicTypeShow(){
		return "musicTypeEdit";
	}
	
	/**
	 * @Title: addMusicType
	 * @Description: 添加音乐类型
	 * @return String
	 */
	public String addMusicType(){
		try {
			//检查音乐类型是否存在
			MusicType musicType = new MusicType();
			musicType.setMusic_type_name(paramsMusicType.getMusic_type_name());
			musicType = adminManager.queryMusicType(musicType);
			if (musicType!=null) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("musicType", paramsMusicType);
				return "musicTypeEdit";
			}
			
			 //添加音乐类型
			adminManager.addMusicType(paramsMusicType);
			
			setSuccessTip("添加成功", "Admin_listMusicTypes.action");
		} catch (Exception e) {
			setErrorTip("添加音乐类型异常", "Admin_listMusicTypes.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editMusicType
	 * @Description: 编辑音乐类型
	 * @return String
	 */
	public String editMusicType(){
		try {
			 //得到音乐类型
			MusicType musicType = adminManager.queryMusicType(paramsMusicType);
			Param.setAttribute("musicType", musicType);
			
		} catch (Exception e) {
			setErrorTip("查询音乐类型异常", "Admin_listMusicTypes.action");
			return "infoTip";
		}
		
		return "musicTypeEdit";
	}
	
	/**
	 * @Title: saveMusicType
	 * @Description: 保存编辑音乐类型
	 * @return String
	 */
	public String saveMusicType(){
		try {
			//检查音乐类型是否存在
			MusicType musicType = new MusicType();
			musicType.setMusic_type_name(paramsMusicType.getMusic_type_name());
			musicType = adminManager.queryMusicType(musicType);
			if (musicType!=null&&musicType.getMusic_type_id()!=paramsMusicType.getMusic_type_id()) {
				tip="失败，该类型已经存在！";
				Param.setAttribute("musicType", paramsMusicType);
				return "musicTypeEdit";
			}
			
			 //保存编辑音乐类型
			adminManager.updateMusicType(paramsMusicType);
			
			setSuccessTip("编辑成功", "Admin_listMusicTypes.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("musicType", paramsMusicType);
			return "musicTypeEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delMusicTypes
	 * @Description: 删除音乐类型
	 * @return String
	 */
	public String delMusicTypes(){
		try {
			 //删除音乐类型
			adminManager.delMusicTypes(paramsMusicType);
			
			setSuccessTip("删除音乐类型成功", "Admin_listMusicTypes.action");
		} catch (Exception e) {
			setErrorTip("删除音乐类型异常", "Admin_listMusicTypes.action");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listMusics
	 * @Description: 查询音乐
	 * @return String
	 */
	public String listMusics(){
		try {
			if (paramsMusic==null) {
				paramsMusic = new Music();
			}
			//设置分页信息
			setPagination(paramsMusic);
			int[] sum={0};
			List<Music> musics = adminManager.listMusics(paramsMusic,sum); 
			
			Param.setAttribute("musics", musics);
			setTotalCount(sum[0]);
			
			//查询音乐类型
			MusicType musicType = new MusicType();
			musicType.setStart(-1);
			List<MusicType> musicTypes = adminManager.listMusicTypes(musicType, null);
			Param.setAttribute("musicTypes", musicTypes);
			
		} catch (Exception e) {
			setErrorTip("查询音乐异常", "main.jsp");
			return "infoTip";
		}
		
		return "musicShow";
	}
	
	/**
	 * @Title: queryMusic
	 * @Description: 查看音乐
	 * @return String
	 */
	public String queryMusic(){
		try {
			 //得到音乐
			Music music = adminManager.queryMusic(paramsMusic);
			Param.setAttribute("music", music);
			
		} catch (Exception e) {
			setErrorTip("查询音乐异常", "Admin_listMusics.action");
			return "infoTip";
		}
		
		return "musicDetail";
	}
	
	/**
	 * @Title: addMusicShow
	 * @Description: 显示添加音乐页面
	 * @return String
	 */
	public String addMusicShow(){
		//查询音乐类型
		MusicType musicType = new MusicType();
		musicType.setStart(-1);
		List<MusicType> musicTypes = adminManager.listMusicTypes(musicType, null);
		Param.setAttribute("musicTypes", musicTypes);
		
		return "musicEdit";
	}
	
	/**
	 * @Title: addMusic
	 * @Description: 添加音乐
	 * @return String
	 */
	public String addMusic(){
		try {
			 //添加音乐
			adminManager.addMusic(paramsMusic);
			
		} catch (Exception e) {
			setErrorTip("添加音乐异常", "Admin_listMusics.action");
		}
		setSuccessTip("添加音乐成功", "Admin_listMusics.action");
		return "infoTip";
	}
	
	/**
	 * @Title: editMusic
	 * @Description: 编辑音乐
	 * @return String
	 */
	public String editMusic(){
		try {
			 //得到音乐
			Music music = adminManager.queryMusic(paramsMusic);
			Param.setAttribute("music", music);
			
			//查询音乐类型
			MusicType musicType = new MusicType();
			musicType.setStart(-1);
			List<MusicType> musicTypes = adminManager.listMusicTypes(musicType, null);
			Param.setAttribute("musicTypes", musicTypes);
			
		} catch (Exception e) {
			setErrorTip("查询音乐异常", "Admin_listMusics.action");
			return "infoTip";
		}
		
		return "musicEdit";
	}
	
	/**
	 * @Title: saveMusic
	 * @Description: 保存编辑音乐
	 * @return String
	 */
	public String saveMusic(){
		try {
			 //保存编辑音乐
			adminManager.updateMusic(paramsMusic);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("music", paramsMusic);
			
			//查询音乐类型
			MusicType musicType = new MusicType();
			musicType.setStart(-1);
			List<MusicType> musicTypes = adminManager.listMusicTypes(musicType, null);
			Param.setAttribute("musicTypes", musicTypes);
			
			return "musicEdit";
		}
		setSuccessTip("编辑音乐成功", "Admin_listMusics.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delMusics
	 * @Description: 删除音乐
	 * @return String
	 */
	public String delMusics(){
		try {
			 //删除音乐
			adminManager.delMusics(paramsMusic);

			setSuccessTip("删除音乐成功", "Admin_listMusics.action");
		} catch (Exception e) {
			setErrorTip("删除音乐异常", "Admin_listMusics.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询订单
	 * @return String
	 */
	public String listOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			setPagination(paramsOrders);
			int[] sum={0};
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询订单异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersShow";
	}
	 
	/**
	 * @Title: delOrderss
	 * @Description: 删除订单
	 * @return String
	 */
	public String delOrderss(){
		try {
			 //删除订单
			adminManager.delOrderss(paramsOrders);

			setSuccessTip("删除订单成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("删除订单异常", "Admin_listOrderss.action");
		}
		return "infoTip";
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
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

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}

}
