/**

 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;


public class Jukebox extends HttpServlet {
    public Jukebox()
    {
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws ServletException, IOException
    {
        Vector vector = new Vector();
        vector.add(new Song("Pachchai Nirame", "2:15", "Tamil Pop", "Alai Payuthey (2000)", false, "TPS001", "http://www.raaga.com/channels/tamil/movie/T0000209.html"));
        vector.add(new Song("Bombay (Children of Combodia)", "2:38", "Jazz", "Fred Andersen Quartet/1", false, "WCS002", "http://www.epitonic.com/artists/fredandersonquartet.html#tracks"));
        vector.add(new Song("A Dios Le Pido", "1:50", "Latin Pop", "Juanes : Un Dia Normal/1", false, "SPS003", "http://es.artists.mp3s.com/artist_song/2515/2515448.html"));
        for (int i = 0; i < vector.size(); i++)
        {
            String st = request.getParameter(((Song)vector.elementAt(i)).getSongID());
            if (st != null)
                ((Song)vector.elementAt(i)).select(true);
         }

        displayHtml(vector, response);
    }

    public void displayHtml(Vector vector, HttpServletResponse response)
            throws IOException
    {
        response.setContentType("text/html");
        PrintWriter printwriter = response.getWriter();
        HtmlPage htmlpage = new HtmlPage();
        htmlpage.setTitle("CTE Jukebox");
        htmlpage.setBackgroundColor("fdf5e6");
        htmlpage.addText("<H1>CTE Jukebox</H1>");
        htmlpage.addText("<FORM action='/servlet/Jukebox' method='post'>");
        htmlpage.addText("<H4>Select titles for your playlist:-</H4>");
        HtmlTable htmltable = new HtmlTable(10);
        htmltable.startRow();
        htmltable.addCell("<B>Title</B>");
        htmltable.addCell("<B>Duration(min)</B>");
        htmltable.addCell("<B>Category</B>");
        htmltable.addCell("<B>Album/Track</B>");
        htmltable.endRow();
        for (int i = 0; i < vector.size(); i++)
        {
            htmltable.startRow();
            String st = "<INPUT type='checkbox' name='" + ((Song)vector.elementAt(i)).getSongID() + "' value='" + ((Song)vector.elementAt(i)).getTitle() + "'";
            if (((Song)vector.elementAt(i)).isSelected())
                st = st + " checked>";
            else
                st = st + ">";
            st = st + ((Song)vector.elementAt(i)).getTitle();
            htmltable.addCell(st);
            htmltable.addCell(((Song)vector.elementAt(i)).getTime());
            htmltable.addCell(((Song)vector.elementAt(i)).getCategory());
            htmltable.addCell(((Song)vector.elementAt(i)).getAlbumTrack());
            htmltable.addCell("<A href='" + ((Song)vector.elementAt(i)).getSongLink() + "'>Play</A>");
            htmltable.endRow();
        }

        htmlpage.addText(htmltable.buildHtml());
        htmlpage.addText("<BR><BR><BR>");
        htmlpage.addText("<INPUT type='submit' name='UserRequest' value='My Playlist'>");
        htmlpage.addText("<BR><BR><BR>");
        htmlpage.addText("<TEXTAREA name='playList' rows='10' cols='80' readonly>");
        for (int j = 0; j < vector.size(); j++)
            if (((Song)vector.elementAt(j)).isSelected())
                htmlpage.addText(((Song)vector.elementAt(j)).getTitle() + "\t" + ((Song)vector.elementAt(j)).getTime() + "\t" + ((Song)vector.elementAt(j)).getCategory() + "\n" + ((Song)vector.elementAt(j)).getAlbumTrack() + "\n\n");

        htmlpage.addText("</TEXTAREA>");
        htmlpage.addText("</FORM>");
        printwriter.print(htmlpage.buildHtml());
        printwriter.flush();
        printwriter.close();
    }
}