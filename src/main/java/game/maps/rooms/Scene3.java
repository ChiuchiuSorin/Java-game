package game.maps.rooms;

import game.RefLinks;
import game.items.Interactable;
import game.items.Player;
import game.items.enemies.AxeOrc;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.SCENE2;
import static game.maps.types.RoomType.SCENE4;

public class Scene3 extends Room {
    private static volatile Scene3 roomInstance;
    private RefLinks refLink;
    private Enemy[] enemies = new Enemy[4];
    private Interactable[] intObj = new Interactable[1];

    private Scene3(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(1550, 2350, 0, 460);
        width = 25;
        height = 15;
        transitions.put(SCENE2, new RoomTransition(SCENE2, 800, 0));
        transitions.put(SCENE4, new RoomTransition(SCENE4, -800, 0));

        intObj[0] = new Interactable(refLink, 500, 380, 64, 64, 28, 60, false, ObjType.CHEST);
        enemies[0] = new Enemy(refLink, 300, 300);
        enemies[1] = new Enemy(refLink, 300, 60);
        enemies[2] = new AxeOrc(refLink, 600, 300);
        enemies[3] = new AxeOrc(refLink, 600, 60);

        int[][] thirdRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Scene3 loadWorld(RefLinks reflink) {
        if (roomInstance == null)
            synchronized (Scene3.class) {
                if (roomInstance == null)
                    roomInstance = new Scene3(reflink);
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
        for (Enemy enemy : enemies) {
            enemy.Draw(g);
        }
        intObj[0].Draw(g);
        player.Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if (isRoomCollision(player, map))
            player.Update();
        hitEnemy(player, enemies);
        hitPlayer(player, enemies);
        intObj[0].actionUsed(player);
        intObj[0].Update();
        for(Enemy enemy : enemies){
            if (!enemyCollision(enemy, intObj[0].getBounds()) && !mapCollision(enemy, map))
                enemy.Update();
        }
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return !mapCollision(player, map) && !playerCollision(player, intObj[0].getBounds());
    }
}
