package game.maps.rooms;

import game.items.enemies.Enemy;
import game.items.Player;
import game.maps.MapManager;

import java.awt.*;

public class Floor extends Room {
    private static volatile Floor roomInstance;

    private Floor() {
        width = 25;
        height = 15;
        room = generateFloor();
    }

    public static Floor loadWorld() {
        if(roomInstance == null)
            synchronized (Floor.class){
                if(roomInstance == null) {
                    roomInstance = new Floor();
                }
            }
        return roomInstance;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    private int[][] generateFloor() {
        int[][] floorMap = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                floorMap[x][y] = getRandomNumber(0, 10);
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

