package goods;
import java.util.*;

//记住我们是以商品号为主码，只能唯一，不能出现相同的，要控制，同时应该将位数控制在8位数（建议）
//页面调用即可，需要功能再增加
public class Manage {
	private static WaresDAOimpl Merchandise=new WaresDAOimpl();
	//这里面存放的就是浏览页面的数据值，注意在写页面的时候要搭配下面一些函数使用
	private static Vector<wares> goods=new Vector<wares>();
	public Manage() {
		goods.clear();
		goods=Merchandise.SelectAll();
	}
	//获取所有商品记录(浏览）
	public static Vector<wares> Getdatas(){
		if(goods.isEmpty()) {
			goods.clear();
			goods=Merchandise.SelectAll();
		}
		return goods;
	}
	//获取所有商品记录（刷新）
	public static Vector<wares> ReGetdatas(){
		goods.clear();
		goods=Merchandise.SelectAll();
		return goods;
	}
	//得到goods的数据
	public static Vector<wares> Getgoods(){
		return goods;
	}
	//当向数据库里增加数据时，浏览页面也应该增加
	public void addGoods(wares a) {
		if(a!=null) {
			goods.add(a);
		}
	}
	//把数据库里数据删除时，浏览页面也应该删除
	public void deleteGoods(wares b) {
		int nums=-1;
		if(b!=null) {
			for(int i=0;i<goods.size();i++) {
				if(b.GetId().equals(goods.get(i).GetId())) {
					nums=i;
					break;
				}
			}
			if(nums!=-1) {
				goods.remove(nums);
			}
		}
	}
	//更改数据库里的数据时，浏览页面也应该更改
	public void updataGoods(wares c) {
		int nums=-1;
		if(c!=null) {
			for(int i=0;i<goods.size();i++) {
				if(c.GetId().equals(goods.get(i).GetId())) {
					nums=i;
					break;
				}
			}
		}
		if(nums!=-1) {
			goods.set(nums, c);
		}
	}
	//查找商品库通过Id
	public Vector<wares> FindId(String id) {
		if(id.length()==0|| id==null) {
			return null;
		}else {
			Vector<wares> s=Merchandise.SelectById(id);
			return s;
		}
	}
	//查找商品库通过Category
	public Vector<wares> FindCategory(String Category){
		if(Category.length()==0||Category==null) {
			return null;
		}else {
			Vector<wares> s=Merchandise.SelectByCategory(Category);
			return s;
		}
	}
	//查找商品库通过Name
	public Vector<wares> FindName(String Name){
		if(Name.length()==0||Name==null) {
			return null;
		}else {
			Vector<wares> s=Merchandise.SelectByName(Name);
			return s;
		}
	}
	//增加商品库记录，-1代表记录存在，1代表成功，0代表传入值为空
	public int AddRecord(wares s) {
		int nums=-1;
		if(s==null) {
			nums=0;
		}
		if(Merchandise.addWares(s)) {
			addGoods(s);
			nums=1;
		}
		return nums;
	}
	//修改商品库记录,-1空记录，0代表传入值为空，1代表成功，通过id来查找
	public int UpdataRecord(wares s) {
		int nums=-1;
		if(s==null) {
			nums=0;
		}
		if(Merchandise.UpdateWares(s)) {
			updataGoods(s);
			nums=1;
		}
		return nums;
	}
	//删除商品库记录，-1是记录，0代表传入值为空，1代表成功
	public int DeleteRecord(wares s) {
		int nums=-1;
		if(s==null) {
			nums=0;
		}
		if(Merchandise.DeleteWares(s)) {
			deleteGoods(s);
			nums=1;
		}
		return nums;
	}
}