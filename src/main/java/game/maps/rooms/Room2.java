package game.maps.rooms;

import game.RefLinks;
import game.items.*;
import game.items.enemies.Enemy;
import game.items.typesEnums.ObjType;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.*;

public class Room2 extends Room {

    private static volatile Room2 roomInstance;
    private RefLinks refLink;
    private Permanent[] permanentObjs = new Permanent[2];
    private Enemy[] enemies = new Enemy[2];
    private Interactable[] intObj = new Interactable[1];

    private Room2(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(0, 750, 420, 880);
        width = 25;
        height = 15;
        transitions.put(ROOM_ONE, new RoomTransition(ROOM_ONE, 0, 460));
        transitions.put(ROOM_THREE, new RoomTransition(ROOM_THREE, -800, 0));
        transitions.put(ROOM_SIX, new RoomTransition(ROOM_SIX, 0, -460));

        enemies[0] = new Enemy(refLink, 628, 320);
        enemies[1] = new Enemy(refLink, 680, 320);

        intObj[0] = new Interactable(refLink, 100, 80, 64, 64, 28, 60, false, ObjType.CHEST);
        permanentObjs[0] = new Permanent(refLink, 650, 380, 96, 64, 64, 90, ObjType.TABLEANDCHAIRS);
        permanentObjs[1] = new Permanent(refLink, 300, 250, 64, 64, 50, 60, ObjType.FIRE, true);

        int[][] secondRoom = new int[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                secondRoom[x][y] = secondRoom(y, x);
            }
        }
        room = secondRoom;
    }

    public static Room2 loadWorld(RefLinks reflink) {
        if (roomInstance == null)
            synchronized (Room2.class) {
                if (roomInstance == null)
                    roomInstance = new Room2(reflink);
            }
        return roomInstance;
    }

    private int secondRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room2 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
        return room2[x][y];
    }

    @Override
    public void draw(Graphics g, Player player) {
        permanentObjs[1].Draw(g);
        enemies[0].Draw(g);
        enemies[1].Draw(g);
        intObj[0].Draw(g);
        player.Draw(g);
        permanentObjs[0].Draw(g);
    }

    @Override
    public void update(Player player, MapManager map) {
        if (isRoomCollision(player, map))
            player.Update();
        intObj[0].actionUsed(player);
        intObj[0].Update();
        hitEnemy(player, enemies);
        hitPlayer(player, enemies);
        for(Enemy enemy:enemies) {
            if (!isEnemyRoomCollision(map, enemy)) {
                enemy.Update();
            }
        }
    }

    private boolean isEnemyRoomCollision(MapManager map, Enemy enemy) {
            if (mapCollision(enemy, map))
                return true;
            for (Permanent obj : permanentObjs) {
                if (enemyCollision(enemy, obj.getBounds()))
                    return true;
            }
            if(enemyCollision(enemy,intObj[0].getBounds()))
                return true;
        return false;
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return !mapCollision(player, map) && !playerCollision(player, permanentObjs[0].getBounds())
                && !playerCollision(player, permanentObjs[1].getBounds()) && !playerCollision(player, intObj[0].getBounds());
    }

//    @Override
//    public void hitEnemy(Player player1, Enemy[] enemy1) {
//        for (Enemy enemy : enemy1) {
//            if (Collision.AABB(player1.attackBounds, enemy.getBounds()) && !enemy.isDead) {
//                enemy.anim.cycleOver = false;
//                enemy.isDead = true;
//                System.out.println("enemy hit");
//            }
//        }
//    }
//
//    @Override
//    public void hitPlayer(Player player1, Enemy[] enemy1) {
//        for (Enemy enemy : enemy1) {
//            if (Collision.AABB(player1.getBounds(), enemy.attackBounds) && !enemy.isDead) {
//                enemy.attacking = true;
//            } else {
//                enemy.attacking = false;
//            }
//        }
//    }
}


