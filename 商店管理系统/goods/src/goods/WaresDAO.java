package goods;
import java.util.Vector;
//定义接口实现
public interface WaresDAO{
	//增加数据、更新数据、删除数据、查找数据
	public boolean addWares(wares a);
	public boolean UpdateWares(wares b);
	public boolean DeleteWares(wares c);
	public Vector<wares> SelectAll();
	public Vector<wares> SelectByCategory(String Category);
	public Vector<wares> SelectById(String id);
	public Vector<wares> SelectByName(String Name);
}

