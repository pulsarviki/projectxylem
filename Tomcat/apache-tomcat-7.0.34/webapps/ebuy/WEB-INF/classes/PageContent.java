import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.*;
import javax.servlet.http.*;

public class PageContent {

	public static HashMap<String, Product> products = new HashMap<String, Product>();
public static HashMap<String, Users> users = new HashMap<String, Users>();
public static HashMap<String, Order> orders = new HashMap<String, Order>();
//public static HashMap<String, CartItem> cartItems = new HashMap<String, CartItem>();

	HttpServletRequest request;
	private String CONTENT;
	private String BASICS;
	private String HEADER;
	private String CUSTOMER_HEADER;
	private String DEFAULT_HEADER;
	private String SALESMAN_HEADER;
	private String STOREMANAGER_HEADER;
	private String RETAILER_HEADER;
	private String SIDEBAR;
	private String FOOTER;


	PageContent(){

	this.CONTENT = "<div id=\"content\">"+
	"<div class=\"login\">"+
	"<h1>Please login to continue.</h1>"+
	"<br/>"+
	"<form action=\"LoginPage\" method=\"post\">"+
    		"<div class=\"input\">"+
		"      <div class=\"blockinput\">"+
		"        <i class=\"icon-envelope-alt\"></i><input type=\"text\" name=\"username\" placeholder=\"Username\">"+
		"      </div><br>"+
		"      <div class=\"blockinput\">"+
		"        <i class=\"icon-unlock\"></i><input type=\"password\" placeholder=\"Password\" name=\"password\">"+
		"      </div><br>"+

		"      <div class=\"blockinput\">"+
		"        <i class=\"icon-unlock\"></i>"+
				"<select name=\"utype\">"+
				"    <option value=\"CUSTOMER\">CUSTOMER</option>"+
				"    <option value=\"STOREMANAGER\">STOREMANAGER</option>"+
				"    <option value=\"SALESMAN\">SALESMAN</option>"+
				"    <option value=\"RETAILER\">RETAILER</option>"+
				"  </select>"+
		"      </div>"+

		"    </div>"+
		"<button type=\"submit\" value=\"Login\">Login</button>"+
		"<br/>"+
		"<a href=\"#\">Forgot Password?</a>"+
	"  </form></div>"+
	"        </div>";

	this.BASICS = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\""+
"	\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"+
"<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">"+
"<head>"+
"<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"+
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>"+
"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"+
"<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/940.css\" />"+
"<title>No Images Template</title>"+
"</head>"+
"<body>"+
""+
"<div id=\"wrap\">"+
""+
"<div id=\"header\">"+
"<h1><a href=\"#\">ebuy.com</a></h1>"+
"</div>";

this.DEFAULT_HEADER  = "<div id=\"menu\">"+
"<ul>"+
"<li><a href=\"/ebuy/HomePage\">Home</a></li>"+
"<li><a href=\"940px.html\">About Us</a></li>"+
"<li><a href=\"/ebuy/RegisterPage\">Register</a></li>"+
"<li><a href=\"/ebuy/LoginPage\">Login</a></li>"+
"</ul>"+
"</div>";

	this.HEADER  = "<div id=\"menu\">"+
"<ul>"+
"<li><a href=\"/ebuy/HomePage\">Home</a></li>"+
"<li><a href=\"940px.html\">About Us</a></li>"+
"<li><a href=\"/ebuy/RegisterPage\">Register</a></li>"+
"<li><a href=\"/ebuy/LoginPage\">Login</a></li>"+
"</ul>"+
"</div>";

this.CUSTOMER_HEADER  = "";

this.SALESMAN_HEADER  = "";

this.STOREMANAGER_HEADER  = "";

this.RETAILER_HEADER  = "";

	this.SIDEBAR = "<div id=\"sidebar\">"+
"<h3>Menu Navigation</h3>"+
"<ul>"+
"<li><a href=\"/ebuy/HomePage?cat=Smartwatches\">Smart Watches</a></li>"+
"<li><a href=\"/ebuy/HomePage?cat=Speakers\">Speakers</a></li>"+
"<li><a href=\"/ebuy/HomePage?cat=Headphones\">Headphones</a></li>"+
"<li><a href=\"/ebuy/HomePage?cat=Phones\">Phones</a></li>"+
"<li><a href=\"/ebuy/HomePage?cat=Laptops\">Laptops</a></li>"+
"<li><a href=\"/ebuy/HomePage?cat=Externalstorage\">External Storage</a></li>"+
"<li><a href=\"/ebuy/HomePage?cat=Others\">Others</a></li>"+
"</ul>"+
""+
"</div>"+
""+
"<div style=\"clear: both;\"> </div>"+
""+
""+
"<div style=\"clear: both;\"> </div>"+
""+
"</div>";


    this.FOOTER = "<div id=\"footer\">"+
"<p>Copyright 2010 You | Design by <a href=\"http://www.blogliber.com\">Blog Liber</a></p>"+
"</div>"+
""+
"</div>"+
""+
"</body>"+
"</html>";
}

public static void  writeToFile(Object obj, String filename){
		File ifile=new File(filename);

	    try{
	    FileOutputStream fos=new FileOutputStream(ifile);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        	oos.writeObject(obj);
	        oos.flush();
	        oos.close();
	        fos.close();
	    }catch(Exception e){
			System.out.println("Error writing obj.");
		}

	}

	// Read the HashMaps from the File GameSpeedDataStore
	public static Object readFromFile(String filename) {
		Object obj = null;
	    try{
	        File filei=new File(filename);
	        FileInputStream fis=new FileInputStream(filei);
	        ObjectInputStream ois=new ObjectInputStream(fis);

	        obj=ois.readObject();
	        //	        ArrayList<String> mArray = (ArrayList<String>) ois.readObject();
	        ois.close();
	        fis.close();

	    }catch(Exception e){
	    	System.out.println("Error reading obj.");
	    }
	    return obj;
	}

  public void setContent( String content) {
  	this.CONTENT = "<div id=\"content\">"+
					//	"<article>"+
						content+
					//	"</article>"+
					"</div>";
  }

	public int getProductsCount(HttpSession session)
	{
		int cartacc_count,cartproducts_count;
		HashMap<String, Product> cartproducts = (HashMap<String, Product>) session.getAttribute("cart");
		HashMap<String, Accessory> cartacc = (HashMap<String, Accessory>) session.getAttribute("cartacc");
		if(cartacc==null)
		 {
			 cartacc_count=0;
		 }
		 else{
			 cartacc_count=cartacc.size();
		 }
		 if(cartproducts==null)
		 {
			 cartproducts_count=0;
		 }
		 else{
			 cartproducts_count=cartproducts.size();
		 }
		 return cartproducts_count+cartacc_count;

	}

  public void setHeader( String header, String username, int product_count) {
		if(header==null){
	  	this.HEADER = this.DEFAULT_HEADER;
	  }
		else if(header.toUpperCase().equals("CUSTOMER")){
	  	this.HEADER = "<div id=\"menu\">"+
			"<ul>"+
			"<li><a href=\"/ebuy/HomePage\">Home</a></li>"+
			"<li><a href=\"940px.html\">My Account</a></li>"+
			"<li><a href=\"without730px.html\">My Orders</a></li>"+
			"<li><a href=\"#\">Track Order</a></li>"+
			"<li><a href=\"/ebuy/LogoutPage\">Logout</a></li>"+
			"</ul>"+
			"<div style=\"float: right\">Welcome, <a style=\"color:white\" href=\"#\"> "+username+"</a> &nbsp; <a style=\"color:white\" href=\"/ebuy/CartPage\"> Cart("+product_count+")</a></div>"+
			"</div>";
	  }
		else if(header.toUpperCase().equals("STOREMANAGER")){
	  	this.HEADER = "<div id=\"menu\">"+
			"<ul>"+
			"<li><a href=\"/ebuy/HomePage\">Home</a></li>"+
			"<li><a href=\"940px.html\">My Account</a></li>"+
			"<li><a href=\"#\">Manage Products</a></li>"+
			"<li><a href=\"/ebuy/LogoutPage\">Logout</a></li>"+
			"</ul>"+
			"<div style=\"float: right\">Welcome, <a style=\"color:white\" href=\"#\"> "+username+"</a> &nbsp;</div>"+
			"</div>";
	  }
		else if(header.toUpperCase().equals("RETAILER")){
	  	this.HEADER = "<div id=\"menu\">"+
			"<ul>"+
			"<li><a href=\"/HomePage\">Home</a></li>"+
			"<li><a href=\"940px.html\">My Account</a></li>"+
			"<li><a href=\"#\">Manage Offers</a></li>"+
			"<li><a href=\"/ebuy/LogoutPage\">Logout</a></li>"+
			"</ul>"+
			"<div style=\"float: right\">Welcome, <a style=\"color:white\" href=\"#\"> "+username+"</a> &nbsp;</div>"+
			"</div>";
	  }
		else if(header.toUpperCase().equals("SALESMAN")){
	  	this.HEADER = "<div id=\"menu\">"+
			"<ul>"+
			"<li><a href=\"/ebuy/HomePage\">Home</a></li>"+
			"<li><a href=\"940px.html\">My Account</a></li>"+
			"<li><a href=\"without730px.html\">Create Account</a></li>"+
			"<li><a href=\"#\">Manage Orders</a></li>"+
			"<li><a href=\"/ebuy/LogoutPage\">Logout</a></li>"+
			"</ul>"+
			"<div style=\"float: right\">Welcome, <a style=\"color:white\" href=\"#\"> "+username+"</a> &nbsp;</div>"+
			"</div>";
	  }
		else{
	  	this.HEADER = this.DEFAULT_HEADER;
	  }
	}

  public String getBasics() {
  	 return BASICS;
  }

  public String getHeader() {
  	return HEADER;
  }

  public String getSideBar() {
  	 return SIDEBAR;
  }

  public String getFooter() {
  	return FOOTER;
  }

  public String getContent() {
  	return CONTENT;
  }

  public String getPageContent() {
  	return getBasics() + getHeader()  + getContent() + getSideBar() + getFooter();
  }

	public static HashMap<String, Product> getProducts() {
		return products;
	}

	public static void setProducts(HashMap<String, Product> products) {
		PageContent.products = products;
	}

	public static HashMap<String, Users> getUsers() {
		return users;
	}

	public static void setUsers(HashMap<String, Users> users) {
		PageContent.users = users;
	}

	public static HashMap<String, Order> getOrders() {
		return orders;
	}

	public static void setOrders(HashMap<String, Order> orders) {
		PageContent.orders = orders;
	}

	// public static HashMap<String, CartItem> getCartItems() {
	// 	return cartItems;
	// }
	//
	// public static void setCartItems(HashMap<String, CartItem> cartItems) {
	// 	PageContent.cartItems = cartItems;
	// }

}
