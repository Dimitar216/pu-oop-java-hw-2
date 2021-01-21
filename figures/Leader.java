package figures;

import java.awt.*;

/**
 * This class creates an instance of a figure on the board and renders it.
 */
public class Leader extends Figures {

    private Color color;
    /**
     * Constructor for Leader
     * @param row row position
     * @param col col position
     * @param widthOfTile width of Leader
     * @param heightOfTile height of Leader
     * @param color color of figure
     */
    public Leader(double row, double col,int widthOfTile,int heightOfTile,Color color){
        this.row          = row;
        this.col          = col;
        this.widthOfTile  = widthOfTile;
        this.heightOfTile = heightOfTile;
        this.color        = color;
        this.idOfFigure = 0;
    }

    /**
     * renders figure on the board based on row/col and width/height.
     * @param g
     */
    public void render(Graphics g){
        int tileX = (int) (this.col * this.widthOfTile);
        int tileY = (int) (this.row * this.heightOfTile);

        g.setColor(this.color);
        g.fillRect(tileX+22,tileY+20,this.widthOfTile-40, this.heightOfTile-40);

    }

    /**
     * Method which checks if the move of the figure is valid.
     * @param moveRow row it moves to.
     * @param moveCol col it moves to.
     * @return true if the move is valid and false if not.
     */
    @Override
    public boolean isMoveValid(int moveRow, int moveCol) {
        int rowCoefficient = (int) Math.abs(moveRow-this.row);
        int colCoefficient = (int) Math.abs(moveCol - this.col);

        return rowCoefficient == 0 && colCoefficient >= 1 || rowCoefficient >= 1 && colCoefficient == 0;
    }
}