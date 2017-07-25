// The classes in java.io package are needed to interact 
// with the user through console io 
// import java.io.*; 

// We need the class "Random" and "Vector" from the java.util package.
import java.util.Random;
import java.util.Vector;
/*
 * Created on Jan 6, 2004
 *
 */

/**
 * Simulation manager. Holds all living being objects. 
 * For each block of time, each living being will be given a chance to live. 
 * The living being can choose to live each block of time however it wishes. 
 * 
 * @author iCarnegie srt
 */
public class Simulation {

	/**
	 * maximum energy from Sunlight for a table-cell during a block of time.
	 */
	private static final int MAX_SUNLIGHT = 10;

	/**
	 * The display mechanism for living beings can be one of:
	 * - image
	 * - color
	 */
	public static final String IMAGE = "image";

	/**
	 * The display mechanism for living beings can be one of:
	 * - image
	 * - color
	 */
	public static final String COLOR = "color";

	// Concept example: final, but not static.
	// since two simulations may have different values.
	// Once the value is set in constructor, it cannot be changed 
	// during the Simulation object's entire lifetime.
	/**
	 * Index of the first row. 
	 */
	private final int firstRow;
	
	/**
	 * Index of the first column. 
	 */
	private final int firstColumn;

	/**
	 * Index of the last row. 
	 */
	private final int lastRow;

	/**
	 * Index of the last column
	 */
	private final int lastColumn;

	/**
	 * Random number generator
	 */
	private Random rand;

	/**
	 * The living beings simulated.
	 */
	private Vector livingBeings;

	/**
	 * Blocks of time since simulation started
	 */
	private int time = 0;

	/**
	 * Construct and initialize a new simulation object
	 * 
	 * @param initialFirstRow first row in the lake
	 * @param initialFirstColumn first column in the lake
	 * @param initialLastRow last row in the lake
	 * @param initialLastColumn last column in the lake
	 */
	public Simulation(
		int initialFirstRow,
		int initialFirstColumn,
		int initialLastRow,
		int initialLastColumn) {
		firstRow = initialFirstRow;
		firstColumn = initialFirstColumn;
		lastRow = initialLastRow;
		lastColumn = initialLastColumn;
		
		// Let us construct a Vector that will initially hold 50 beings.
		// For more information about this constructor, see Java API
		// http://java.sun.com/j2se/1.4.2/docs/api/java/util/Vector.html#Vector(int)
		livingBeings = new Vector(50);
		
		// we set the seed for random number generator to make the 
		// simulation sequence reproducible for a given initial world.
		// For more information about this constructor, see Java API
		// http://java.sun.com/j2se/1.4.2/docs/api/java/util/Random.html#Random(long)
		rand = new Random(7);
		time = 0;
	}

	/**
	 * Get the index of first row in the lake
	 * 
	 * @return first row in simulation
	 */
	public int getFirstRow() {
		return firstRow;
	}

	/**
	 * Get the index of first column in the lake
	 * 
	 * @return first columns in simulation
	 */
	public int getFirstColumn() {
		return firstColumn;
	}

	/**
	 * Get the index of last row in the lake
	 * 
	 * @return last row in simulation
	 */
	public int getLastRow() {
		return lastRow;
	}

	/**
	 * Get the index of last column in the lake
	 * 
	 * @return last column in simulation
	 */
	public int getLastColumn() {
		return lastColumn;
	}

	/**
	 * Get the amount of sunlight shining currently at a given location. 
	 * Expressed in units of energy.
	 * 
	 * @param row - the row-wise value of location
	 * @param column - the column-wise value of location
	 * @return - an integer that indicates the amount of energy
	 */
	public int getSunlight(int row, int column) {
		if (row >= getFirstRow() && row <= getLastRow() 
			&& column >= getFirstColumn() && column <= getLastColumn()) {
			return rand.nextInt(MAX_SUNLIGHT);			
		} else {
			return 0; // darkness outside the lake boundaries
		}
	}

	/**
	 * Get a random number generator
	 * 
	 * @return random number generator
	 */
	public Random getRand() {
		return rand;
	}

	/**
	 * Get the time block currently being simulated.
	 * 
	 * @return current time in simulation
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Add a living being to the simulation if it does not exist.
	 *
	 * @param newLivingBeing - the living being to be added 
	 */
	public void addLivingBeing(LivingBeing newLivingBeing) {

		if (newLivingBeing == null) {

			return;
		}

		// If this living being is already in the array, return.
		if (livingBeings.contains(newLivingBeing)) {

			return;
		}

		// Add to the vector.
		livingBeings.add(newLivingBeing);
	}

	/** 
	 * Locate all living beings within a specific distance from a given location.
	 *
	 * @param row - input location
	 * @param column - input location
	 * @param distance - distance from the input location. A value of of zero 
	 * will return only cohabitants in the specified cell.
	 * @return A vector containing living beings in a neighborhood.
	 */
	public Vector getNeighbors(int row, int column, int distance) {
		
		int beingIndex;
		Vector neighbors = new Vector();
		LivingBeing being;

		for (beingIndex = 0; beingIndex < livingBeings.size(); ++beingIndex) {
			being = (LivingBeing) livingBeings.get(beingIndex);
			if (being != null
				&& being.getRow() <= row + distance
				&& being.getRow() >= row - distance
				&& being.getColumn() <= column + distance
				&& being.getColumn() >= column - distance) {

				neighbors.add(being);
			}
		}

		return neighbors;
	}

	/**
	 * For all living beings in the simulation, give a little time to live.
	 */
	public void simulateATimeBlock() {

		int beingIndex;
		LivingBeing being;

		time = time + 1;
		
		// Let us visit the living beings in the order in which they are stored
		// in the livingBeings vector.
		for (beingIndex = 0; beingIndex < livingBeings.size(); ++beingIndex) {
			being = (LivingBeing) livingBeings.get(beingIndex);
			if (being != null && ! being.isDead()) {
				being.liveALittle();
			}
		}
		
		// Let us remove all the dead corpses.
		beingIndex = 0;
		while (beingIndex < livingBeings.size()) {
			being = (LivingBeing) livingBeings.get(beingIndex);
			if (being.isDead()) {
				livingBeings.remove(being);
			} else {
				++beingIndex;
			}
		}
	}
}
