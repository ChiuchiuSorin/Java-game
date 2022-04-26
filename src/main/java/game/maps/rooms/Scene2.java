package game.maps.rooms;

import game.RefLinks;
import game.items.Permanent;
import game.items.Player;
import game.items.enemies.AxeOrc;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.*;
import static game.maps.types.RoomType.SCENE2;

public class Scene2 extends Room{
    private static volatile Scene2 roomInstance;
    private RefLinks refLink;
    private Permanent[] permanentObjs = new Permanent[1];
    private Enemy[] enemies = new Enemy[2];

    private Scene2(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(750,1550,0,460);
        width = 25;
        height = 15;
        transitions.put(SCENE1, new RoomTransition(SCENE1, 800,0));
        transitions.put(SCENE3, new RoomTransition(SCENE3, -800,0));

        permanentObjs[0] = new Permanent(refLink, 542, 290, 64, 64, 50, 60, ObjType.FIRE, true);
        enemies[0] = new AxeOrc(refLink, 400,300);
        enemies[1] = new AxeOrc(refLink, 400,150);

        int[][] thirdRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Scene2 loadWorld(RefLinks reflink) {
        if(roomInstance == null)
            synchronized (Scene2.class){
                if(roomInstance == null)
                    roomInstance = new Scene2(reflink);
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
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
        return room3[x][y];
    }

    @Override
    public void draw(Graphics g, Player player) {
        enemies[0].Draw(g);
        enemies[1].Draw(g);
        permanentObjs[0].Draw(g);
        player.Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if(isRoomCollision(player, map))
            player.Update();
        hitEnemy(player, enemies);
        hitPlayer(player, enemies);
        if(!enemyCollision(enemies[0], permanentObjs[0].getBounds()) && !mapCollision(enemies[0], map))
        {
            enemies[0].Update();
            enemies[1].Update();
        }
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return !mapCollision(player, map) && !playerCollision(player, permanentObjs[0].getBounds());
    }
}
