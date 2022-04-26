package game.maps.rooms;

import game.items.Player;
import game.items.enemies.Enemy;
import game.maps.MapManager;

import java.awt.*;

public class GrassFloor extends Room {
    private static volatile GrassFloor roomInstance;

    private GrassFloor() {
        width = 25;
        height = 15;
        room = generateGrassFloor();
    }

    public static GrassFloor loadWorld() {
        if(roomInstance == null)
            synchronized (GrassFloor.class){
                if(roomInstance == null)
                    roomInstance = new GrassFloor();
            }
        return roomInstance;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    private int[][] generateGrassFloor() {
        int[][] floorMap = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                floorMap[x][y] = getRandomNumber(20, 38);
            }
        }
        return floorMap;
    }

    @Override
    public void draw(Graphics g, Player player) {
    }
    @Override
    public void update(Player player, MapManager map) {

    }
    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return false;
    }
    @Override
    public void hitEnemy(Player player1, Enemy[] enemy1) {

    }
    @Override
    public void hitPlayer(Player player1, Enemy[] enemy1) {

    }
}
