import java.io.*;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.util.*;
import java.util.Map.Entry;


public class Home extends HttpServlet{

    private Utilities utils = new Utilities();
    HashMap<String, Products> products = null;
    URL url;
    
    public void init() throws ServletException {
        SAXProductsHandler s = new SAXProductsHandler();
        try {
            url = getClass().getResource("ProductCatalog.xml");
    		
            products = s.readDataFromXML(url.getPath());
            System.out.println("_--------------------");
            utils.writeToFile(products, "products");
        } catch (Exception e) {
        }
            
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            
            HashMap<String, Products> products = (HashMap<String, Products>) utils.readFromFile("products");
            //prinProducttMap(products);
            
            String cat = request.getParameter("cat");
            String category = "";
            boolean isCategory = false;
            if(cat == null || cat == "") {
                isCategory = false;
            } else if(cat.equals("TV")){
                isCategory = true;
                category = "TV";
            } else if(cat.equals("Tablets")){
                isCategory = true;
                category = "Tablets";
            }  else if(cat.equals("Laptops")){
                isCategory = true;
                category = "Laptops";
            } else if(cat.equals("Smart-Phones")){
                isCategory = true;
                category = "SmartPhones";
            } else if(cat.equals("Others")){
                isCategory = true;
                category = "Others";
            } 
            String contentStr = "";
            if(products == null || products.isEmpty()) {
                System.out.println("NO products");
            }else{
                for(Entry<String, Products> m :products.entrySet()){
                    System.out.println(m.getKey());		
                    Products c = m.getValue();
                    if(isCategory == false || (isCategory == true && category.equals(c.getCategory()))) {
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
                                       "  <a href=\"/csj/review?pname="+m.getKey()+"\"><button type = \"submit\" value= \"Review Product\" class=\"addtocart\">Review Product</button></a>"+
      
                                       "  <button type = \"submit\" value= \"View Review\" class=\"addtocart\">View Review</button>"+
                                   
                          "             </div>"+
                           "        </div>";

                        contentStr = contentStr +	"        <div class=\"bottom_prod_box_big\"></div>"+
                        "      </div>";
                    }
                }
            }

            utils.setBasicWithCSS("styles1");

            HttpSession session = request.getSession();

            // if(session != null){
                String headerval = (String)session.getAttribute("headerval");
                utils.setHeader(headerval);
            // }

            utils.setContent(contentStr);
            out.println(utils.getWholeHTML());

    }
}