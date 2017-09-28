import java.util.*;
import java.text.*;

public class Order implements java.io.Serializable{
	private String id;
	private String description;
	private String buyer;
	private String retailer;
	private double price;
  private HashMap<String,Product> products;
  private HashMap<String,Accessory> accessories;

	private String status = "ORDERED";
	private Date orderDate = new Date();

	public Order(String id, String description, String buyer, String status){
		this.setId(id);
		this.setDescription(description);
		this.setBuyer(buyer);
		this.setStatus(status);
	}

	public Order(){
    this.products = new HashMap<String,Product>();
    this.accessories = new HashMap<String,Accessory>();
  }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

  public HashMap<String, Accessory> getAccessories() {
		return this.accessories;
	}

	public void setAccessories(HashMap<String, Accessory> accessories) {
		this.accessories = accessories;
	}

  public HashMap<String, Product> getProducts() {
		return this.products;
	}

	public void setProducts(HashMap<String, Product> products) {
		this.products = products;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

  public static String orderIdGenerator()
  {
    Date dNow = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
    String datetime = ft.format(dNow);
    return datetime;
  }


}
