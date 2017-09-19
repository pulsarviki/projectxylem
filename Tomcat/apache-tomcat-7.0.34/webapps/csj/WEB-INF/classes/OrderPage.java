import java.io.*;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Map.Entry;


public class OrderPage extends HttpServlet {
  
  private CommonUtils store = new CommonUtils();
  

  public void prinProducttMap(HashMap<String, Product> mapInFile) throws ServletException {
	  
      for(Entry<String, Product> m :mapInFile.entrySet()){
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
			
	   }
  }
  
  protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, User> usersFromDb = null;
		HashMap<String, Order> ordersFromDb = null;
		
		
		String act = request.getParameter("act");

		String ordername = request.getParameter("ordername");
	
		
		Random rand = new Random();
		
		if(act != null && act.equals("remove")) {
			ordersFromDb = (HashMap<String, Order>) store.readFromFile("orders");

			if(ordersFromDb == null || ordersFromDb.isEmpty()) {
				ordersFromDb = new HashMap<String, Order>();
			} else {

				ordersFromDb.remove(ordername);
			}
			store.writeToFile(ordersFromDb, "orders");

			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/myaccount?Ordered-removed-successfully."));
			System.out.println("Removed ordered product");
			
		} else if(act != null && act.equals("cancel")) {
			ordersFromDb = (HashMap<String, Order>) store.readFromFile("orders");

			if(ordersFromDb == null || ordersFromDb.isEmpty()) {
				ordersFromDb = new HashMap<String, Order>();
			} else {
				Order orderObj = ordersFromDb.get(ordername);
				orderObj.setStatus(OrderStatus.CANCEL);
				
				ordersFromDb.put(ordername, orderObj);
			}
			store.writeToFile(ordersFromDb, "orders");

			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/myaccount?Ordered-removed-successfully."));
			System.out.println("Canceled ordered product");
			
		} else if(act != null && act.equals("update")) {
			OrderStatus orderstatus = OrderStatus.fromString(request.getParameter("orderstatus"));
			ordersFromDb = (HashMap<String, Order>) store.readFromFile("orders");
			
			String totalpriceString = request.getParameter("totalprice");
			
			double totalprice = Double.parseDouble(totalpriceString) ;
			
			if(ordersFromDb == null || ordersFromDb.isEmpty()) {
				ordersFromDb = new HashMap<String, Order>();
			} else {
				Order orderObj = ordersFromDb.get(ordername);
				orderObj.setStatus(orderstatus);
				orderObj.setPrice(totalprice);
				ordersFromDb.put(ordername, orderObj);
			}
			store.writeToFile(ordersFromDb, "orders");

			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/myaccount?Ordered-updated-successfully."));
			System.out.println("Canceled ordered product");
			
		} else {

			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String cardno = request.getParameter("cardno");
			
			String totalpriceString = request.getParameter("totalprice");
			
			double totalprice = Double.parseDouble(totalpriceString) ;
			
			usersFromDb = (HashMap<String, User>) store.readFromFile("users");
			User userObj = null;
			if(usersFromDb == null || usersFromDb.isEmpty()) {
				usersFromDb = new HashMap<String, User>();
				userObj =new User();
				userObj.setName(name);
				
			} else {
				userObj = usersFromDb.get(name);
				if(userObj== null || usersFromDb.isEmpty()) {
					userObj =new User();
					userObj.setName(name);
				}
			}
			
			userObj.setAddress(address);
			userObj.setCredNo(cardno);
			//prinUsertMap(usersFromDb);
			usersFromDb.put(name, userObj);
			store.setUsers(usersFromDb);
			
			store.writeToFile(usersFromDb, "users");
			
			// ORDER
			ordersFromDb = (HashMap<String, Order>) store.readFromFile("orders");
			Order orderObj = null;
			
			if(ordersFromDb == null || ordersFromDb.isEmpty()) {
				ordersFromDb = new HashMap<String, Order>();
				orderObj = new Order();
				orderObj.setId(1);
				ordername = ordername + "-"+ orderObj.getId();
				orderObj.setName(ordername);
				
				orderObj.setBuyer(name);
			} else {
				orderObj = ordersFromDb.get(ordername);
				if(orderObj== null || ordersFromDb.isEmpty()) {
					orderObj =new Order();
					orderObj.setId(ordersFromDb.size()+1);
					ordername = ordername + "-"+ orderObj.getId();
				
					orderObj.setName(ordername);
					
					orderObj.setBuyer(name);
				} else {
					ordername = ordername + "-"+ orderObj.getId();
				}
				
			}
			
			orderObj.setPrice(totalprice);
			ordersFromDb.put(ordername, orderObj);
			store.setOrders(ordersFromDb);
			
			store.writeToFile(ordersFromDb, "orders");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home?Ordered-product-successfully."));
			
		}
		
	}
	
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    
    HashMap<String, Order> ordersFromDb = (HashMap<String, Order>) store.readFromFile("orders");
    
    
    String contentStr = "<hr><h1 style=\"background-color: #e9e9e9;padding-left: 20px;padding-top: 8px;\"> My Orders</h1> <hr>";
    if(ordersFromDb == null || ordersFromDb.isEmpty()) {
    	System.out.println("NO Orders");
    } else {
    	System.out.println("Orders ");
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Calendar cal = Calendar.getInstance();
    	
    	String delDate = "";
    	String ordersSelection = "";
    	for(Entry<String, Order> m :ordersFromDb.entrySet()){
		  	System.out.println(m.getKey());	
		  	Order c = m.getValue();
		  	cal.setTime(c.getOrderDate()); 
		  	cal.add(Calendar.DATE, 14);
		  	delDate = sdf.format(cal.getTime());
		  	
		  		//		  		contentStr = contentStr +buildString(m, contentStr);
	  		 contentStr = contentStr +
	  				"<div class=\"prod_box_big\">"+
		 			"        <div class=\"top_prod_box_big\"></div>"+
		 			"        <div class=\"center_prod_box_big\">"+
		 			"          <div> <hr><h4 style=\"background-color: #e9e9e9;padding-left: 20px;padding-top: 8px;\"> Order Details</h4> <hr></div>"+
		 			"          <div class=\"details_big_box\">"+
		 			"            <div class=\"product_title_big\">"+c.getName()+"</div>"+
		 			"          <div class=\"prod_price\">Price : <span class=\"price\">$"+c.getPrice()+"</span></div>"+
		 			"          <div class=\"prod_price\">Order Date : <span class=\"price\">"+c.getOrderDate()+"</span></div>"+
		 			"          <div class=\"prod_price\">Order No : <span class=\"price\">"+c.getId()+"</span></div>"+
		 			"          <div class=\"prod_price\">Order Status : <span class=\"price\">"+c.getStatus()+"</span></div>"+
		 			
		 			"          <div class=\"prod_price\">Delivery Date : <span class=\"price\">"+delDate+"</span></div>"+
		 		
							 "<form action=\"/csj/myaccount/salesman/orders?act=remove\" method = \"post\">"+
								"  <input type=\"hidden\" name=\"ordername\" value= \""+c.getName()+"\">"+
								"  <input type=\"hidden\" name=\"username\" value= \""+c.getName()+"\">"+
								
								"  <button type = \"submit\" value= \"Remove Order\" class=\"addtocart\">Remove Order</button>"+
								"</form>"+
							 "<form action=\"/csj/myaccount/salesman/orders?act=cancel\" method = \"post\">"+
								"  <input type=\"hidden\" name=\"ordername\" value= \""+c.getName()+"\">"+
								"  <input type=\"hidden\" name=\"username\" value= \""+c.getName()+"\">"+
								
								"  <button type = \"submit\" value= \"Cancel Order\" class=\"addtocart\">Cancel Order</button>"+
								"</form>"+
					"          </div>"+
		 			"   	</div>     </div>";
	  		 
	  		ordersSelection = 	ordersSelection +" <option value=\""+c.getName()+"\">"+c.getName()+"</option>";
	 	
	  	}		
    	contentStr = contentStr +"<div class=\"center_content\">"+
				"      <div class=\"center_title_bar\">Update Orders</div>"+
				"      <div class=\"prod_box_big\">"+
				"        <div class=\"top_prod_box_big\"></div>"+
				"        <div class=\"center_prod_box_big\">"+
				"          <div class=\"contact_form\">"+
							"<form action=\"/csj/myaccount/salesman/orders?act=update\" method=\"post\">"+
				
				"            <div class=\"form_row\">"+
				"              <label class=\"contact\"><strong>Order Name:</strong></label>"+
							"<select name=\"ordername\">"+ 
								ordersSelection +
							"  </select>"+
				"            </div>"+
							
				"            <div class=\"form_row\">"+
				"              <label class=\"contact\"><strong>Order Price:</strong></label>"+
				"              <input type=\"text\" name=\"totalprice\" class=\"contact_input\" />"+
				"            </div>"+
				
				"            <div class=\"form_row\">"+
				"              <label class=\"contact\"><strong>Order Status:</strong></label>"+
								"<select name=\"orderstatus\">"+
								"    <option value=\"ORDERED\">ORDERED</option>"+
								"    <option value=\"CANCEL\">CANCEL</option>"+
								"</select>"+
				"            </div>"+
			
		
				"             <div><button type=\"submit\" value=\"Update\"  style=\"margin-left: 275px; padding: 8px; background: burlywood;\" class=\"contact\">Update</button> </div>"+
							"</form>"+
				"          </div>"+
				"        </div>"+
				"        <div class=\"bottom_prod_box_big\"></div>"+
				"      </div>"+
				"    </div>";
	
    }			

	store.setBasicWithCSS("styles1");
	store.setContent(contentStr);

	HttpSession session = request.getSession();
	String headerval = (String)session.getAttribute("headerval");
	
	store.setHeader(headerval);
    out.println(store.getWholeHTML());
  }
}
