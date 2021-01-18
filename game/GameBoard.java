package game;

import figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

/**
 * Gameboard class where everything is initialized and rendered
 */
public class GameBoard extends JFrame implements MouseListener {
    private int originalRow;
    private int originalCol;
    private static final int TILE_SIZE = 100;
    private static final int rowOfCollection = 6;
    private static final int colOfCollection = 6;
    private Object[][] figureCollection;
    private Object selectedFigure;

    GameTile redTileOne = new GameTile(1,0,TILE_SIZE,TILE_SIZE,Color.RED); //140,160 tiles
    GameTile brownTileOne = new GameTile(1,1,TILE_SIZE,TILE_SIZE,BROWN);
    GameTile whiteTileOne = new GameTile(1,2,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTile brownTileTwo = new GameTile(1,3,TILE_SIZE,TILE_SIZE,BROWN);
    GameTile redTileTwo = new GameTile(1,4,TILE_SIZE,TILE_SIZE,Color.RED);

    GameTile grayTileOne = new GameTile(2,0,TILE_SIZE,TILE_SIZE,Color.GRAY);
    GameTile grayTileTwo = new GameTile(2,1,TILE_SIZE,TILE_SIZE,Color.GRAY);
    GameTile whiteTileTwo = new GameTile(2,2,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTile grayTileThree = new GameTile(2,3,TILE_SIZE,TILE_SIZE,Color.GRAY);
    GameTile grayTileFour = new GameTile(2,4,TILE_SIZE,TILE_SIZE,Color.GRAY);

    GameTile whiteTileThree = new GameTile(3,0,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTile whiteTileFour = new GameTile(3,1,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTileDot whiteCenter = new GameTileDot(3,2,3.25,2.245,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTile whiteTileSix = new GameTile(3,3,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTile whiteTileSeven = new GameTile(3,4,TILE_SIZE,TILE_SIZE,Color.WHITE);

    GameTile grayTileFive = new GameTile(4,0,TILE_SIZE,TILE_SIZE,Color.GRAY);
    GameTile grayTileSix = new GameTile(4,1,TILE_SIZE,TILE_SIZE,Color.GRAY);
    GameTile whiteTileEight = new GameTile(4,2,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTile grayTileSeven = new GameTile(4,3,TILE_SIZE,TILE_SIZE,Color.GRAY);
    GameTile grayTileEight = new GameTile(4,4,TILE_SIZE,TILE_SIZE,Color.GRAY);

    GameTile brownTileThree = new GameTile(5,0,TILE_SIZE,TILE_SIZE,BROWN);
    GameTile redTileThree = new GameTile(5,1,TILE_SIZE,TILE_SIZE,Color.RED);
    GameTile whiteTileNine = new GameTile(5,2,TILE_SIZE,TILE_SIZE,Color.WHITE);
    GameTile redTileFour = new GameTile(5,3,TILE_SIZE,TILE_SIZE,Color.RED);
    GameTile brownTileFour = new GameTile(5,4,TILE_SIZE,TILE_SIZE,BROWN);

    Leader yellowLeader = new Leader(1,4,TILE_SIZE,TILE_SIZE,Color.YELLOW);
    Leader greenLeader = new Leader(5,0,TILE_SIZE,TILE_SIZE,Color.GREEN);

    Guard yellowGuardOne = new Guard(1,0,TILE_SIZE,TILE_SIZE,Color.YELLOW,Color.GREEN);
    Guard yellowGuardTwo = new Guard(1,1,TILE_SIZE,TILE_SIZE,Color.YELLOW,Color.GREEN);
    Guard yellowGuardThree = new Guard(1,2,TILE_SIZE,TILE_SIZE,Color.YELLOW,Color.GREEN);
    Guard yellowGuardFour = new Guard(1,3,TILE_SIZE,TILE_SIZE,Color.YELLOW,Color.GREEN);

    Guard greenGuardOne = new Guard(5,1,TILE_SIZE,TILE_SIZE,Color.GREEN,Color.YELLOW);
    Guard greenGuardTwo = new Guard(5,2,TILE_SIZE,TILE_SIZE,Color.GREEN,Color.YELLOW);
    Guard greenGuardThree = new Guard(5,3,TILE_SIZE,TILE_SIZE,Color.GREEN,Color.YELLOW);
    Guard greenGuardFour = new Guard(5,4,TILE_SIZE,TILE_SIZE,Color.GREEN,Color.YELLOW);

    private static final Color BROWN = new Color(51,0,0);

    /**
     * constructor
      */
    public GameBoard(){
        this.figureCollection = new Object[rowOfCollection][colOfCollection];

        //Yellow
        this.figureCollection[1][0] = yellowGuardOne;
        this.figureCollection[1][1] = yellowGuardTwo;
        this.figureCollection[1][2] = yellowGuardThree;
        this.figureCollection[1][3] = yellowGuardFour;
        this.figureCollection[1][4] = yellowLeader;

        //Green
        this.figureCollection[5][0] = greenLeader;
        this.figureCollection[5][1] = greenGuardOne;
        this.figureCollection[5][2] = greenGuardTwo;
        this.figureCollection[5][3] = greenGuardThree;
        this.figureCollection[5][4] = greenGuardFour;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(509, 609);
        this.setVisible(true);
        this.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardCoordinates(e.getY());
        int col = this.getBoardCoordinates(e.getX());

        if(this.selectedFigure !=null){
            Figures figure = (Figures) this.selectedFigure;
            figure.move(row,col);
            this.figureCollection[originalRow][originalCol] = null;
            //this.figureCollection[row][col] = this.selectedFigure; makes things go done goof
            this.selectedFigure = null;
            this.repaint();
        }

        if(this.hasBoardFigure(row,col)){
            if(this.selectedFigure == null){
                this.originalRow = row;
                this.originalCol = col;
            }
            this.selectedFigure = this.getBoardFigure(row,col);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Method which calls the methods where all the figures/lines/tiles are rendered.
     * @param g
     */
    @Override
    public void paint(Graphics g){

        tilesRender(g);

        figuresRender(g);

        g.setColor(Color.BLACK);
        linesInitializeAndRender(g);

    }

    /**
     * Method where all the lines are initialized and rendered.
     * @param g
     */
    public static void linesInitializeAndRender(Graphics g){
        Graphics2D lineDrawer = (Graphics2D) g;
        Line2D line0 = new Line2D.Float(8,100,8,2500);
        Line2D line1 = new Line2D.Float(100,100,100,2500);
        Line2D line2 = new Line2D.Float(200,100,200,2500);
        Line2D line3 = new Line2D.Float(300,100,300,2500);
        Line2D line4 = new Line2D.Float(400,100,400,2500);
        Line2D line5 = new Line2D.Float(500,100,500,2500);
        Line2D line6 = new Line2D.Float(0,100,1300,100);
        Line2D line7 = new Line2D.Float(0,200,1300,200);
        Line2D line8 = new Line2D.Float(0,300,1300,300);
        Line2D line9 = new Line2D.Float(0,400,1300,400);
        Line2D line10 = new Line2D.Float(0,500,1300,500);
        Line2D line11 = new Line2D.Float(0,600,1300,600);
        lineDrawer.draw(line0);
        lineDrawer.draw(line1);
        lineDrawer.draw(line2);
        lineDrawer.draw(line3);
        lineDrawer.draw(line4);
        lineDrawer.draw(line5);
        lineDrawer.draw(line6);
        lineDrawer.draw(line7);
        lineDrawer.draw(line8);
        lineDrawer.draw(line9);
        lineDrawer.draw(line10);
        lineDrawer.draw(line11);
    }

    /**
     * Method where all the tiles are rendered.
     * @param g
     */
    public void tilesRender(Graphics g){
        brownTileThree.render(g);
        redTileThree.render(g);
        whiteTileNine.render(g);
        redTileFour.render(g);
        brownTileFour.render(g);
        grayTileFive.render(g);
        grayTileSix.render(g);
        whiteTileEight.render(g);
        grayTileSeven.render(g);
        grayTileEight.render(g);
        whiteTileThree.render(g);
        whiteTileFour.render(g);
        whiteCenter.render(g);
        whiteTileSix.render(g);
        whiteTileSeven.render(g);
        grayTileOne.render(g);
        grayTileTwo.render(g);
        whiteTileTwo.render(g);
        grayTileThree.render(g);
        grayTileFour.render(g);
        redTileOne.render(g);
        brownTileOne.render(g);
        whiteTileOne.render(g);
        brownTileTwo.render(g);
        redTileTwo.render(g);
    }

    /**
     * Method where all the figures are rendered.
     * @param g
     */
    public void figuresRender(Graphics g){
        yellowLeader.render(g);
        greenLeader.render(g);

        yellowGuardOne.render(g);
        yellowGuardTwo.render(g);
        yellowGuardThree.render(g);
        yellowGuardFour.render(g);

        greenGuardOne.render(g);
        greenGuardTwo.render(g);
        greenGuardThree.render(g);
        greenGuardFour.render(g);
    }

    private int getBoardCoordinates(int coordinates){
        return  coordinates/TILE_SIZE;
    }
    private Object getBoardFigure(int row, int col){
        return this.figureCollection[row][col];
    }
    private boolean hasBoardFigure(int row,int col){
        return this.getBoardFigure(row,col) !=null;
    }
}