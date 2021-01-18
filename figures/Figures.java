package figures;

public abstract class Figures {
    protected double row;
    protected double col;
    protected int widthOfTile;
    protected int heightOfTile;

    public void move(int row,int col){
        this.row = row;
        this.col = col;
    }
}
