// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Simulation.java

import java.util.Random;
import java.util.Vector;

public class Simulation
{

	private static final int MAX_SUNLIGHT = 10;
	public static final String IMAGE = "image";
	public static final String COLOR = "color";
	private final int firstRow;
	private final int firstColumn;
	private final int lastRow;
	private final int lastColumn;
	private Random rand;
	private Vector livingBeings;
	private int time;

	public Simulation(int i, int j, int k, int l)
	{
		time = 0;
		firstRow = i;
		firstColumn = j;
		lastRow = k;
		lastColumn = l;
		livingBeings = new Vector(50);
		rand = new Random(7L);
		time = 0;
	}

	public int getFirstRow()
	{
		return firstRow;
	}

	public int getFirstColumn()
	{
		return firstColumn;
	}

	public int getLastRow()
	{
		return lastRow;
	}

	public int getLastColumn()
	{
		return lastColumn;
	}

	public int getSunlight(int i, int j)
	{
		if (i >= getFirstRow() && i <= getLastRow() && j >= getFirstColumn() && j <= getLastColumn())
			return rand.nextInt(10);
		else
			return 0;
	}

	public Random getRand()
	{
		return rand;
	}

	public int getTime()
	{
		return time;
	}

	public void addLivingBeing(LivingBeing livingbeing)
	{
		if (livingbeing == null)
			return;
		if (livingBeings.contains(livingbeing))
		{
			return;
		} else
		{
			livingBeings.add(livingbeing);
			return;
		}
	}

	public Vector getNeighbors(int i, int j, int k)
	{
		Vector vector = new Vector();
		for (int l = 0; l < livingBeings.size(); l++)
		{
			LivingBeing livingbeing = (LivingBeing)livingBeings.get(l);
			if (livingbeing != null && livingbeing.getRow() <= i + k && livingbeing.getRow() >= i - k && livingbeing.getColumn() <= j + k && livingbeing.getColumn() >= j - k)
				vector.add(livingbeing);
		}

		return vector;
	}

	public void simulateATimeBlock()
	{
		time = time + 1;
		for (int i = 0; i < livingBeings.size(); i++)
		{
			LivingBeing livingbeing = (LivingBeing)livingBeings.get(i);
			if (livingbeing != null && !livingbeing.isDead())
				livingbeing.liveALittle();
		}

		for (int j = 0; j < livingBeings.size();)
		{
			LivingBeing livingbeing1 = (LivingBeing)livingBeings.get(j);
			if (livingbeing1.isDead())
				livingBeings.remove(livingbeing1);
			else
				j++;
		}

	}
}
