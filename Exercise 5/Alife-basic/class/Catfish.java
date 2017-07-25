// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Catfish.java

import java.util.Random;

public class Catfish extends LivingBeing
{

	private String ALIVE;
	private String DEAD;
	private int ENERGY_TO_SWIM;
	private int row;
	private int column;
	private String imageFileName;
	private String deadOrAlive;
	private int age;
	private int energy;
	private Simulation simulation;

	public Catfish(int i, int j, Simulation simulation1)
	{
		ALIVE = "alive";
		DEAD = "dead";
		ENERGY_TO_SWIM = 2;
		simulation = simulation1;
		deadOrAlive = ALIVE;
		age = 0;
		energy = 10;
		imageFileName = "/Catfish-right.gif";
		row = i;
		column = j;
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	public String getImage()
	{
		return imageFileName;
	}

	public int getAge()
	{
		return age;
	}

	public int getEnergy()
	{
		return energy;
	}

	public void die()
	{
		deadOrAlive = DEAD;
	}

	public boolean isDead()
	{
		return deadOrAlive == DEAD;
	}

	private void swimIfPossible()
	{
		energy = energy - ENERGY_TO_SWIM;
		if (energy <= 0)
		{
			return;
		} else
		{
			row = simulation.getRand().nextInt(10);
			column = simulation.getRand().nextInt(10);
			return;
		}
	}

	public void liveALittle()
	{
		if (energy <= 0)
		{
			die();
			return;
		} else
		{
			age = age + 1;
			swimIfPossible();
			return;
		}
	}
}
