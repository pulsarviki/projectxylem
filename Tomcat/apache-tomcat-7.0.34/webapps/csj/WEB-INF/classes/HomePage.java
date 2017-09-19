import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Map.Entry;


public class HomePage extends HttpServlet {
  
  private CommonUtils store = new CommonUtils();

  public void prinProducttMap(HashMap<String, Product> mapInFile) throws ServletException {
	  
      /*for(Entry<String, Product> m :mapInFile.entrySet()){
			  	System.out.println(m.getKey());
			Product c = m.getValue();
			System.out.println("\t Name : "+c.getName());
			System.out.println("\t Price : "+c.getPrice());
			System.out.println("\t Accessories : ");
			HashMap<String,Accessory> accessories = c.getAccessories();
			for(Entry<String, Accessory> ma :accessories.entrySet()){
				System.out.println("\t\t\t" + ma.getKey());
				Accessory a = ma.getValue();
				System.out.println("\t\t\t\t Name : "+a.getName());
				System.out.println("\t\t\t\t Price : "+a.getPrice());
			}
			
	   }*/
  }
  
  public String buildString(Entry<String, Product> m, String contentStr) throws ServletException {
	  Product c = m.getValue();
	  contentStr = contentStr +
				"<div class=\"prod_box_big\">"+
			"        <div class=\"top_prod_box_big\"></div>"+
			"        <div class=\"center_prod_box_big\">"+
			"          <div class=\"product_img_big\"> <a href=\"javascript:popImage('images/big_pic.jpg','Some Title')\" title=\"\"><img src=\"images/laptop.gif\" alt=\"\" border=\"0\"></a>"+
			"            <div class=\"thumbs\"> <a href=\"#\" title=\"\"><img src=\"images/thumb1.gif\" alt=\"\" border=\"0\"></a> <a href=\"#\" title=\"header=[Thumb2] body=[ ] fade=[on]\"><img src=\"images/thumb1.gif\" alt=\"\" border=\"0\"></a> <a href=\"#\" title=\"header=[Thumb3] body=[ ] fade=[on]\"><img src=\"images/thumb1.gif\" alt=\"\" border=\"0\"></a> </div>"+
			"          </div>"+
			"          <div class=\"details_big_box\">"+
			"            <div class=\"product_title_big\">"+m.getKey()+"</div>"+
			"            <div class=\"specifications\"> Category: <span class=\"blue\">"+c.getCategory()+"</span><br>"+
			"              rebate: <span class=\"blue\">"+c.getDiscount()+"</span><br>"+
			"              Discount: <span class=\"blue\">"+c.getDiscount()+" %</span><br>"+
			"            </div>"+
			"            <div class=\"prod_price_big\"> price : <span class=\"price\">$"+c.getPrice()+"</span></div>"+
			"            <a href=\"#\" class=\"addtocart\">add to cart</a> <a href=\"#\" class=\"compare\">Buy</a> </div>"+
			"        </div>";
			  	HashMap<String,Accessory> accessories = c.getAccessories();
			  	if(!accessories.isEmpty()) {
			  		contentStr = contentStr +"<hr><h4 style=\"background-color: #e9e9e9;padding-left: 13px;padding-top: 8px;\"> Accessories</h4> <hr>";
			  	}
				for(Entry<String, Accessory> ma :accessories.entrySet()){
					Accessory a = ma.getValue();
					contentStr = contentStr +"<div class=\"prod_box\">"+
							"        <div class=\"top_prod_box\"></div>"+
							"        <div class=\"center_prod_box\">"+
							"          <div class=\"product_title\"><a href=\"details.html\">"+c.getName()+"</a></div>"+
							"          <div class=\"product_img\"><a href=\"details.html\"><img src=\"images/laptop.gif\" alt=\"\" border=\"0\"></a></div>"+
							"             <div class=\"specifications\"> Discount: <span class=\"blue\">"+c.getDiscount()+" %</span><br></div>"+
							
							"          <div class=\"prod_price\">price : <span class=\"price\">$"+c.getPrice()+"</span></div>"+
							"        </div>"+
							"        <div class=\"bottom_prod_box\"></div>"+
							"        <div class=\"prod_details_tab\"> <a href=\"#\" title=\"Add to cart\"><img src=\"images/cart.gif\" alt=\"\" border=\"0\" class=\"left_bt\"></a>  <a href=\"details.html\" class=\"prod_details\">Buy Now</a> </div>"+
							"      </div>";
							
							
				}		
	
						contentStr = contentStr +	"        <div class=\"bottom_prod_box_big\"></div>"+
							"      </div>";
		return contentStr;				
  }
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    HashMap<String, Product> products = (HashMap<String, Product>) store.readFromFile("products");
    prinProducttMap(products);
    
    String cat = request.getParameter("cat");
    Category category = Category.OTHER;
    boolean isCategory = false;
    if(cat == null || cat == "") {
    	isCategory = false;
    } else if(cat.equals("TV")){
    	isCategory = true;
    	category = Category.TV;
    } else if(cat.equals("Tablets")){
    	isCategory = true;
    	category = Category.TABLETS;
    }  else if(cat.equals("Laptops")){
    	isCategory = true;
    	category = Category.LAPTOPS;
    } else if(cat.equals("Smart-Phones")){
    	isCategory = true;
    	category = Category.SMARTPHONES;
    } 
    String contentStr = "";
    if(products == null || products.isEmpty()) {
    	System.out.println("NO products");
    } else {
    
    
	 for(Entry<String, Product> m :products.entrySet()){
		  	System.out.println(m.getKey());		
		  	Product c = m.getValue();
		  	if(isCategory == false || (isCategory == true && category == c.getCategory())) {
		  		//		  		contentStr = contentStr +buildString(m, contentStr);
		  		 contentStr = contentStr +
		 				"<div class=\"prod_box_big\">"+
		 			"        <div class=\"top_prod_box_big\"></div>"+
		 			"        <div class=\"center_prod_box_big\">"+
		 			"          <div class=\"product_img_big\"> <a href=\"javascript:popImage('images/"+c.getCategory()+".jpg','Some Title')\" title=\"\"><img width=\"150\" height=\"150\" src=\"images/"+c.getCategory()+".jpg\" alt=\"\" border=\"0\"></a>"+
		 			"            <div class=\"thumbs\"> <a href=\"#\" title=\"\"><img width=\"30\" height=\"30\" src=\"images/"+c.getCategory()+".jpg\" alt=\"\" border=\"0\"></a> <a href=\"#\" title=\"header=[Thumb2] body=[ ] fade=[on]\"><img width=\"30\" height=\"30\" src=\"images/"+c.getCategory()+".jpg\" alt=\"\" border=\"0\"></a> <a href=\"#\" title=\"header=[Thumb3] body=[ ] fade=[on]\"><img width=\"30\" height=\"30\" src=\"images/"+c.getCategory()+".jpg\" alt=\"\" border=\"0\"></a> </div>"+
		 			"          </div>"+
		 			"          <div class=\"details_big_box\">"+
		 			"            <div class=\"product_title_big\">"+m.getKey()+"</div>"+
		 			"            <div class=\"specifications\"> Category: <span class=\"blue\">"+c.getCategory()+"</span><br>"+
		 			"              Spectial discount: <span class=\"blue\">$"+c.getRdiscount()+"</span><br>"+
		 			"              Warranty Offered: <span class=\"blue\">$"+c.getRwarranty()+"</span><br>"+
		 			"              Discount: <span class=\"blue\">$"+c.getDiscount()+"</span><br>"+
		 			"            </div>"+
		 			"            <div class=\"prod_price_big\"> price : <span class=\"price\">$"+c.getPrice()+"</span></div>"+
					 			 "<form action=\"/csj/cart\" method = \"post\">"+
					 			"  <input type=\"hidden\" name=\"name\" value= \""+c.getName()+"\">"+
					 			"  <input type=\"hidden\" name=\"price\" value=\""+c.getPrice()+"\">"+

					 			"  <input type=\"hidden\" name=\"discount\" value=\""+c.getDiscount()+"\">"+
					 			"  <button type = \"submit\" value= \"Add To Cart\" class=\"addtocart\">Add To Cart</button>"+
					 			"</form>"+
					 			 "<form action=\"/csj/preview\" method = \"post\">"+
						 			"  <input type=\"hidden\" name=\"name\" value= \""+c.getName()+"\">"+
						 			"  <input type=\"hidden\" name=\"price\" value=\""+c.getPrice()+"\">"+

					 			"  <input type=\"hidden\" name=\"rdiscount\" value= \""+c.getRdiscount()+"\">"+
					 			"  <input type=\"hidden\" name=\"rwarranty\" value=\""+c.getRwarranty()+"\">"+
						 			"  <input type=\"hidden\" name=\"discount\" value=\""+c.getDiscount()+"\">"+
						 			"  <button type = \"submit\" value= \"Add To Cart\" class=\"addtocart\">Save To Order</button>"+
						 			"</form>"+
					 		
					"             </div>"+
		 			"        </div>";
		 			  	HashMap<String,Accessory> accessories = c.getAccessories();
		 			  	if(!accessories.isEmpty()) {
		 			  		contentStr = contentStr +"<hr><h4 style=\"background-color: #e9e9e9;padding-left: 13px;padding-top: 8px;\"> Accessories</h4> <hr>";
		 			  	}
		 				for(Entry<String, Accessory> ma :accessories.entrySet()){
		 					Accessory a = ma.getValue();
		 					contentStr = contentStr +"<div class=\"prod_box\">"+
		 							"        <div class=\"top_prod_box\"></div>"+
		 							"        <div class=\"center_prod_box\">"+
		 							"          <div class=\"product_title\"><a href=\"details.html\">"+a.getName()+"</a></div>"+
		 							"          <div class=\"product_img\"><a href=\"details.html\"><img width=\"130\" height=\"130\" src=\"images/"+c.getCategory()+"-acc.jpg\" alt=\"\" border=\"0\"></a></div>"+
		 							"             <div class=\"specifications\"> Discount: <span class=\"blue\">$"+a.getDiscount()+" </span><br></div>"+
		 							
		 							"          <div class=\"prod_price\">price : <span class=\"price\">$"+a.getPrice()+"</span></div>"+

		 							"        </div>"+
		 							"        <div class=\"bottom_prod_box\"></div>"+
		 							"        <div class=\"prod_details_tab\">"+
		 										"<form action=\"/csj/cart?act=addacc\" method = \"post\">"+
									 			"  <input type=\"hidden\" name=\"accname\" value= \""+a.getName()+"\">"+
									 			"  <input type=\"hidden\" name=\"price\" value=\""+a.getPrice()+"\">"+
									 			"  <input type=\"hidden\" name=\"discount\" value=\""+a.getDiscount()+"\">"+
									 			"  <button type = \"submit\" value= \"+Cart\" class=\"addtocart\">+Cart</button>"+
									 			"</form>"+
									 			 "<form action=\"/csj/preview?act=addacc\" method = \"post\">"+
										 			"  <input type=\"hidden\" name=\"accname\" value= \""+a.getName()+"\">"+
										 			"  <input type=\"hidden\" name=\"price\" value=\""+a.getPrice()+"\">"+
										 			"  <input type=\"hidden\" name=\"discount\" value=\""+a.getDiscount()+"\">"+
										 			"  <button type = \"submit\" value= \"Save&Order\" class=\"addtocart\">Save&Order</button>"+
										 			"</form>"+
		 							"      </div>  </div>";
		 							
		 							
		 				}		
		 	
		 						contentStr = contentStr +	"        <div class=\"bottom_prod_box_big\"></div>"+
		 							"      </div>";
		  	}		
	  }	
	}
				

	store.setBasicWithCSS("styles1");
	store.setContent(contentStr);

	HttpSession session = request.getSession();
	String headerval = (String)session.getAttribute("headerval");
	
	store.setHeader(headerval);
	//System.out.println("\t\t\t\t headerval : "+headerval);
			
    out.println(store.getWholeHTML());
  }
}
