package game;

import java.awt.*;

/**
 * Game tile class where when instanced and given parameters renders a game tile with a dot based on those parameters.
 */
public class GameTileDot {
    private double row;
    private double col;
    private int widthOfTile;
    private int heightOfTile;
    private double rowDot;
    private double colDot;
    private Color color;

    /**
     * Constructor for Guard
     * @param row row position
     * @param col col position
     * @param rowDot row position of the dot
     * @param colDot col position of the dot
     * @param widthOfTile width of tile
     * @param heightOfTile height of tile
     * @param color color of the tile
     */
    protected GameTileDot(double row, double col,double rowDot,double colDot,int widthOfTile,int heightOfTile,Color color){
        this.row          = row;
        this.col          = col;
        this.widthOfTile  = widthOfTile;
        this.heightOfTile = heightOfTile;
        this.color        = color;
        this.rowDot       = rowDot;
        this.colDot       = colDot;

    }

    /**
     * renders tile with a dot on the board based on row/col and width/height and row/col for the dot.
     * @param g
     */
    protected void render(Graphics g){
        int tileX = (int) (this.col * this.widthOfTile);
        int tileY = (int) (this.row * this.heightOfTile);
        int tileDotX = (int) (this.colDot * this.widthOfTile);
        int tileDotY = (int) (this.rowDot * this.heightOfTile);

        g.setColor(this.color);
        g.fillRect(tileX,tileY,this.widthOfTile, this.heightOfTile);
        g.setColor(Color.GRAY);
        g.fillOval(tileDotX,tileDotY,this.widthOfTile/2,this.heightOfTile/2);
    }
}
