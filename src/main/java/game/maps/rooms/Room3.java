package game.maps.rooms;

import game.RefLinks;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.items.Permanent;
import game.items.Player;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.*;

public class Room3 extends Room{
    private static volatile Room3 roomInstance;
    private RefLinks refLink;
    private Permanent[] permanentObjs = new Permanent[1];
    private Enemy[] enemies = new Enemy[1];

    private Room3(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(750,1600,420,960);
        width = 25;
        height = 15;
        transitions.put(ROOM_TWO, new RoomTransition(ROOM_TWO, 800,0));
        transitions.put(ROOM_FOUR, new RoomTransition(ROOM_FOUR, 0,460));
        permanentObjs[0] = new Permanent(refLink, 542, 290, 64, 64, 50, 60, ObjType.FIRE, true);
        enemies[0] = new Enemy(refLink, 400,300);

        int[][] thirdRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Room3 loadWorld(RefLinks reflink) {
        if(roomInstance == null)
            synchronized (Room3.class){
                if(roomInstance == null)
                    roomInstance = new Room3(reflink);
            }
        return roomInstance;
    }
    private int thirdRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room3 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
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
            enemies[0].Update();
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return !mapCollision(player, map) && !playerCollision(player, permanentObjs[0].getBounds());
    }
}
