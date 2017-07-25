// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Label.java

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Label extends HttpServlet
{

	public Label()
	{
	}

	public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws ServletException, IOException
	{
		httpservletresponse.setContentType("text/html");
		PrintWriter printwriter = httpservletresponse.getWriter();
		String s = httpservletrequest.getParameter("SenderName");
		String s1 = httpservletrequest.getParameter("SenderStrtAddr");
		String s2 = httpservletrequest.getParameter("SenderCityZip");
		String s3 = httpservletrequest.getParameter("ReceiverName");
		String s4 = httpservletrequest.getParameter("ReceiverStrtAddr");
		String s5 = httpservletrequest.getParameter("ReceiverCityZip");
		String s6 = httpservletrequest.getParameter("destination");
		printwriter.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		printwriter.println("<HTML>");
		printwriter.println("<HEAD>");
		printwriter.println("<TITLE>");
		printwriter.println("ShippingLabelForm");
		printwriter.println("</TITLE>");
		printwriter.println("</HEAD>");
		printwriter.println("<BODY>");
		printwriter.println("<b>From:-</b><br><p></p>");
		printwriter.println((new StringBuilder()).append("").append(s).append("<BR><p></p>").toString());
		printwriter.println((new StringBuilder()).append("").append(s1).append("<BR><center><img></center><p></p>").toString());
		printwriter.println((new StringBuilder()).append("").append(s2).append("<BR><p></p>").toString());
		printwriter.println("<center><b>To:-</b><br></center><p></p>");
		printwriter.println((new StringBuilder()).append("").append(s3).append("<BR></center><p></p>").toString());
		printwriter.println((new StringBuilder()).append("").append(s4).append("<BR></center><p></p>").toString());
		printwriter.println((new StringBuilder()).append("").append(s5).append("<BR><p></p>").toString());
		printwriter.println((new StringBuilder()).append("<IMG src='/").append(s6).append("' alt='").append(s6).append("'>").toString());
		printwriter.println("</BODY>");
		printwriter.println("</HTML>");
	}
}
