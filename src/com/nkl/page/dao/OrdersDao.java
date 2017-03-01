package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Orders;

public class OrdersDao {

	public int addOrders(Orders orders, Connection conn){
		String sql = "INSERT INTO orders(orders_id,music_id,user_id,music_price,orders_date) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			orders.getMusic_id(),
			orders.getUser_id(),
			orders.getMusic_price(),
			orders.getOrders_date()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delOrders(String orders_id, Connection conn){
		String sql = "DELETE FROM orders WHERE orders_id=?";

		Object[] params = new Object[] { new Integer(orders_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delOrderss(String[] orders_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <orders_ids.length; i++) {
			sBuilder.append("?");
			if (i !=orders_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM orders WHERE orders_id IN(" +sBuilder.toString()+")";

		Object[] params = orders_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateOrders(Orders orders, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE orders SET orders_id = " + orders.getOrders_id() +" ");
		
		sBuilder.append(" where orders_id = " + orders.getOrders_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public Orders getOrders(Orders orders, Connection conn){
		Orders _orders=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT b.*,m.music_name,bt.music_type_name,u.real_name,u.nick_name FROM orders b ");
		sBuilder.append("  join music m on b.music_id=m.music_id ");
		sBuilder.append("  join music_type bt on m.music_type_id=bt.music_type_id ");
		sBuilder.append("  join user u on u.user_id=b.user_id WHERE 1=1");
		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and b.orders_id = " + orders.getOrders_id() +" ");
		}
		if (orders.getUser_id()!=0) {
			sBuilder.append(" and b.user_id = " + orders.getUser_id() +" ");
		}
		if (orders.getMusic_id()!=0) {
			sBuilder.append(" and b.music_id = " + orders.getMusic_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Orders.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _orders = (Orders)list.get(0);
		}
		return _orders;
	}

	public List<Orders>  listOrderss(Orders orders, Connection conn){
		List<Orders> orderss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT b.*,m.music_name,bt.music_type_name,u.real_name,u.nick_name FROM orders b ");
		sBuilder.append("  join music m on b.music_id=m.music_id ");
		sBuilder.append("  join music_type bt on m.music_type_id=bt.music_type_id ");
		sBuilder.append("  join user u on u.user_id=b.user_id WHERE 1=1");

		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and b.orders_id = " + orders.getOrders_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getMusic_name())) {
			sBuilder.append(" and m.music_name like '%" + orders.getMusic_name() +"%' ");
		}
		if (orders.getUser_id()!=0) {
			sBuilder.append(" and b.user_id = " + orders.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + orders.getReal_name() +"%' ");
		}
		sBuilder.append(" order by orders_date desc,orders_id asc) t");

		if (orders.getStart() != -1) {
			sBuilder.append(" limit " + orders.getStart() + "," + orders.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Orders.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			orderss = new ArrayList<Orders>();
			for (Object object : list) {
				orderss.add((Orders)object);
			}
		}
		return orderss;
	}

	public int  listOrderssCount(Orders orders, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM orders b ");
		sBuilder.append("  join music m on b.music_id=m.music_id ");
		sBuilder.append("  join music_type bt on m.music_type_id=bt.music_type_id ");
		sBuilder.append("  join user u on u.user_id=b.user_id WHERE 1=1");

		if (orders.getOrders_id()!=0) {
			sBuilder.append(" and b.orders_id = " + orders.getOrders_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getMusic_name())) {
			sBuilder.append(" and m.music_name like '%" + orders.getMusic_name() +"%' ");
		}
		if (orders.getUser_id()!=0) {
			sBuilder.append(" and b.user_id = " + orders.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(orders.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + orders.getReal_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
}
