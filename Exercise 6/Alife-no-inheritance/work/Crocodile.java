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
	private int age = 0;
	private final String name;
	private Simulation simulation;
	private int minEnergy;
	private int maxEnergy;
	private String direction;
	private static int nCrocodilesCreated = 0;
    public Crocodile(int initialRow, int initialColumn, Simulation initialSimulation) {
        this.simulation = initialSimulation;
        this.deadOrAlive = "alive";
        if(initialRow > initialSimulation.getLastRow()) {
            this.row = initialSimulation.getLastRow();
        } else if(initialRow < initialSimulation.getFirstRow()) {
            this.row = initialSimulation.getFirstRow();
        } else {
            this.row = initialRow;
        }
        if(initialColumn > initialSimulation.getLastColumn()) {
            this.column = initialSimulation.getLastColumn();
        } else if(initialColumn < initialSimulation.getFirstColumn()) {
            this.column = initialSimulation.getFirstColumn();
        } else {
            this.column = initialColumn;
        }
        this.minEnergy = 1000;
        this.maxEnergy = 2000;
        this.energy = this.simulation.getRand().nextInt(this.maxEnergy - this.minEnergy) + this.minEnergy;
        this.age = 0;
        this.name = "Crocodile" + nCrocodilesCreated;
        this.direction = "right";
        ++nCrocodilesCreated;
    }
    public int getRow() {
        return this.row;
    }
    public int getColumn() {
        return this.column;
    }
    public int getAge() {
        return this.age;
    }
    public String getColor() {
        return "#FFFFFF";
    }
    public String getName() {
        return this.name;
    }
    private int getMinEnergy() {
        return this.minEnergy;
    }
    private int getMaxEnergy() {
        return this.maxEnergy;
    }
    public int getEnergy() {
        return this.energy;
    }
    private void setEnergy(int newEnergy) {
        if(newEnergy < this.getMinEnergy()) {
            this.energy = newEnergy;
            this.die();
        } else if(newEnergy > this.getMaxEnergy()) {
            this.energy = this.getMaxEnergy();
        } else {
            this.energy = newEnergy;
        }
    }
    private void die() {
        this.deadOrAlive = "dead";
    }
    public boolean isDead() {
        return this.deadOrAlive == "dead";
    }
    private String getDirection() {
        return this.direction;
    }
    private boolean isHungry() {
        return this.getEnergy() < 2 * this.getMinEnergy();
    }
    private int moveToRow(int newRow) {
        if(this.isDead()) {
            return -1;
        } else {
            if(newRow > this.simulation.getLastRow()) {
                newRow = this.simulation.getLastRow();
            } else if(newRow < this.simulation.getFirstRow()) {
                newRow = this.simulation.getFirstRow();
            }
            if(newRow < this.row) {
                this.direction = "up";
            } else if(newRow > this.row) {
                this.direction = "down";
            }
            this.row = newRow;
            return this.row;
        }
    }
    private int moveToColumn(int newColumn) {
        if(this.isDead()) {
            return -1;
        } else {
            if(newColumn > this.simulation.getLastColumn()) {
                newColumn = this.simulation.getLastColumn();
            } else if(newColumn < this.simulation.getFirstColumn()) {
                newColumn = this.simulation.getFirstColumn();
            }
            if(newColumn < this.column) {
                this.direction = "left";
            } else if(newColumn > this.column) {
                this.direction = "right";
            }
            this.column = newColumn;
            return this.column;
        }
    }
    public String getSpecies() {
        return "Crocodile";
    }
    public String getDisplayMechanism() {
        return "image";
    }
    public String getImage() {
        return this.getDirection() == "right"?"/Crocodile-right.gif":(this.getDirection() == "left"?"/Crocodile-left.gif":(this.getDirection() == "up"?"/Crocodile-up.gif":(this.getDirection() == "down"?"/Crocodile-down.gif":"Crocodile-right.gif")));
    }
    private void wadeIfPossible() {
        this.setEnergy(this.getEnergy() - 10);
        if(!this.isDead()) {
            int firstRow = this.simulation.getFirstRow();
            int firstColumn = this.simulation.getFirstColumn();
            int lastRow = this.simulation.getLastRow();
            int lastColumn = this.simulation.getLastColumn();
            int newRow = this.simulation.getRand().nextInt(lastRow - firstRow + 1);
            int newColumn = this.simulation.getRand().nextInt(lastColumn - firstColumn + 1);
            newRow += firstRow;
            newColumn += firstColumn;
            this.moveToRow(newRow);
            this.moveToColumn(newColumn);
        }
    }
    private void eatIfPossible() {
        if(!this.isDead()) {
            Vector foodMaybe = this.simulation.getNeighbors(this.getRow(), this.getColumn(), 0);
            for(int neighborIndex = 0; neighborIndex < foodMaybe.size(); ++neighborIndex) {
                if(foodMaybe.get(neighborIndex) instanceof Catfish) {
                    Catfish fish = (Catfish)foodMaybe.get(neighborIndex);
                    int energyGained = fish.getEnergy();
                    fish.die();
                    this.setEnergy(this.getEnergy() + energyGained - 10);
                }
            }
        }
    }
    public void liveALittle() {
        if(!this.isDead()) {
            ++this.age;
            this.wadeIfPossible();
            this.eatIfPossible();
            this.minEnergy = this.getMinEnergy() + 5;
            this.maxEnergy = this.getMaxEnergy() + 10;
        }
    }
}