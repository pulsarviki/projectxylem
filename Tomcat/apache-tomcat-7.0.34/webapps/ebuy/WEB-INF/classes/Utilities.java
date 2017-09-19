import java.io.*;
import java.util.*;

public class Utilities{

    private String CONTENT = "";
	private String BASICS = "<!doctype html>"+
							"<html>"+
							"<head>"+
              "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
							"<title>spigot - Free CSS Tobjlate by ZyPOP</title>"+
							"<link rel=\"stylesheet\" type=\"text/css\" href=\"/ebuy/style.css\" />"+
							"</head>"+
							"<body>"+
							"<div id=\"container\">";

	private String HEADER  = "<header>"+
  "<div id=\"header\">"+
  "<h1><a href=\"#\">ebuy.com</a></h1>"+
  "</div>"+
							"    </header>"+
							"    <nav>"+
              "<div id=\"menu\">"+
            "<ul>"+
            "<li><a href=\"index.html\">730px Version</a></li>"+
            "<li><a href=\"940px.html\">940px Version</a></li>"+
            "<li><a href=\"without730px.html\">Without footer columns(730px)</a></li>"+
            "<li><a href=\"without940px.html\">Without footer columns(940px)</a></li>"+
            "</ul>"+
            "</div>"+
							"    </nav>"+
							"<div id=\"body\">";

	private final String SIDEBAR = "<aside class=\"sidebar\">"+
							"	"+
              "<div id=\"sidebar\">"+
            "<h3>Menu Navigation</h3>"+
            "<ul>"+
            "<li><a href=\"#\">Proin at</a></li>"+
            "<li><a href=\"#\">Class aptent taciti</a></li>"+
            "<li><a href=\"#\">Morbi in dolor</a></li>"+
            "<li><a href=\"#\">Praesent ultricies</a></li>"+
            "<li><a href=\"#\">Aenean euismod</a></li>"+
            "<li><a href=\"#\">Donec sempe</a></li>"+
            "<li><a href=\"#\">Suspendisse potenti</a></li>"+
            "</ul>"+
            ""+
            "</div>"+
							"		"+
							"        </aside>"+
							"    	<div class=\"clear\"></div>"+
							"    </div>";


    private final String FOOTER = "<footer>"+

                    "<div id=\"footer\">"+
                "<p>© Copyright 2010 You | Design by <a href=\"http://www.blogliber.com\">Blog Liber</a></p>"+
                "</div>"+
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
                  "<link rel=\"stylesheet\" href=\"/css/styles.css\" type=\"text/css\" />"+
                  "<link rel=\"stylesheet\" href=\"/css/"+extCode +".css\" type=\"text/css\" />"+
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
