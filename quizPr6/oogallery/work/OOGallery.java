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
		String bgrnd = request.getParameter("bgrnd").concat(".gif");
		String theme1 = request.getParameter("theme").concat("One.jpg");
        String theme2 = request.getParameter("theme").concat("Two.jpg");
		/** 
		 * 	Create an instance of the HtmlPage class 
		 */
        HtmlPage myPage = new HtmlPage();

        /**
         *  Set the title for the response Web page
         */
        myPage.setTitle("OOGalleryPage using HtmlPage and HtmlImage classes");

		/** 
		 * 	Create an instance of the HtmlImage class for the first image
		 */
        HtmlImage primerImage = new HtmlImage("/" + theme1,theme1);

		/** 
		 * 	Create an instance of the HtmlImage class for the second image
		 */
        HtmlImage segundaImage = new HtmlImage("/" + theme2,theme2);

		/** 
		 * 	Set the attributes of the HtmlPage object 
		 */
        myPage.setBackgroundImage("/" + bgrnd + "");

		/** 
		 * 	Add the images 
		 */
        myPage.addText( primerImage.buildHtml() );
        myPage.addText( segundaImage.buildHtml() );

		/**
		 *	 Construct the HTML response
		 */
        out.println( myPage.buildHtml() );
	}
}
