/*
 * Created on Jul 4, 2003
 *
 */

/**
 * Each living being lives in a location (row, column) at any given time. 
 * More than one living being can be in a location. E.g.: algae and fish.
 * Depending on the species, living beings could move. 
 * <p>
 * Each living being has some energy level. If the energy level falls below
 * the minimum needed to live, it dies. When a living being dies, it is deleted
 * from the simulation.
 * </p>
 * 
 * @author iCarnegie av
 */
public abstract class LivingBeing {

/*!Begin Snippet:createLivingBeing*/
	/**
	 * Create an organism that belongs to a specified species at
	 * a specified location and add it to the specified simulation.
	 * 
	 * @param simulation - the simulation this organism belongs to
	 * @param species - the organism's species
	 * @param value - row and column values specified as String from HTML form.
	 * row value is the last two digits, 
	 * column value is given in the remaining digits.
	 */
	public static void createLivingBeing(
		Simulation sim,
		String species,
		String value) {
		int rowAndCol = Integer.parseInt(value);
		int row = rowAndCol / 100;
		int column = rowAndCol - (100 * row);

		if (species.equals("algae")) {
			sim.addLivingBeing(new AlgaeColony(row, column, sim));
		}

		if (species.equals("catfish")) {
			sim.addLivingBeing(new Catfish(row, column, sim));
		}

		if (species.equals("crocodile")) {
			sim.addLivingBeing(new Crocodile(row, column, sim));
		}

	}
/*!End Snippet:createLivingBeing*/

	
	/**
	 * Get the row at which the living being is located 
	 * 
	 * @return - the row of the living being's location. 
	 */		
	public abstract int getRow();

	/**
	 * Get the column at which the living being is located
	 * 
	 * @return - the column of the living being's location. 
	 */		
	public abstract int getColumn();

	/**
	 * Get the living being's age
	 * 
	 * @return the age of the living being expressed in blocks of time
	 */
	public abstract int getAge();

	/**
	 * Color of the living being expressed in hex notation.
	 * For example, the "green-est" color is "#00FF00",
	 * "blue-est" is "#0000FF", the "red-est" is "#FF0000".
	 * 
	 * @return the rgb color in hex notation. preceded by a pound character '#'
	 */
	public abstract String getColor();

	/**
	 * Get the name of this living being
	 * 
	 * @return the name of the living being.
	 */
	public abstract String getName();

	/**
	 * Get the filename that contains the image of the living being
	 * 
	 * @return the image of the living being.
	 */
	public abstract String getImage();

	/**
	 * Get the preferred display mechanism for living being
	 * 
	 * @return one of the constants IMAGE or COLOR, 
	 * depending on the display mechanism for the living being.
	 */
	public abstract String getDisplayMechanism();
	
	/**
	 * Returns the species.
	 * 
	 * @return the species
	 */
	public abstract String getSpecies();

	/**
	 * Get the energy currently carried by the living being.
	 * 
	 * @return current energy level of the organism
	 */
	public abstract int getEnergy();

	/**
	 * Is the living being dead?
	 * 
	 * @return <code>true</code> if dead. <code>false</code>, otherwise.
	 */
	public abstract boolean isDead();

	/**
	 * The living being's age increases by one time block.
	 */
	public abstract void liveALittle();
}