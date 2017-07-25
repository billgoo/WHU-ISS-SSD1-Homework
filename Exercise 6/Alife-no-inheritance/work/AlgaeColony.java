/**
 * AlgaeColony does not move. If there is sunlight, a portion of the solar 
 * energy will be converted into life-energy.
 * 
 */
public class AlgaeColony extends LivingBeing {

	/**
	 * The algae is born "alive". 
	 * Then it dies, becoming a corpse. 
	 */
	private static final String ALIVE = "alive";

	/**
	 * The algae is born "alive". 
	 * Then it dies, becoming a "dead" corpse. 
	 */
	private static final String DEAD = "dead";

	/**
	 * Lowest possible energy needed for a baby to survive. 
	 */
	private static final int BABY_MIN_ENERGY = 5;

	/**
	 * Maximum energy that a baby can store. 
	 */
	private static final int BABY_MAX_ENERGY = 255;
	
	/**
	 * Amount of energy needed to live for a block of time.
	 */
	private static final int ENERGY_TO_LIVE = 1;
	
	/**
	 * Name of species
	 */	
	private static final String SPECIES = "Algae";

	/**
	 * Row-wise location of the algae
	 */
	private int row;

	/**
	 * Column-wise location of the algae
	 */
	private int column;

	/**
	 * Is the algae dead or alive?
	 */
	private String deadOrAlive;
	
	/**
	 * Amount of energy the algae has.
	 */
	private int energy;

	/**
	 * Age expressed as blocks of time lived
	 */
	private int age = 0;

	/**
	 * Name of this algae.
	 */
	private final String name;

	/**
	 * The simulation to which this algae belongs.
	 * This is needed so the algae can send a message 
	 * to simulation and ask
	 * for prey (or predator) in the neighboring locations. 
	 * Prey is food. Food is good!
	 */
	private Simulation simulation;

	/**
	 * Minimum energy level needed to survive.
	 * The minimum could increase as the individual grows.
	 */
	private int minEnergy;
	
	/**
	 * Maximum energy level that the algae could carry.
	 * The maximum could change as the individual grows.
	 */
	private int maxEnergy;

	/**
	 * Number of Algae objects created so far.
	 */
	private static int nAlgaeCreated = 0;

	
	/**
	 * Constructor. Initialize an algae to start life at a specified 
	 * location with a specified energy. If location is out of bounds,
	 * locate the algae at the nearest edge.
	 * 
	 * @param initialRow - the row at which the algae is located
	 * @param initialColumn - the column at which the algae is located
	 * @param initialSimulation - the simulation that the algae belongs to
	 */
	public AlgaeColony(
		int initialRow,
		int initialColumn,
		Simulation initialSimulation) {
	/*!End Snippet:constructorSignature*/	
			
			
			simulation = initialSimulation;

			deadOrAlive = ALIVE; 

			// Set the Row within bounds
			if (initialRow > initialSimulation.getLastRow()) {
				row = initialSimulation.getLastRow();
			} else if (initialRow < initialSimulation.getFirstRow()) {
				row = initialSimulation.getFirstRow();
			} else {
				row = initialRow;
			}

			// Set the Column within bounds
			if (initialColumn > initialSimulation.getLastColumn()) {
				column = initialSimulation.getLastColumn();
			} else if (initialColumn < initialSimulation.getFirstColumn()) {
				column = initialSimulation.getFirstColumn();
			} else {
				column = initialColumn;
			}

			// Set the minEnergy and maxEnergy
			minEnergy = BABY_MIN_ENERGY;
			maxEnergy = BABY_MAX_ENERGY;

			energy =
				simulation.getRand().nextInt(maxEnergy - minEnergy) + minEnergy;

			age = 0;
			name = SPECIES + nAlgaeCreated;
			++nAlgaeCreated;
	}

	/**
	 * Get the row at which the algae is located 
	 * 
	 * @return - the row of the algae's location. 
	 */		
	public int getRow() {
		return row;
	}

	/**
	 * Get the column at which the algae is located
	 * 
	 * @return - the column of the algae's location. 
	 */		
	public int getColumn() {
		return column;
	}

	/**
	 * Get the algae's age
	 * 
	 * @return the age of the algae expressed in blocks of time
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Get the name of this algae
	 * 
	 * @return the name of the algae.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the filename that contains the image of the algae
	 * 
	 * @return the image of the algae.
	 */
	public String getImage() {
		return "/blank.gif";
	}
	
	/**
	 * Get the minimum energy needed to live.
	 * 
	 * @return the minimum energy needed for the algae to live.
	 */
	private int getMinEnergy() {
		return minEnergy;
	}
	
	/**
	 * get the maximum energy that the algae can carry.
	 * 
	 * @return the maximum energy the algae can carry.
	 */
	private int getMaxEnergy() {
		return maxEnergy;
	}
	
	/**
	 * Get the energy currently carried by the algae.
	 * 
	 * @return current energy level of the organism
	 */
	public int getEnergy() {
		return energy;
	}


	/**
	 * Sets energy level.
	 * If new energy level is less than minimum energy level, the organism dies.
	 * New energy level is capped at maximum energy level.
	 */
	private void setEnergy(int newEnergy) {
		
		if (newEnergy < getMinEnergy()) {
			energy = newEnergy;
			die();
		} else if (newEnergy > getMaxEnergy()) {
			energy = getMaxEnergy();
		} else {
			energy = newEnergy;
		}
	}

	/**
	 * Die: Change the deadOrAlive to DEAD.
	 */
	private void die() {
		deadOrAlive = DEAD;
	}

	/**
	 * Is the algae dead?
	 * 
	 * @return <code>true</code> if dead. <code>false</code>, otherwise.
	 */
	public boolean isDead() {
		return (deadOrAlive == DEAD);
	}


	/**
	 * The mechanism to display Algae is to use its color.
	 * 
	 * @return a constant defined in {@link Simulation#COLOR Simulation} class
	 */
	public String getDisplayMechanism() {
		return Simulation.COLOR;
	}

	
	/**
	 * Get the species that the algae belongs to
	 * 
	 * @return a string indicating the species.
	 */
	public String getSpecies() {
		return SPECIES;
	}

	/**
	 * Get the color of Algae
	 * 
	 * @return - the color as a string in hexademinal notation
	 */
	public String getColor() {

		int energy = getEnergy();
		// Convert energy scale into green scale expressed in hex form.

		// Let us limit the energy to the color range of 0 to 255.
		if (energy < 0) {
			energy = 0;
		}
		
		if (energy > 255) {
			energy = 255;
		}

		String greenLevel = Integer.toHexString(energy);

		// If energy is a value less than 16, 
		// there will be only one character.
		// Since we need 2 characters for green, we pad left with a "0".
		if (energy < 16) {
			greenLevel = "0" + greenLevel;
		}

		// Now prepend with "00" to indicate there is no red 
		// and append with "00" to indicate there is no blue.
		return "#00" + greenLevel + "00";
	}

	/**
	 * Algae is being eaten up. 
	 * So, relinquish energy up to the amount requested.
	 * If no energy remains, die.
	 * 
	 * @param energyWanted - amount of energy requested - expressed as int.
	 * @return - the amount of energy that algae can give up. 
	 * If the requested energy is greater than the available energy,
	 * only the available energy will be given up. 
	 */
	public int giveUpEnergy(int energyWanted) {

		int energy = getEnergy();

		// We do not know what it means to want negative energy!!!
		if (energyWanted < 0) {
			return 0;
		}

		if (energyWanted < energy) {
			setEnergy(energy - energyWanted);
		} else // caller is asking for more than energy than available. 
			// Give only what I have. 
			// I will die since my energy level falls below minimum.
			{
			energyWanted = energy;
			setEnergy(getMinEnergy() - 1);
		}
		return energyWanted;
	}

	/**
	 * Algae lives its life. May gain or lose energy.
	 */
	public void liveALittle() {

		if (isDead()) {
			return;
		}

		age = age + 1;
		int row = getRow();
		int column = getColumn();
		int sun = simulation.getSunlight(row, column);

		// Let us assume that algae can convert 50% of 
		// solar energy into life-energy.
		// Algae has to give up some energy to live.
		setEnergy((int) (sun * 0.5) + getEnergy() - ENERGY_TO_LIVE);
	}

}
