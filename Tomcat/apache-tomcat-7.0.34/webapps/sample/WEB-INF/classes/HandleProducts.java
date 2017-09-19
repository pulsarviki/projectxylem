import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.*;
import javax.servlet.http.*;

public class HandleProducts extends HttpServlet{

    private Utilities utils = new Utilities();
    private UserType u = new UserType();

    private  HashMap<String, Products> product_details = new HashMap<String, Products>();

    public void init() throws ServletException {
        HashMap<String, Products> products = new HashMap<String, Products>();
        // Products productObj = new Products("Iphone X", 999, 0);
        products.put("Iphone", new Products("Note 8", 999, 0));
        utils.writeToFile(products, "products");
        // product_details = products;
        // utils.setProducts(products);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            String type = request.getParameter("type");
            String op = request.getParameter("operator");
            String name = request.getParameter("name");
            Double price = Double.parseDouble(request.getParameter("price"));
			Double discount = Double.parseDouble(request.getParameter("discount"));

            if(op.equals("ADD") && type.equals("PRODUCT")) {
				System.out.println("PRODUCT.. ADD");
				Products productObj = new Products("Iphone X", 999, 0);
				System.out.println("category" + request.getParameter("category"));
				
				product_details = (HashMap<String, Products>) utils.readFromFile("products");
	
				
				product_details.put("Iphone X", productObj);
				// utils.setProducts(product_details);
				
				utils.writeToFile(product_details, "products");
				// prinProducttMap(productsFromDb);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/myaccount/storemanager?"+"Product-"+name+"-added-successfully"));
			
			}
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        utils.setBasicWithCSS("styles1");

        HttpSession session = request.getSession();
        String header = u.getheader((String)session.getAttribute("usertype"));

        String content = 
        "<div class=\"center_content\">"+
    "      <div class=\"center_title_bar\">Add Product</div>"+
    "      <div class=\"prod_box_big\">"+
    "        <div class=\"top_prod_box_big\"></div>"+
    "        <div class=\"center_prod_box_big\">"+
    "          <div class=\"contact_form\">"+
                "<form action=\"handleproducts?operator=ADD&type=PRODUCT\" method=\"post\">"+
    
    "            <div class=\"form_row\">"+
    "              <label class=\"contact\"><strong>Name:</strong></label>"+
    "              <input type=\"text\" name=\"name\" class=\"contact_input\" />"+
    "            </div>"+
    "            <div class=\"form_row\">"+
    "              <label class=\"contact\"><strong>Category:</strong></label>"+
                    "<select name=\"category\">"+
                    "    <option value=\"OTHER\">Other</option>"+
                    "    <option value=\"SMARTPHONES\">Smart Phones</option>"+
                    "    <option value=\"LAPTOPS\">Laptops</option>"+
                    "    <option value=\"TV\">TV</option>"+
                    "  </select>"+
    "            </div>"+
    "            <div class=\"form_row\">"+
    "              <label class=\"contact\"><strong>Price:</strong></label>"+
    "              <input type=\"text\" name=\"price\" class=\"contact_input\" />"+
    "            </div>"+
    "            <div class=\"form_row\">"+
    "              <label class=\"contact\"><strong>Discount:</strong></label>"+
    "              <input type=\"text\" name=\"discount\" class=\"contact_input\" />"+
    "            </div>"+
                
    "             <button type=\"submit\" value=\"Add\" style=\"margin-left: 275px; padding: 8px; background: burlywood;\" class=\"contact\">Add</button> "+
                "</form>"+
    "          </div>"+
    "        </div>"+
    "        <div class=\"bottom_prod_box_big\"></div>"+
    "      </div>"+
    "    </div>";

        utils.setHeader(header);
        utils.setContent(content);
        PrintWriter out = response.getWriter();
        out.println(utils.getWholeHTML());
    }
}