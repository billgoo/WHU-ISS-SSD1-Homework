// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Catfish.java

import java.util.Random;
import java.util.Vector;

public class Catfish extends LivingBeing
{

	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";
	private static final int ENERGY_TO_SWIM = 2;
	private static final int DEBUG = 0;
	private static final int ENERGY_TO_LOOK_FOR_FOOD = 1;
	private static final int ENERGY_TO_EAT = 1;
	private static final int ENERGY_IN_A_FULL_MEAL = 10;
	private static final int BABY_MIN_ENERGY = 15;
	private static final int BABY_MAX_ENERGY = 100;
	private static final int MIN_ENERGY_GROWTH_INCREMENT = 5;
	private static final int MAX_ENERGY_GROWTH_INCREMENT = 10;
	private static final String RIGHT = "right";
	private static final String LEFT = "left";
	private static final String UP = "up";
	private static final String DOWN = "down";
	private static final String SPECIES = "Catfish";
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
	private static int nCatfishCreated = 0;

	public Catfish(int initialRow, int initialColumn, Simulation initialSimulation)
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
		minEnergy = 15;
		maxEnergy = 100;
		energy = simulation.getRand().nextInt(maxEnergy - minEnergy) + minEnergy;
		age = 0;
		name = "Catfish" + nCatfishCreated;
		direction = "right";
		nCatfishCreated++;
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

	public void die()
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
		return "Catfish";
	}

	public String getDisplayMechanism()
	{
		return "image";
	}

	public String getImage()
	{
		if (getDirection() == "right")
			return "/Catfish-right.gif";
		if (getDirection() == "left")
			return "/Catfish-left.gif";
		if (getDirection() == "up")
			return "/Catfish-up.gif";
		if (getDirection() == "down")
			return "/Catfish-down.gif";
		else
			return "Catfish-right.gif";
	}

	private AlgaeColony lookForFoodInNeighborhood()
	{
		setEnergy(getEnergy() - 1);
		if (isDead())
			return null;
		Vector neighbors = simulation.getNeighbors(getRow(), getColumn(), 1);
		for (int neighborIndex = 0; neighborIndex < neighbors.size(); neighborIndex++)
			if (neighbors.get(neighborIndex) instanceof AlgaeColony)
				return (AlgaeColony)neighbors.get(neighborIndex);

		return null;
	}

	private void swimIfPossible()
	{
		if (isDead())
			return;
		if (isHungry())
		{
			AlgaeColony food = lookForFoodInNeighborhood();
			if (food != null)
			{
				setEnergy(getEnergy() - 2);
				if (isDead())
					return;
				moveToRow(food.getRow());
				moveToColumn(food.getColumn());
			}
			return;
		}
		setEnergy(getEnergy() - 2);
		if (isDead())
			return;
		int direction = simulation.getRand().nextInt(4);
		if (direction == 0)
			moveToRow(getRow() - 1);
		if (direction == 1)
			moveToRow(getRow() + 1);
		if (direction == 2)
			moveToColumn(getColumn() - 1);
		if (direction == 3)
			moveToColumn(getColumn() + 1);
	}

	private void eatIfPossible()
	{
		if (isDead())
			return;
		Vector foodMaybe = simulation.getNeighbors(getRow(), getColumn(), 0);
		for (int neighborIndex = 0; neighborIndex < foodMaybe.size(); neighborIndex++)
			if (foodMaybe.get(neighborIndex) instanceof AlgaeColony)
			{
				AlgaeColony alg = (AlgaeColony)foodMaybe.get(neighborIndex);
				int energyGained = alg.giveUpEnergy(10);
				setEnergy((getEnergy() + energyGained) - 1);
				return;
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
			swimIfPossible();
			eatIfPossible();
			minEnergy = getMinEnergy() + 5;
			maxEnergy = getMaxEnergy() + 10;
			return;
		}
	}

}
