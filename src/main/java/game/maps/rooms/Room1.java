package game.maps.rooms;

import game.RefLinks;
import game.items.*;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.ROOM_TWO;

public class Room1 extends Room {
    private static volatile Room1 roomInstance;
    private Permanent[] permanentObjs = new Permanent[3];
    private RefLinks refLink;
    private Interactable[] intObj = new Interactable[1];

    private Room1(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(0, 800, 0, 420);
        width = 25;
        height = 15;
        transitions.put(ROOM_TWO, new RoomTransition(ROOM_TWO, 0, -460));

        permanentObjs[0] = new Permanent(this.refLink, 250, 100, 64, 96, 54, 60, ObjType.BED);
        intObj[0] = new Interactable(refLink, 400, 300, 64, 64, 28, 60, false, ObjType.CHEST);

        int[][] secondRoom = new int[width][height];
        setInRoom(true);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                secondRoom[x][y] = firstRoom(y, x);
            }
        }
        room = secondRoom;
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return !playerCollision(player, permanentObjs[0].getBounds()) && !mapCollision(player, map) && !playerCollision(player, intObj[0].getBounds());
    }

    public static Room1 loadWorld(RefLinks reflink) {
        if (roomInstance == null)
            synchronized (Room1.class) {
                if (roomInstance == null)
                    roomInstance = new Room1(reflink);
            }
        return roomInstance;
    }

    private int firstRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room1 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
        return room1[x][y];
    }

    @Override
    public void draw(Graphics g, Player player) {
        permanentObjs[0].Draw(g);
        intObj[0].Draw(g);
        player.Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if (isRoomCollision(player, map))
            player.Update();
        intObj[0].actionUsed(player);
        intObj[0].Update();
    }
}
