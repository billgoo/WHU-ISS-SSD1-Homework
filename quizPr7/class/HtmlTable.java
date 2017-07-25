// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HtmlTable.java


public class HtmlTable
{

	private boolean heading;
	private String tableBody;

	public HtmlTable()
	{
		tableBody = "<TABLE>";
		heading = false;
	}

	public HtmlTable(int i)
	{
		tableBody = "<TABLE cellpadding=" + i + ">";
		heading = false;
	}

	public void addHeader(String s)
	{
		tableBody = tableBody + "<THEAD bgcolor='" + s + "'>";
		heading = true;
	}

	public void startRow()
	{
		tableBody = tableBody + "<TR>";
	}

	public void endRow()
	{
		tableBody = tableBody + "</TR>";
	}

	public void addCell(String s)
	{
		tableBody = tableBody + "<TD>";
		tableBody = tableBody + s;
		tableBody = tableBody + "</TD>";
	}

	public void addCell(String s, String s1)
	{
		tableBody = tableBody + "<TD bgcolor='" + s + "'>";
		tableBody = tableBody + s1;
		tableBody = tableBody + "</TD>";
	}

	public String buildHtml()
	{
		if (heading)
			tableBody = tableBody + "</THEAD>";
		tableBody = tableBody + "</TABLE>";
		return tableBody;
	}
}
