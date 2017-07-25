// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HtmlPage.java


public class HtmlPage
{

	private String pageTitle;
	private String pageBody;
	private String bgImage;
	private String bgColor;

	HtmlPage()
	{
		pageTitle = "";
		pageBody = "";
		bgImage = "";
		bgColor = "";
	}

	private String getHeader()
	{
		return "<HTML><HEAD><TITLE>" + pageTitle + "</TITLE></HEAD>";
	}

	private String getFooter()
	{
		return "</HTML>";
	}

	public void setTitle(String s)
	{
		pageTitle = s;
	}

	public void setBackgroundImage(String s)
	{
		bgImage = s;
	}

	public void setBackgroundColor(String s)
	{
		bgColor = s;
	}

	private String getBody()
	{
		return "<BODY background='" + bgImage + "' bgcolor='" + bgColor + "'>" + pageBody + "</BODY>";
	}

	private String getDoctype()
	{
		return "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
	}

	public void addText(String s)
	{
		pageBody = pageBody + s;
	}

	public String buildHtml()
	{
		String s = getDoctype() + getHeader() + getBody() + getFooter();
		return s;
	}
}
