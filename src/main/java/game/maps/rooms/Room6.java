package game.maps.rooms;

import game.RefLinks;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.items.Permanent;
import game.items.Player;
import game.items.traps.Arrow;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.ROOM_SEVEN;
import static game.maps.types.RoomType.ROOM_TWO;

public class Room6 extends Room {

    private static volatile Room6 roomInstance;
    private RefLinks refLink;
    private Enemy enemies[] = new Enemy[2];
    private Permanent permanentObjs[] = new Permanent[2];
    private Arrow arrow;

    private Room6(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(0, 750, 880, 1400);
        width = 25;
        height = 15;
        transitions.put(ROOM_TWO, new RoomTransition(ROOM_TWO, 0, 460));
        transitions.put(ROOM_SEVEN, new RoomTransition(ROOM_SEVEN, -800, 0));
        permanentObjs[0] = new Permanent(refLink, 700, 444, 32, 32, 32, 32, ObjType.ARROWTRAP);
        permanentObjs[1] = new Permanent(refLink, 353, 370, 64, 64, 50, 60, ObjType.FIRE, true);
        enemies[0] = new Enemy(refLink, 500, 300);
        enemies[1] = new Enemy(refLink, 200, 300);

        arrow = new Arrow(refLink, 700, 390, 32, 32);
        int[][] thirdRoom = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Room6 loadWorld(RefLinks reflink) {
        if (roomInstance == null)
            synchronized (Room6.class) {
                if (roomInstance == null)
                    roomInstance = new Room6(reflink);
            }
        return roomInstance;
    }

    private int thirdRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room3 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
        return room3[x][y];
    }

    @Override
    public void draw(Graphics g, Player player) {
        permanentObjs[0].Draw(g);
        enemies[0].Draw(g);
        enemies[1].Draw(g);
        player.Draw(g);
        arrow.Draw(g);
        permanentObjs[1].Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if (isRoomCollision(player, map))
            player.Update();
        hitEnemy(player, enemies);
        hitPlayer(player, enemies);
        for (Enemy enemy : enemies){
            if(!mapCollision(enemy, map))
                enemy.Update();
        }
        arrow.arrowCollision(player, map);
        arrow.Update();
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return !mapCollision(player, map) && !playerCollision(player, permanentObjs[1].getBounds());
    }


}



