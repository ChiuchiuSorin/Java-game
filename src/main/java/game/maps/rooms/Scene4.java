package game.maps.rooms;

import game.RefLinks;
import game.items.Player;
import game.items.enemies.AxeOrc;
import game.items.enemies.Enemy;
import game.items.enemies.FinalBoss;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.*;

public class Scene4 extends Room{
    private static volatile Scene4 roomInstance;
    private RefLinks refLink;
    private Enemy[] enemies = new Enemy[4];

    private Scene4(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(2350,3125,0,460);
        width = 25;
        height = 15;
        transitions.put(SCENE3, new RoomTransition(SCENE3, 800,0));
        enemies[0] = new Enemy(refLink, 400,300);
        enemies[1] = new AxeOrc(refLink, 200,350);
        enemies[2] = new AxeOrc(refLink, 200,50);
        enemies[3] = new FinalBoss(refLink, 650,240);


        int[][] thirdRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Scene4 loadWorld(RefLinks reflink) {
        if(roomInstance == null)
            synchronized (Scene4.class){
                if(roomInstance == null)
                    roomInstance = new Scene4(reflink);
            }
        return roomInstance;
    }
    private int thirdRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room3 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
        return room3[x][y];
    }

    @Override
    public void draw(Graphics g, Player player) {
        for(Enemy enemy : enemies){
            enemy.Draw(g);
        }
        player.Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if(isRoomCollision(player, map))
            player.Update();
        hitEnemy(player, enemies);
        hitPlayer(player, enemies);
        for(Enemy enemy : enemies) {
            if (!mapCollision(enemy, map))
                enemy.Update();
        }
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return !mapCollision(player, map);
    }
}
