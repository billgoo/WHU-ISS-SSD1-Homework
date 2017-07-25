public class Catfish {

    /**
     * Location of the catfish - which column.
     */
    private int column = 1;

    /** 
     * Energy level of catfish
     */
    private int energyLevel = 10;

    /**
     * Location of catfish - the column
     * 
     * @return - an integer representing the column location of catfish.
     */
    public int getColumn() {
	// Student: return the column value
        return column;
    }

    /**
     * Swim one cell to the right by incrementing the value stored in column by 1.
     */
    public void swimRight() {
	// Student: Increment the value stored in column attribute by 1 and
	// decrement the value stored in energyLevel by 1 if the 
	// new value of column is less than or equal to 10.
        if (column + 1 <= 10)
        {
            column = column + 1;
            energyLevel = energyLevel - 1;
        }
    }

    /**
     * get the image of catfish
     *
     * @return a String indicating the filename of catfish image
     */
    public String getImage() {
	// Student: return the image filename that represents the catfish
	// The image of a tired catfish (a catfish with energyLevel less than 5)
	// is "/CatFish-tired.gif".
	// The iamge of a catfish that is not tired is "/CatFish.gif".
        if (energyLevel < 5) {
            return "/CatFish-tired.gif";
        }else {
            return "/CatFish.gif";
        }
    }
}
