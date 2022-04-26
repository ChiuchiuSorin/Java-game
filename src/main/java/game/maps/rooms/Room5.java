package game.maps.rooms;

import game.RefLinks;
import game.items.*;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.ROOM_FOUR;

public class Room5 extends Room {
    private static volatile Room5 roomInstance;
    private RefLinks refLink;
    private Permanent[] permanentObjs = new Permanent[2];
    private Interactable[] intObj = new Interactable[5];

    private Room5(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(1550, 2400, 0, 460);
        width = 25;
        height = 15;
        transitions.put(ROOM_FOUR, new RoomTransition(ROOM_FOUR, 800, 0));
        intObj[0] = new Interactable(refLink, 100, 60, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[1] = new Interactable(refLink, 200, 60, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[2] = new Interactable(refLink, 100, 350, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[3] = new Interactable(refLink, 200, 350, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[4] = new Interactable(refLink, 700, 230, 64, 64, 28, 60, false, ObjType.POWERUP);
        permanentObjs[0] = new Permanent(refLink, 700, 166, 64, 64, 64, 64, ObjType.POWERDECORATION);
        permanentObjs[1] = new Permanent(refLink, 700, 294, 64, 64, 64, 64, ObjType.POWERDECORATION);

        int[][] thirdRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Room5 loadWorld(RefLinks reflink) {
        if (roomInstance == null)
            synchronized (Room5.class) {
                if (roomInstance == null)
                    roomInstance = new Room5(reflink);
            }
        return roomInstance;
    }

    private int thirdRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room3 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {18, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {17, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {16, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
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
        intObj[0].Draw(g);
        intObj[1].Draw(g);
        player.Draw(g);
        intObj[2].Draw(g);
        intObj[3].Draw(g);
        permanentObjs[0].Draw(g);
        intObj[4].Draw(g);
        permanentObjs[1].Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if (!isRoomCollision(player, map))
            player.Update();
        for(Interactable interactableObj : intObj){
            interactableObj.actionUsed(player);
            interactableObj.Update();
        }
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        if(mapCollision(player,map)){
            return true;
        }
        for(Interactable interactableObj : intObj){
            if(playerCollision(player, interactableObj.getBounds()))
                return true;
        }
        if(playerCollision(player, permanentObjs[0].getBounds()))
            return true;
        if(playerCollision(player, permanentObjs[1].getBounds()))
            return true;
        return false;
    }

    @Override
    public void hitEnemy(Player player1, Enemy[] enemy1) {

    }

    @Override
    public void hitPlayer(Player player1, Enemy[] enemy1) {

    }
}


