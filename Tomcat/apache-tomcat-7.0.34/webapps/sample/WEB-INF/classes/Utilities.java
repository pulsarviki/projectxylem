import java.io.*;
import java.util.*;

public class Utilities{

    private String CONTENT = "";
	private String BASICS = "<!doctype html>"+
							"<html>"+
							"<head>"+
							"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
							"<title>spigot - Free CSS Tobjlate by ZyPOP</title>"+
							"<link rel=\"stylesheet\" href=\"/sample/styles.css\" type=\"text/css\" />"+

							"<!--[if lt IE 9]>"+
							"<script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>"+
							"<![endif]-->"+
							"<!--"+
							"spigot, a free CSS web tobjlate by ZyPOP (zypopwebtobjlates.com/)"+
							""+
							"Download: http://zypopwebtobjlates.com/"+
							""+
							"License: Creative Commons Attribution"+
							"//-->"+
							"</head>"+
							"<body>"+
							"<div id=\"container\">";

	private String HEADER  = "<header>"+
							"    	<h1><a href=\"/\">Just<span>Buy</span></a></h1>"+
							"        <h2>find everything with best price</h2>"+
							"    </header>"+
							"    <nav>"+
							"    	<ul>"+
							"        	<li class=\"start selected\"><a href=\"/sample/home\">Home</a></li>"+
							"                        <li><a href=\"/sample/home?cat=TV\">TV</a></li>"+
							"                        <li><a href=\"/sample/home?cat=Tablets\">Tablets</a></li>"+
							"                        <li><a href=\"/sample/home?cat=Smart-Phones\">Smart Phones</a></li>"+

							"            <li><a href=\"/sample/register?param1=hello\">Register</a></li>"+
							
							"            <li class=\"\"><a href=\"/sample/login\">Login</a></li>"+
							"            <li><a href=\"/sample/cart\">Cart</a></li>"+
							"        </ul>"+
							"    </nav>"+
							"<div id=\"body\">";

	private final String SIDEBAR = "<aside class=\"sidebar\">"+
							"	"+
							"            <ul>	"+
							"               <li>"+
							"                    <h4>Categories</h4>"+
							"                    <ul>"+
							"                        <li><a href=\"/sample/home?cat=TV\">TV</a></li>"+
							"                        <li><a href=\"/sample/home?cat=Tablets\">Tablets</a></li>"+
							"                        <li><a href=\"/sample/home?cat=Smart-Phones\">Smart Phones</a></li>"+

							"                        <li><a href=\"/sample/home?cat=Laptops\">Laptops</a></li>"+
							"                        <li><a href=\"/sample/home?cat=Others\">Others</a></li>"+
							"                    </ul>"+
							"                </li>"+
							"                "+
							"                <li>"+
							"                    <h4>About us</h4>"+
							"                    <ul>"+
							"                        <li class=\"text\">"+
							"                        	<p style=\"margin: 0;\">We are the best sellers in 2016 with great prices and deals. We have above one million happy customers.</p>"+
							"                        </li>"+
							"                    </ul>"+
							"                </li>"+
							"            </ul>"+
							"		"+
							"        </aside>"+
							"    	<div class=\"clear\"></div>"+
							"    </div>";
	

    private final String FOOTER = "<footer>"+
								
								"        <div class=\"footer-bottom\">"+
								"            <p>© JustBuy 2016. <a href=\"http://zypopwebtobjlates.com/\">Free CSS Website Tobjlates</a> by rohan borde</p>"+
								"         </div>"+
								"    </footer>"+
								"</div>"+
								"</body>"+
								"</html>";

    void  writeToFile(Object obj, String filename){
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
    
    Object readFromFile(String filename) {
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
        this.CONTENT = "<section id=\"content\">"+
                      //	"<article>"+
                          content+
                      //	"</article>"+
                      "</section>";
    }
    
    public void setHeader( String header) {
            this.HEADER = header;
    }
    
    public void setBasicWithCSS( String extCode) {
            this.BASICS = "<!doctype html>"+
                  "<html>"+
                  "<head>"+
                  "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
                  "<title>spigot - Free CSS Tobjlate by ZyPOP</title>"+
                  "<link rel=\"stylesheet\" href=\"/sample/styles.css\" type=\"text/css\" />"+
                  "<link rel=\"stylesheet\" href=\"/sample/"+extCode +".css\" type=\"text/css\" />"+
                  "<!--[if lt IE 9]>"+
                  "<script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>"+
                  "<![endif]-->"+
                  "<!--"+
                  "spigot, a free CSS web tobjlate by ZyPOP (zypopwebtobjlates.com/)"+
                  ""+
                  "Download: http://zypopwebtobjlates.com/"+
                  ""+
                  "License: Creative Commons Attribution"+
                  "//-->"+
                  "</head>"+
                  "<body>"+
                  "<div id=\"container\">";
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
  
    public String getWholeHTML() {
        return getBasics() + getHeader()  + getContent() + getSideBar() + getFooter();
    }
}