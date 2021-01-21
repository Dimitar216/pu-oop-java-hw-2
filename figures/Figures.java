package figures;

/**
 * Abstract class for Guard/Leader to inherit.
 */
public abstract class Figures {
    protected double row;
    protected double col;
    protected int widthOfTile;
    protected int heightOfTile;
    public byte idOfFigure;

    /**
     * Method which changes the row and col of the figure.
     * @param row new row of the figure.
     * @param col new col of the figure.
     */
    public void move(int row,int col){
        this.row = row;
        this.col = col;
    }

    /**
     * Method which checks if the move of the figure is valid.
     * @param moveRow row it moves to.
     * @param moveCol col it moves to.
     * @return true if the move is valid and false if not.
     */
    public abstract boolean isMoveValid(int moveRow,int moveCol);
}
