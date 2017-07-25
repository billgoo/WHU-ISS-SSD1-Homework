// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Catfish.java


public class Catfish
{

	private int column;
	private int energyLevel;

	public Catfish()
	{
		column = 1;
		energyLevel = 10;
	}

	public int getColumn()
	{
		return column;
	}

	public void swimRight()
	{
		if (column + 1 <= 10)
		{
			column = column + 1;
			energyLevel = energyLevel - 1;
		}
	}

	public String getImage()
	{
		if (energyLevel < 5)
			return "/CatFish-tired.gif";
		else
			return "/CatFish.gif";
	}
}
