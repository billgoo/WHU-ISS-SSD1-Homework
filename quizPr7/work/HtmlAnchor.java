/**
 *  Author:	ssd1-dev-srt
 *  Date:	June 2003
 *  Description: 	Class that builds the HTML source for a HTML <A> element.
 */
public class HtmlAnchor {
	private String displayText;
	private String targetLocation;

	/**
	 *  Constructor
	 */
	public HtmlAnchor(String text, String location) {
		displayText = text;
		targetLocation = location;
	}

	/**
	 *  Construct the HTML source for the tag
	 */
	public String buildHtml() {
		return("<A href='" + targetLocation + "'>" + displayText + "</A>");
	}

} 
