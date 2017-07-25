/**
 *  Author:	ssd1-dev-srt
 *  Date:	June 2003
 *  Description: 	Class that builds the HTML source for a HTML document.
 */
/*!Begin Snippet:file*/
import java.io.*;

public class HtmlPage {
	private String pageTitle;
	private String pageBody;
	private String bgImage;
	private String bgColor;

	/**
	 *  Constructor
	 */
	HtmlPage() {
		pageTitle = "";
		pageBody = "";
		bgImage = "";
		bgColor = "";
	}

	/**
	 *  Build the page header
	 */
	private String getHeader() {
		return ("<HTML><HEAD><TITLE>" + pageTitle + "</TITLE></HEAD>");
	}

	/**
	 *  Build the page footer
	 */
	private String getFooter() {
		return ("</HTML>");
	}

	/**
	 *  Set the page title
	 */
	public void setTitle(String pTitle) {
		pageTitle = pTitle;
	}

	/**
	 *  Set the body background image attribute
	 */
	public void setBackgroundImage(String imageName) {
		bgImage = imageName;
	}

	/**
	 *  Set the body background color attribute
	 */
	public void setBackgroundColor(String colorValue) {
		bgColor = colorValue;
	}

	/**
	 *  Build the page body
	 */
	private String getBody() {

		return (
			"<BODY background='"
				+ bgImage
				+ "' bgcolor='"
				+ bgColor
				+ "'>"
				+ pageBody
				+ "</BODY>");
	}

	/**
	 *  Build the document type
	 */
	private String getDoctype() {

		return ("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
	}

	/**
	 *  Add a line of text to the body
	 */
	public void addText(String textString) {
		pageBody = pageBody + textString;
	}

	/**
	 *  Build the page now
	 */
	public String buildHtml() {

			String pageString = getDoctype() + // Add document type
					    getHeader() + // Add header
					    getBody() + // Add body
					    getFooter(); // Add footer

		return pageString;
	}
}
/*!End Snippet:file*/