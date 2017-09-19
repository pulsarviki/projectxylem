import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.*;
import javax.servlet.http.*;


public class Users implements java.io.Serializable{
	private String name;
	private String address;
	private String credNo;
	private int cartCount;
	private String password;
  private static HashMap<String, Users> userset;


	private String utype = "CUSTOMER";

  public static HashMap<String, Users> loadUsers()
  {
    userset = (HashMap<String, Users>) PageContent.readFromFile("users");
    return userset;
  }

  public static void dumpUsers(HashMap<String, Users> obj)
  {
    PageContent.writeToFile(obj,"users");
  }

	public Users(String name, String address, String credNo, int cartCount,String utype){
		this.name = name;
		this.address = address;
		this.credNo = credNo;
		this.cartCount = cartCount;
		this.utype = utype;
	}

	public Users(String name, String password){
		this.name = name;
		this.password = password;
	}

	public Users() {

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getCredNo() {
		return credNo;
	}
	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
