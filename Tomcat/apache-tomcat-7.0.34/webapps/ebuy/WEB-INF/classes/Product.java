
import java.util.HashMap;

public class Product implements java.io.Serializable{

	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;

	private double rdiscount;
	private double rwarranty;
	private String category = "OTHERS";

	private int retailerZip;
	private String retailerCity;
	private String retailerState;
	private String productOnSale;
	private String manufacturerName;

	 private HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();

	public Product(String name, double price, String image, String retailer,String condition,double discount , HashMap<String,Accessory> accessories){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
		this.setAccessories(accessories);
	}

   public Product(String name, double price,double discount){
		this.name=name;
		this.price=price;
		this.discount = discount;
	}

   public Product(String name, double price,double discount, String category){
		this.name=name;
		this.price=price;
		this.discount = discount;
		this.category = category;
	}

	public Product(String name, double price,double discount, String category, double rdiscount, double rwarranty ){
	 this.name=name;
	 this.price=price;
	 this.discount = discount;
	 this.category = category;
	 this.rwarranty = rwarranty;
	 this.rdiscount = rdiscount;
 }

   public Product(String name, double price,double discount, String category,String retailer, int retailerZip, String retailerCity, String retailerState,  String productOnSale, String manufacturerName){
		this.name=name;
		this.price=price;
		this.discount = discount;
		this.category = category;

		this.retailer=retailer;
		this.retailerZip=retailerZip;
		this.retailerCity=retailerCity;
		this.retailerState =retailerState;
		this.productOnSale = productOnSale;
		this.manufacturerName = manufacturerName;
	}

	public Product(){

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setAccessories(HashMap<String,Accessory> accessories) {
		this.accessories = accessories;
	}

	public HashMap<String,Accessory> getAccessories() {
		return accessories;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getRdiscount() {
		return rdiscount;
	}

	public void setRdiscount(double rdiscount) {
		this.rdiscount = rdiscount;
	}


	public double getRwarranty() {
		return rwarranty;
	}

	public void setRwarranty(double rwarranty) {
		this.rwarranty = rwarranty;
	}

	public int getRetailerZip() {
		return retailerZip;
	}

	public void setRetailerZip(int retailerZip) {
		this.retailerZip = retailerZip;
	}

	public String getRetailerCity() {
		return retailerCity;
	}

	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}

	public String getRetailerState() {
		return retailerState;
	}

	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}


	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getProductOnSale() {
		return productOnSale;
	}

	public void setProductOnSale(String productOnSale) {
		this.productOnSale = productOnSale;
	}

}
