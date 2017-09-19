import java.io.*;
import java.util.*;

public class UserType{
    String header = "";
    String userBasedHeader = "";
    public String getheader(String utype){
        
        if(utype.equals("CUSTOMER")){
            userBasedHeader = " <li class=\"start selected\"><a href=\"/sample/myaccount/customer\">My Account</a></li>"+
            " <li class=\"start selected\"><a href=\"/sample/myaccount/customer/orders\">My Orders</a></li>"+
            " <li class=\"start selected\"><a href=\"/sample/myaccount/customer/orders/track\">Track Order</a></li>";
            ;
        }else if(utype.equals("STOREMANAGER")){
            userBasedHeader = " <li class=\"start selected\"><a href=\"/sample/myaccount/storemanager\">My Account</a></li>"+
            " <li class=\"start selected\"><a href=\"/sample/myaccount/storemanager/handleproducts\">Handle Products</a></li>";
        }else if(utype.equals("SALESMAN")){
            userBasedHeader =  " <li class=\"start selected\"><a href=\"/sample/myaccount/retailer\">My Account</a></li>"+
            " <li class=\"start selected\"><a href=\"/sample/myaccount/salesman/createuser\">Create Accounts</a></li>"+
            " <li class=\"start selected\"><a href=\"/sample/myaccount/salesman/orders\">Orders</a></li>";
        }else if(utype.equals("RETAILER")){
            userBasedHeader =  " <li class=\"start selected\"><a href=\"/sample/myaccount/retailer\">My Account</a></li>"+
            " <li class=\"start selected\"><a href=\"/sample/myaccount/retailer/offer\">Offer Warranty or Discounts</a></li>";
        }

        header = "<header>"+
        "    	<h1><a href=\"/\">Just<span>Buy</span></a></h1>"+
        "        <h2>find everything with best price</h2>"+
        "    </header>"+
        "    <nav>"+
        "    	<ul>"+
        "        	<li class=\"start selected\"><a href=\"/sample/home\">Home</a></li>"+
            userBasedHeader+

        "            <li><a href=\"/sample/register\"  style=\"padding-left: 63px;border: 0px;\"><u>Register</u></a></li>"+
        "            <li class=\"\"><a href=\"/sample/logout\" style=\"border: 0px;\"><u>Logout</u></a></li>"+
        "        </ul>"+
        "    </nav>"+
        "<div id=\"body\">";

        return header;
    }

}