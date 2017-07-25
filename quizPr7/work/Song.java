
/**
 */
class Song
{
    private String title;
    private String playTime;
    private String category;
    private String albumTrack;
    private boolean selected;
    private String songID;
    private String songLink;

    public Song(String st, String sp, String sc, String sa, boolean flag, String ssI, String ssL)
    {
        title = st;
        playTime = sp;
        category = sc;
        albumTrack = sa;
        selected = flag;
        songID = ssI;
        songLink = ssL;
    }

    public Song()
    {
    }

    public void setTitle(String st)
    {
        title = st;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTime(String sp)
    {
        playTime = sp;
    }

    public String getTime()
    {
        return playTime;
    }

    public void setCategory(String sc)
    {
        category = sc;
    }

    public String getCategory()
    {
        return category;
    }

    public void setAlbumTrack(String sa)
    {
        albumTrack = sa;
    }

    public String getAlbumTrack()
    {
        return albumTrack;
    }

    public void setSongID(String ssI)
    {
        songID = ssI;
    }

    public String getSongID()
    {
        return songID;
    }

    public void setSongLink(String ssL)
    {
        songLink = ssL;
    }

    public String getSongLink()
    {
        return songLink;
    }

    public void select(boolean flag)
    {
        selected = flag;
    }

    public boolean isSelected()
    {
        return selected;
    }
}