/**
 *  Author:	ssd1-dev-srt
 *  Date:	May'03
 *  Description: Servlet that displays pictures with background, using the HtmlPage and HtmlImage classes
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class OOGallery extends HttpServlet {
	public void doPost(HttpServletRequest request,
						HttpServletResponse response)
						throws ServletException, IOException {

		/**
		 *  Indicate the content type (for example, text/html), 
		 *  being returned by the response
		 */
		response.setContentType("text/html");
		/** 
		 * 	Retrieve an output stream to use to send data to the client 
		 */
		PrintWriter out = response.getWriter();

		/**
		 *	 Get the user input from the form
		 */
		 String ThemePicture1 = request.getParameter("theme")+"One";
	     String ThemePicture2 = request.getParameter("theme")+"Two";
	     String BackgroundImage = "/"+request.getParameter("bgrnd")+".gif";
	     String Bgcolor="#FFF8DC";
		/** 
		 * 	Create an instance of the HtmlPage class 
		 */
	     HtmlPage htmlPage = new HtmlPage();
		
		/**
         *  Set the title for the response Web page
         */
	     HtmlImage htmlImage1 = new HtmlImage("/" + ThemePicture1 + ".jpg", ThemePicture1);

		/** 
		 * 	Create an instance of the HtmlImage class for the first image
		 */
	     HtmlImage htmlImage2 = new HtmlImage("/" + ThemePicture2 + ".jpg", ThemePicture2);

		/** 
		 * 	Create an instance of the HtmlImage class for the second image
		 */
	     HtmlImage htmlImage2 = new HtmlImage("/" + ThemePicture2 + ".jpg", ThemePicture2);

		/** 
		 * 	Set the attributes of the HtmlPage object 
		 */
	     htmlPage.setTitle("OOGalleryPage using HtmlPage and HtmlImage classes");
	     htmlPage.setBackgroundImage(BackgroundImage);
	     htmlPage.setBackgroundColor("Bgcolor");


		/** 
		 * 	Add the images 
		 */
	     String picture1=htmlImage1.buildHtml();
	     String picture2=htmlImage2.buildHtml();
         htmlPage.addText(picture1);
	     htmlPage.addText("<BR>");
         htmlPage.addText("<BR>");
	     htmlPage.addText(picture2);

		/**
		 *	 Construct the HTML response
		 */
	     out.println(htmlPage.buildHtml());
	}
}
