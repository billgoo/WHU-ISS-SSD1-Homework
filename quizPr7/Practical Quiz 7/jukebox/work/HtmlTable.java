/**
 *  Author:	ssd1-dev-srt
 *  Date:	June 2003
 *  Description: 	Class that builds the HTML source for a HTML table element.
 */
public class HtmlTable {
	private boolean heading;
	private String tableBody;

	/**
	 *	Constructor : overloaded constructors
	 *
	 */
	public HtmlTable() {
		tableBody = "<TABLE>";
		heading = false;
	}


	public HtmlTable(int cp) {
		tableBody = "<TABLE cellpadding=" + cp + ">";
		heading = false;
	}


	/**
	 *  Add a header to the table
	 */
	public void addHeader(String colorValue) {
		tableBody = tableBody + "<THEAD bgcolor='" + colorValue + "'>";

		heading = true;
	}


	/**
	 *  Start building a row
	 */	
	public void startRow() {
		tableBody = tableBody + "<TR>";
	}


	/**
	 *  Stop building a row
	 */
	public void endRow() {
		tableBody = tableBody + "</TR>";
	}


	/**
	 *  Add a cell to an existing row
	 */
	public void addCell(String cellData) {
		tableBody = tableBody + "<TD>";

		tableBody = tableBody + cellData;

		tableBody = tableBody + "</TD>";
	}


	/**
	 *  Add a cell to an existing row
	 */
	public void addCell(String cellColor, String cellData) {
		tableBody = tableBody + "<TD bgcolor='" + cellColor + "'>";

		tableBody = tableBody + cellData;

		tableBody = tableBody + "</TD>";
	}


	/**
	 *  Construct the HTML source
	 */
	public String buildHtml() {

		if (heading)
			tableBody = tableBody + "</THEAD>";

		tableBody = tableBody + "</TABLE>";

		return tableBody;
	}

}
