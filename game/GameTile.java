package game;

import java.awt.*;

/**
 * Game tile class where when instanced and given parameters renders a game tile based on those parameters.
 */
public class GameTile {

    private double row;
    private double col;
    private int widthOfTile;
    private int heightOfTile;
    private Color color;

    /**
     * Constructor for Guard
     * @param row row position
     * @param col col position
     * @param widthOfTile width of tile
     * @param heightOfTile height of tile
     * @param color color of the tile
     */
    protected GameTile(double row, double col,int widthOfTile,int heightOfTile,Color color){
        this.row          = row;
        this.col          = col;
        this.widthOfTile  = widthOfTile;
        this.heightOfTile = heightOfTile;
        this.color        = color;
    }

    /**
     * renders tile on the board based on row/col and width/height.
     * @param g
     */
    protected void render(Graphics g){
        int tileX = (int) (this.col * this.widthOfTile);
        int tileY = (int) (this.row * this.heightOfTile);

        g.setColor(this.color);
        g.fillRect(tileX,tileY,this.widthOfTile, this.heightOfTile);
    }
}