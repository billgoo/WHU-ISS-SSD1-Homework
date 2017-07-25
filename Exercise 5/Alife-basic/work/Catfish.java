import java.util.*;
/*
 * Created on Jan 6, 2004
 *
 */

/**
 * Basic Catfish - simulates a catfish - can swim and consume 
 * energy in the process.
 * 
 * @author iCarnegie srt
 *
 */
public class Catfish extends LivingBeing {

	/**
	 * The catfish is born "alive". 
	 * Then it dies, becoming a corpse. 
	 */
	private String ALIVE = "alive";


	/**
	 * The catfish is born "alive". 
	 * Then it dies, becoming a "dead" corpse. 
	 */
	private String DEAD = "dead";



	/**
	 * Energy needed to swim in a block of time.
	 */
	private int ENERGY_TO_SWIM = 2;
		
	
	
	/**
	 * Row-wise location of the catfish
	 */
	private int row;


	/**
	 * Column-wise location of the catfish
	 */
	private int column;


	/**
	 * Image of the catfish - is really a filename
	 */
	private String imageFileName;

	
	/**
	 * Is the catfish dead or alive?
	 */
	private String deadOrAlive;
	

	/**
	 * Age expressed as blocks of time lived
	 */
	private int age;
	private int energy;


	/**
	 * The simulation to which this catfish belongs.
	 * This is needed so the catfish can send a message 
	 * to simulation.
	 */
	private Simulation simulation;

	

	/**
	 * Constructor. Initialize a catfish to start life at a specified 
	 * location with a specified energy. If location is out of bounds,
	 * locate the catfish at the nearest edge.
	 * 
	 * @param initialRow - the row at which the catfish is located
	 * @param initialColumn - the column at which the catfish is located
	 * @param initialSimulation - the simulation that the catfish belongs to
	 */
	public Catfish(
		int initialRow,
		int initialColumn,
		Simulation initialSimulation) {

			simulation = initialSimulation;

			deadOrAlive = ALIVE; 	

			age = 0;
			energy = 10;		
			
			imageFileName = "/Catfish-right.gif";	

			row = initialRow;
			column = initialColumn;
	}
	
	/**
	 * Get the row at which the catfish is located 
	 * 
	 * @return - the row of the catfish's location. 
	 */		
	public int getRow() {
		return row;
	}

	/**
	 * Get the column at which the catfish is located
	 * 
	 * @return - the column of the catfish's location. 
	 */		
	public int getColumn() {
		return column;
	}


	/**
	 * get filename of catfish image
	 * 
	 * @return filename of Catfish image
	 */
	public String getImage()
	{
		return imageFileName;
	}


	/**
	 * Get the catfish's age
	 * 
	 * @return the age of the catfish expressed in blocks of time
	 */
	public int getAge() {

		// Student:Please complete this method.
		return age;

	}


	/**
	 * Get the energy of the catfish
	 * 
	 * @return current energy level of the catfish
	 */
	public int getEnergy() {

		// Student:Please complete this method.
		return energy;
	}
	

	/**
	 * Die: The Catfish dies.
	 */
	public void die() {

		// Student:Please complete this method.
		// Set the deadOrAlive attribute to indicate that the catfish is dead.
		deadOrAlive = DEAD;
	}



	/**
	 * Is the catfish dead?
	 * 
	 * @return <code>true</code> if dead. <code>false</code>, otherwise.
	 */
	public boolean isDead() {


		if(deadOrAlive == DEAD)
			return true;
		else
			return false;
	}


	
	/** 
	 * Swim to a new location if possible.
	 * Consumes some energy.
	 */
	private void swimIfPossible() {


		// Student:Please complete this method.

		
		// Consume ENERGY_TO_SWIM units of energy to swim. 


		// Check if there is any energy left after consumption.
		

		// Swim at random in one of four directions.
		// Assign a random row location for the Catfish as follows. 
		//
		// (1) Send the "getRand" message to the "simulation" object to get a random number generator. 
		// The "simulation" object is initialized in the constructor above.
		// (2) Send the "nextInt" message to the random number generator obtained above. 
		//
		// The "nextInt" behavior returns an integer between 0(inclusive) and the integer specified as a parameter(exclusive). 
		// Thus, specifiying a value of 10 to the "nextInt" behavior will return an integer between 0 and 9.
		//
			energy = energy - ENERGY_TO_SWIM;
			if (energy <= 0)
			{
				return;
			}else
			{
				row = simulation.getRand().nextInt(10);
				column = simulation.getRand().nextInt(10);
				return;
			}


		// Similarly, assign a random column location for the catfish
		
	}

	

	/**
	 * Catfish lives its life. Dies if it has no energy left.
	 */
	public void liveALittle() {

		// Student:Please complete this method.

		// If there is no energy left, send a "die" message to this catfish
		

		// Increment the age of the Catfish by 1
		
		// Try to swim by sending a "swimIfPossible" message
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
