import java.util.HashMap;

public class Products implements java.io.Serializable{
    private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
    private String category = "OTHER";
	private double rdiscount;
    private double rwarranty;
    
    private HashMap<String,String> accessories = new HashMap<String,String>();

    public Products(String name, double price, String image, String retailer,String condition,double discount, HashMap<String,String> accessories){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
		this.setAccessories(accessories);
	}
	
   public Products(String name, double price,double discount){
		this.name=name;
		this.price=price;
		this.discount = discount;
    }

    public Products(){

    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category) {
		this.category = category;
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

	public void setAccessories(HashMap<String,String> accessories) {
		this.accessories = accessories;
	}

	public HashMap<String,String> getAccessories() {
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
}