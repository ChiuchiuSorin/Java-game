package game.maps.rooms;

import game.RefLinks;
import game.collision.Collision;
import game.items.Character;
import game.items.enemies.Enemy;
import game.items.Player;
import game.items.enemies.MailedEnemy;
import game.maps.MapManager;
import game.maps.types.RoomTransition;

import java.awt.*;

import static game.maps.types.RoomType.*;

public class Room8 extends Room {
    private static volatile Room8 roomInstance;
    private RefLinks refLink;
    private Enemy[] enemies = new Enemy[2];
    private MailedEnemy[] mailedEnemy = new MailedEnemy[1];

    private Room8(RefLinks reflink) {
        this.refLink = reflink;
        boundaries = new RoomBoundaries(1550, 2400, 920, 1420);
        width = 25;
        height = 15;
        transitions.put(ROOM_SEVEN, new RoomTransition(ROOM_SEVEN, 800, 0));
        enemies[0] = new Enemy(refLink, 650, 70);
        mailedEnemy[0] = new MailedEnemy(refLink, 700, 200);
        enemies[1] = new Enemy(refLink, 650, 330);

        int[][] thirdRoom = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                thirdRoom[x][y] = thirdRoom(y, x);
            }
        }
        room = thirdRoom;
    }

    public static Room8 loadWorld(RefLinks reflink) {
        if (roomInstance == null)
            synchronized (Room8.class) {
                if (roomInstance == null)
                    roomInstance = new Room8(reflink);
            }
        return roomInstance;
    }

    private int thirdRoom(int x, int y) {
        ///Definire statica a matricei de coduri de date.

        final int[][] room3 = {
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 15},
                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 14},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 13},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
        return room3[x][y];
    }

    @Override
    public void draw(Graphics g, Player player) {
        for(Enemy enemy : enemies){
            enemy.Draw(g);
        }
        mailedEnemy[0].Draw(g);
        player.Draw(g);

    }

    @Override
    public void update(Player player, MapManager map) {
        if (!mapCollision(player, map))
            player.Update();
        hitEnemy(player, enemies);
        hitPlayer(player, enemies);
        for(Enemy enemy : enemies){
            if(!mapCollision(enemy, map))
                enemy.Update();
        }
        hitEnemy(player, mailedEnemy);
        hitPlayer(player, mailedEnemy);
        if(!mapCollision(mailedEnemy[0], map))
            mailedEnemy[0].Update();
    }

    @Override
    public boolean isRoomCollision(Player player, MapManager map) {
        return false;
    }

    @Override
    public boolean mapCollision(Character chr, MapManager m) {
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
                        if (Collision.AABB(chr.getBounds(), m.mapColl)) {
                            System.out.println("coliziune");
                            flag = true;
                        }
                        break;
                    case 14:
                        Rectangle aux = new Rectangle();
                        aux.y = m.mapColl.y - 30;
                        aux.height = m.mapColl.height + 60;
                        aux.x += m.mapColl.x - 40;
                        aux.width += m.mapColl.width + 60;
                        if (Collision.AABB(chr.getBounds(), m.mapColl)) {
                            System.out.println("coliziune");
                            flag = true;
                        }
                        if (refLink.getKeyManager().action && Collision.AABB(chr.getBounds(), aux) && Player.NOKeys > 0) {
                            m.rooms.get(ROOM_EIGHT).getRoom()[24][4] = 18;
                            m.rooms.get(ROOM_EIGHT).getRoom()[24][5] = 17;
                            m.rooms.get(ROOM_EIGHT).getRoom()[24][6] = 16;
                            System.out.println("gate open");
                            Player.NOKeys--;
                            System.out.println(Player.NOKeys);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return flag;
    }
}



