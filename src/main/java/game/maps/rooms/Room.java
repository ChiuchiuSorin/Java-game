package game.maps.rooms;

import game.collision.Collision;
import game.items.Character;
import game.items.enemies.Enemy;
import game.items.Player;
import game.maps.MapManager;
import game.maps.types.RoomTransition;
import game.maps.types.RoomType;
import game.tiles.Tile;
import lombok.Data;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Room {

    Map<RoomType, RoomTransition> transitions = new HashMap<>();
    protected RoomBoundaries boundaries;
    protected static int width;
    protected static int height;
    protected int[][] room;
    protected boolean inRoom = false;

    public Tile getRoomTiles(int x, int y) {
        if (tileExists(x, y))
            return Tile.empty;
        Tile t = Tile.tiles[room[x][y]];
        if (t == null)
            return Tile.outWall;
        return t;
    }
    public boolean mapCollision(Character chr, MapManager m){
        chr.verifyBounds();

        m.mapColl.x = 0;
        m.mapColl.y = 0;

        boolean flag = false;
        int[][] maplayer2 = MapManager.getCurrentRoom();
        for (int i = 0; i < MapManager.getHeight(); i++) {
            for (int j = 0; j < MapManager.getWidth(); j++) {
                m.mapColl.x = j * 31 - 8;
                m.mapColl.y = i * 30 - 20;
                switch (maplayer2[i][j]) {
                    case 12:
                        if(Collision.AABB(chr.getBounds(), m.mapColl)){
                            System.out.println("coliziune");
                            flag = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return flag;
    }

    public abstract void draw(Graphics g, Player player);
    public abstract void update(Player player, MapManager map);
    public abstract boolean isRoomCollision(Player player, MapManager map);


    public void hitEnemy(Player player1, Enemy[] enemy1) {
        for (Enemy enemy : enemy1) {
            if (Collision.AABB(player1.attackBounds, enemy.getBounds()) && !enemy.isDead && player1.anim.cycleOver) {
                player1.anim.cycleOver = false;
                //player1.attackBounds.x = -300;
                enemy.SetLife(enemy.GetLife()-Player.damage);
                if(enemy.GetLife() <= 0)
                    enemy.isDead = true;
                System.out.println("enemy hit");
            }
        }
    }
    public void hitPlayer(Player player1, Enemy[] enemy1) {
        for (Enemy enemy : enemy1) {
            if (Collision.AABB(player1.getBounds(), enemy.attackBounds) && !enemy.isDead) {
                enemy.attacking = true;
            } else {
                enemy.attacking = false;
            }
        }
    }

    public boolean playerCollision(Player player1, Rectangle obj){
        player1.verifyBounds();
        return Collision.AABB(player1.getBounds(), obj);
    }
    public boolean enemyCollision(Enemy enemy, Rectangle obj){
        enemy.verifyBounds();
        return Collision.AABB(enemy.getBounds(), obj);
    }

    public boolean tileExists(int x, int y) {
        return x < 0 || y < 0 || x >= 25 || y >= 15;
    }
    public int getCurrentTile(int x, int y) {
        return room[x][y];
    }
    public boolean isPlayerInRoom() {
        return Player.xRoom > boundaries.getXMin() && Player.xRoom < boundaries.getXMax() && Player.yRoom > boundaries.getYMin() && Player.yRoom < boundaries.getYMax();
    }

}