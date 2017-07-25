// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Crocodile.java

import java.util.Random;
import java.util.Vector;

public class Crocodile extends LivingBeing
{

	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";
	private static final int ENERGY_TO_WADE = 10;
	private static final int ENERGY_TO_EAT = 10;
	private static final int BABY_MIN_ENERGY = 1000;
	private static final int BABY_MAX_ENERGY = 2000;
	private static final int MIN_ENERGY_GROWTH_INCREMENT = 5;
	private static final int MAX_ENERGY_GROWTH_INCREMENT = 10;
	private static final String RIGHT = "right";
	private static final String LEFT = "left";
	private static final String UP = "up";
	private static final String DOWN = "down";
	private static final String SPECIES = "Crocodile";
	private int row;
	private int column;
	private String deadOrAlive;
	private int energy;
	private int age;
	private final String name;
	private Simulation simulation;
	private int minEnergy;
	private int maxEnergy;
	private String direction;
	private static int nCrocodilesCreated = 0;

	public Crocodile(int initialRow, int initialColumn, Simulation initialSimulation)
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
		minEnergy = 1000;
		maxEnergy = 2000;
		energy = simulation.getRand().nextInt(maxEnergy - minEnergy) + minEnergy;
		age = 0;
		name = "Crocodile" + nCrocodilesCreated;
		direction = "right";
		nCrocodilesCreated++;
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

	public String getColor()
	{
		return "#FFFFFF";
	}

	public String getName()
	{
		return name;
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

	private String getDirection()
	{
		return direction;
	}

	private boolean isHungry()
	{
		return getEnergy() < 2 * getMinEnergy();
	}

	private int moveToRow(int newRow)
	{
		if (isDead())
			return -1;
		if (newRow > simulation.getLastRow())
			newRow = simulation.getLastRow();
		else
		if (newRow < simulation.getFirstRow())
			newRow = simulation.getFirstRow();
		if (newRow < row)
			direction = "up";
		else
		if (newRow > row)
			direction = "down";
		row = newRow;
		return row;
	}

	private int moveToColumn(int newColumn)
	{
		if (isDead())
			return -1;
		if (newColumn > simulation.getLastColumn())
			newColumn = simulation.getLastColumn();
		else
		if (newColumn < simulation.getFirstColumn())
			newColumn = simulation.getFirstColumn();
		if (newColumn < column)
			direction = "left";
		else
		if (newColumn > column)
			direction = "right";
		column = newColumn;
		return column;
	}

	public String getSpecies()
	{
		return "Crocodile";
	}

	public String getDisplayMechanism()
	{
		return "image";
	}

	public String getImage()
	{
		if (getDirection() == "right")
			return "/Crocodile-right.gif";
		if (getDirection() == "left")
			return "/Crocodile-left.gif";
		if (getDirection() == "up")
			return "/Crocodile-up.gif";
		if (getDirection() == "down")
			return "/Crocodile-down.gif";
		else
			return "Crocodile-right.gif";
	}

	private void wadeIfPossible()
	{
		setEnergy(getEnergy() - 10);
		if (isDead())
		{
			return;
		} else
		{
			int firstRow = simulation.getFirstRow();
			int firstColumn = simulation.getFirstColumn();
			int lastRow = simulation.getLastRow();
			int lastColumn = simulation.getLastColumn();
			int newRow = simulation.getRand().nextInt((lastRow - firstRow) + 1);
			int newColumn = simulation.getRand().nextInt((lastColumn - firstColumn) + 1);
			newRow += firstRow;
			newColumn += firstColumn;
			moveToRow(newRow);
			moveToColumn(newColumn);
			return;
		}
	}

	private void eatIfPossible()
	{
		if (isDead())
			return;
		Vector foodMaybe = simulation.getNeighbors(getRow(), getColumn(), 0);
		for (int neighborIndex = 0; neighborIndex < foodMaybe.size(); neighborIndex++)
			if (foodMaybe.get(neighborIndex) instanceof Catfish)
			{
				Catfish fish = (Catfish)foodMaybe.get(neighborIndex);
				int energyGained = fish.getEnergy();
				fish.die();
				setEnergy((getEnergy() + energyGained) - 10);
			}

	}

	public void liveALittle()
	{
		if (isDead())
		{
			return;
		} else
		{
			age++;
			wadeIfPossible();
			eatIfPossible();
			minEnergy = getMinEnergy() + 5;
			maxEnergy = getMaxEnergy() + 10;
			return;
		}
	}

}
