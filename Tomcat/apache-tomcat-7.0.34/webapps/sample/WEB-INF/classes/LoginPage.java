import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginPage extends HttpServlet {
  
    private Utilities utils = new Utilities();
    private UserType u = new UserType();

  public void init() throws ServletException {
      HashMap<String, String> users = new HashMap<String, String>();
      users.put("raj","123");
      utils.writeToFile(users, "user_details");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException{
    String user = request.getParameter("user");
    String pwd = request.getParameter("password");
    String usertype = request.getParameter("usertype");

    HashMap<String, String> details = (HashMap<String, String>) utils.readFromFile("user_details");
    if(details.containsKey(user)){
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.setAttribute("username", user);
        session.setAttribute("usertype", usertype);
        
        String header = u.getheader((String)session.getAttribute("usertype"));
        session.setAttribute("headerval", header);
        
        PrintWriter out = response.getWriter();
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
    }
  }
	
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	utils.setBasicWithCSS("login");
	String content = 
    "<html><body>"+
	"<div class=\"ribbon\"></div><div class=\"login\">"+
	"<h1>Login.</h1>"+
  	"<p>Lets login</p>"+
	"<form action=\"login\" method=\"post\">"+
    		"<div class=\"input\">"+
		"      <div class=\"blockinput\">"+
		"        <i class=\"icon-envelope-alt\"></i><input type=\"text\" name=\"user\" placeholder=\"Username\">"+
		"      </div><br>"+
		"      <div class=\"blockinput\">"+
		"        <i class=\"icon-unlock\"></i><input type=\"password\" placeholder=\"Password\" name=\"password\">"+
		"      </div><br>"+
		
		"      <div class=\"blockinput\">"+
		"        <i class=\"icon-unlock\"></i>"+
				"<select name=\"usertype\">"+
				"    <option value=\"CUSTOMER\">CUSTOMER</option>"+
				"    <option value=\"STOREMANAGER\">STOREMANAGER</option>"+
				"    <option value=\"SALESMAN\">SALESMAN</option>"+
				"    <option value=\"RETAILER\">RETAILER</option>"+
				"  </select>"+
		"      </div>"+
				
		"    </div>"+
		"<button type=\"submit\" value=\"Login\">Login</button>"+
	"  </form></div></body></html>";

	// HttpSession session = request.getSession();
	// String headerval = (String)session.getAttribute("headerval");
	
	// utils.setHeader(headerval);
						
    utils.setContent(content);
    out.println(utils.getWholeHTML());
  }
}
