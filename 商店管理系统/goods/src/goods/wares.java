package goods;
import java.io.Serializable;
//定义商品类
public class wares implements Serializable{
	//序列化，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，
	//如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。
	//用来判断版本一致
	private static final long serialVersionUID=1L;
	//商品编号、名称、类别、售价、数量
	private String id;
	private String name;
	private String category;
	private String price;
	private String amount;
	wares(){}
	wares(String id,String name,String category,String price,String amount){
		this.id=id;
		this.name=name;
		this.category=category;
		this.price=price;
		this.amount=amount;
	}
	public void SetId(String id) {
		this.id=id;
	}
    public  String GetId () {
    	return this.id;
    }
    public  void SetName(String name) {
    	this.name=name;
    }
    public String GetName() {
    	return this.name;
    }
    public void SetCategory(String category) {
    	this.category=category;
    }
	public String GetCategory() {
		return this.category;
	}
	public void SetPrice(String price) {
		this.price=price;
	}
	public String GetPrice() {
		return this.price;
	}
	public void SetAmount(String amount) {
		this.amount=amount;
	}
	public String GetAmount() {
		return this.amount;
	}
	@Override
	public String toString() {
		return "商品编号["+this.id+"]  商品名字["+this.name+"]  商品类别["+this.category+"]  商品价格["+this.price+"] 商品数量["+this.amount+"]";         
	}
}
