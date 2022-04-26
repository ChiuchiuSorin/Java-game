package game.maps.rooms;

import game.RefLinks;
import game.items.*;
import game.items.enemies.Enemy;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.items.typesEnums.ObjType.*;
import static game.maps.types.RoomType.*;

public class Room7 extends Room {
    private static volatile Room7 roomInstance;
    private RefLinks refLink;
    private Permanent[] permanentObjs = new Permanent[8];
    private Enemy[] enemies = new Enemy[2];
    private Interactable[] intObj = new Interactable[1];

    private Room7(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(750, 1550, 920,1420);
        width = 25;
        height = 15;
        transitions.put(ROOM_SIX, new RoomTransition(ROOM_SIX, 800, 0));
        transitions.put(ROOM_EIGHT, new RoomTransition(ROOM_EIGHT, -800, 0));
        enemies[0] = new Enemy(refLink, 320, 320);
        enemies[1] = new Enemy(refLink, 680, 320);
        intObj[0] = new Interactable(refLink, 580, 400, 32, 32, 28, 32, true, KEY);
        permanentObjs[0] = new Permanent(refLink, 600, 30, 64, 64, 50, 60, CAULDRON, true);
        permanentObjs[1] = new Permanent(refLink, 311, 25, 96, 64, 32, 90, TABLE);
        permanentObjs[2] = new Permanent(refLink, 40, 20, 32, 80, 60, 32, SKELETON);
        permanentObjs[3] = new Permanent(refLink, 100, 40, 32, 32, 20, 32, BONEPILE1);
        permanentObjs[4] = new Permanent(refLink, 100, 80, 32, 32, 20, 32, BONEPILE2);
        permanentObjs[5] = new Permanent(refLink, 650, 380, 64, 64, 64, 64, BLOODFOUNTAIN);
        permanentObjs[6] = new Permanent(refLink, 311, 75, 64, 64, 64, 90, BLOOD1);
        permanentObjs[7] = new Permanent(refLink, 311, 40, 90, 50, 64, 90, BLOOD2);

        int[][] thirdRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Room7 loadWorld(RefLinks reflink) {
        if (roomInstance == null)
            synchronized (Room7.class) {
                if (roomInstance == null)
                    roomInstance = new Room7(reflink);
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

        for(Permanent perm : permanentObjs){
            perm.Draw(g);
        }
        intObj[0].Draw(g);
        enemies[0].Draw(g);
        enemies[1].Draw(g);
        player.Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if (!isRoomCollision(player, map))
            player.Update();
        hitEnemy(player, enemies);
        hitPlayer(player, enemies);
        intObj[0].actionUsed(player);
        intObj[0].Update();
        for(Enemy enemy : enemies){
            if(!isEnemyCollision(enemy, map))
                enemy.Update();
        }
    }

    public boolean isEnemyCollision(Enemy enemy, MapManager map) {
        for(int i = 0; i<6; i++){
            if(enemyCollision(enemy, permanentObjs[i].getBounds())){
                return true;
            }
        }
        if(mapCollision(enemy,map))
            return true;
        if(enemyCollision(enemy, intObj[0].getBounds()))
            return true;

        return false;
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        for(int i = 0; i<6; i++){
            if(playerCollision(player, permanentObjs[i].getBounds())){
                return true;
            }
        }
        if(mapCollision(player,map))
            return true;
        if(playerCollision(player, intObj[0].getBounds()))
            return true;

        return false;
    }
}



