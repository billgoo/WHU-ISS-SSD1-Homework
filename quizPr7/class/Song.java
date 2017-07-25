// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Song.java


class Song
{

	private String title;
	private String playTime;
	private String category;
	private String albumTrack;
	private boolean selected;
	private String songID;
	private String songLink;

	public Song(String s, String s1, String s2, String s3, boolean flag, String s4, String s5)
	{
		title = s;
		playTime = s1;
		category = s2;
		albumTrack = s3;
		selected = flag;
		songID = s4;
		songLink = s5;
	}

	public Song()
	{
	}

	public void setTitle(String s)
	{
		title = s;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTime(String s)
	{
		playTime = s;
	}

	public String getTime()
	{
		return playTime;
	}

	public void setCategory(String s)
	{
		category = s;
	}

	public String getCategory()
	{
		return category;
	}

	public void setAlbumTrack(String s)
	{
		albumTrack = s;
	}

	public String getAlbumTrack()
	{
		return albumTrack;
	}

	public void setSongID(String s)
	{
		songID = s;
	}

	public String getSongID()
	{
		return songID;
	}

	public void setSongLink(String s)
	{
		songLink = s;
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
