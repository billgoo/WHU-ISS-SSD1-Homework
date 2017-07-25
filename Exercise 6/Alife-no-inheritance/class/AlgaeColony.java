// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlgaeColony.java

import java.util.Random;

public class AlgaeColony extends LivingBeing
{

	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";
	private static final int BABY_MIN_ENERGY = 5;
	private static final int BABY_MAX_ENERGY = 255;
	private static final int ENERGY_TO_LIVE = 1;
	private static final String SPECIES = "Algae";
	private int row;
	private int column;
	private String deadOrAlive;
	private int energy;
	private int age;
	private final String name;
	private Simulation simulation;
	private int minEnergy;
	private int maxEnergy;
	private static int nAlgaeCreated = 0;

	public AlgaeColony(int initialRow, int initialColumn, Simulation initialSimulation)
	{
		age = 0;
		simulation = initialSimulation;
		deadOrAlive = "alive";
		if (initialRow > initialSimulation.getLastRow())
			row = initialSimulation.getLastRow();
		else
		if (initialRow < initialSimulation.getFirstRow())
			row = initialSimulation.getFirstRow();
		else
			row = initialRow;
		if (initialColumn > initialSimulation.getLastColumn())
			column = initialSimulation.getLastColumn();
		else
		if (initialColumn < initialSimulation.getFirstColumn())
			column = initialSimulation.getFirstColumn();
		else
			column = initialColumn;
		minEnergy = 5;
		maxEnergy = 255;
		energy = simulation.getRand().nextInt(maxEnergy - minEnergy) + minEnergy;
		age = 0;
		name = "Algae" + nAlgaeCreated;
		nAlgaeCreated++;
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	public int getAge()
	{
		return age;
	}

	public String getName()
	{
		return name;
	}

	public String getImage()
	{
		return "/blank.gif";
	}

	private int getMinEnergy()
	{
		return minEnergy;
	}

	private int getMaxEnergy()
	{
		return maxEnergy;
	}

	public int getEnergy()
	{
		return energy;
	}

	private void setEnergy(int newEnergy)
	{
		if (newEnergy < getMinEnergy())
		{
			energy = newEnergy;
			die();
		} else
		if (newEnergy > getMaxEnergy())
			energy = getMaxEnergy();
		else
			energy = newEnergy;
	}

	private void die()
	{
		deadOrAlive = "dead";
	}

	public boolean isDead()
	{
		return deadOrAlive == "dead";
	}

	public String getDisplayMechanism()
	{
		return "color";
	}

	public String getSpecies()
	{
		return "Algae";
	}

	public String getColor()
	{
		int energy = getEnergy();
		if (energy < 0)
			energy = 0;
		if (energy > 255)
			energy = 255;
		String greenLevel = Integer.toHexString(energy);
		if (energy < 16)
			greenLevel = "0" + greenLevel;
		return "#00" + greenLevel + "00";
	}

	public int giveUpEnergy(int energyWanted)
	{
		int energy = getEnergy();
		if (energyWanted < 0)
			return 0;
		if (energyWanted < energy)
		{
			setEnergy(energy - energyWanted);
		} else
		{
			energyWanted = energy;
			setEnergy(getMinEnergy() - 1);
		}
		return energyWanted;
	}

	public void liveALittle()
	{
		if (isDead())
		{
			return;
		} else
		{
			age++;
			int row = getRow();
			int column = getColumn();
			int sun = simulation.getSunlight(row, column);
			setEnergy(((int)((double)sun * 0.5D) + getEnergy()) - 1);
			return;
		}
	}

}
