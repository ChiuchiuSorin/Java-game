package game.tiles;

import game.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private static final int NO_TILES   = 60;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    public static Tile []floors = floorInit();
    public static Tile []grassFloors = grassFloorInit();
    public static Tile outWall = new OutterWall(12);
    public static Tile empty = new EmptySpot(11);
    public static Tile[] gate = gate();

    public static final int TILE_WIDTH  = 32;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 32;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;

    public static Tile[] gate(){
        Tile[] aux = new Tile[6];
        for(int i = 13; i < 19; i++) {
            aux[i - 13] = new Tile(Assets.gate[i-13], i);
        }
        return aux;
    }

    public static Tile[] floorInit(){
        Tile[] aux = new Tile[10];
        for(int i = 0; i < 10; i++) {
            aux[i] = new Tile(Assets.floor[i], i);
        }
        return aux;
    }

    public static Tile[] grassFloorInit(){
        Tile[] aux = new Tile[18];
        int i, j;
        for(i = 20, j = 0; i < 38; i++, j++) {
            aux[j] = new Tile(Assets.grassFloor[j], i);
        }
        return aux;
    }

    public Tile(BufferedImage image, int id){
        img  = image;
        this.id = id;

        tiles[id] = this;
    }
    public void Draw(Graphics g, int x, int y){
        g.drawImage(img, x, y, TILE_WIDTH,TILE_HEIGHT,null);
    }
    public int getID(){return id;}
}
