package game.maps.rooms;

import game.RefLinks;
import game.items.Interactable;
import game.items.Permanent;
import game.items.Player;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.*;

public class Scene1 extends Room {

    private static volatile Scene1 roomInstance;
    private Interactable[] intObj = new Interactable[5];
    private RefLinks refLink;
    private Permanent[] permanentObjs = new Permanent[3];

    private Scene1(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(0,750,0,460);
        width = 25;
        height = 15;
        transitions.put(SCENE2, new RoomTransition(SCENE2, -800,0));

        permanentObjs[0] = new Permanent(refLink, 542, 290, 64, 64, 50, 60, ObjType.FIRE, true);
        intObj[0] = new Interactable(refLink, 50, 380, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[1] = new Interactable(refLink, 150, 380, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[2] = new Interactable(refLink, 250, 380, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[3] = new Interactable(refLink, 350, 380, 64, 64, 28, 60, false, ObjType.CHEST);
        intObj[4] = new Interactable(refLink, 400, 40, 64, 64, 28, 60, false, ObjType.POWERUP);
        permanentObjs[1] = new Permanent(refLink, 340, 40, 64, 64, 64, 64, ObjType.POWERDECORATION);
        permanentObjs[2] = new Permanent(refLink, 465, 40, 64, 64, 64, 64, ObjType.POWERDECORATION);
        int[][] thirdRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Scene1 loadWorld(RefLinks reflink) {
        if(roomInstance == null)
            synchronized (Scene1.class){
                if(roomInstance == null)
                    roomInstance = new Scene1(reflink);
            }
        return roomInstance;
    }

    private int thirdRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room3 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
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
        permanentObjs[1].Draw(g);
        permanentObjs[2].Draw(g);
        intObj[4].Draw(g);
        player.Draw(g);
        permanentObjs[0].Draw(g);
        for(int i = 0; i < 4; i++){
            intObj[i].Draw(g);
        }

    }

    @Override
    public void update(Player player, MapManager map) {
        if(!isRoomCollision(player, map))
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
        for(Permanent permObj : permanentObjs) {
            if (playerCollision(player, permObj.getBounds()))
                return true;
        }
        return false;
    }
}
