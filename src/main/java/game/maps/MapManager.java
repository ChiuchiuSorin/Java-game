package game.maps;

import game.Game;
import game.RefLinks;
import game.exceptions.InvalidTileID;
import game.items.Player;
import game.maps.factory.AbstractRoomFactory;
import game.maps.factory.RoomFactory;
import game.maps.rooms.Room;
import game.maps.types.RoomType;
import game.maps.types.Transition;
import game.tiles.Tile;
import lombok.Getter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static game.maps.types.RoomType.*;

@Getter
public class MapManager {
    private RefLinks refLink;
    private static int width = 25;
    private static int height = 15;
    private AbstractRoomFactory roomFactory = new RoomFactory();
    public static Map<RoomType, Room> rooms = new HashMap<>();
    public Transition roomTransition = new Transition(false, null, null);
    public Rectangle mapColl;
    public static RoomType currentRoomType;
    public static boolean loadLevel1 = false;
    public static boolean loadLevel2 = false;

    public MapManager(RefLinks refLink, int level) {
        this.refLink = refLink;
        mapColl = new Rectangle(0, 0, 24, 24);

        rooms.put(FLOOR, roomFactory.getRoom(FLOOR, this.refLink));
        rooms.put(ROOM_ONE, roomFactory.getRoom(ROOM_ONE, this.refLink));
        rooms.put(ROOM_TWO, roomFactory.getRoom(ROOM_TWO, this.refLink));
        rooms.put(ROOM_THREE, roomFactory.getRoom(ROOM_THREE, this.refLink));
        rooms.put(ROOM_FOUR, roomFactory.getRoom(ROOM_FOUR, this.refLink));
        rooms.put(ROOM_FIVE, roomFactory.getRoom(ROOM_FIVE, this.refLink));
        rooms.put(ROOM_SIX, roomFactory.getRoom(ROOM_SIX, this.refLink));
        rooms.put(ROOM_SEVEN, roomFactory.getRoom(ROOM_SEVEN, this.refLink));
        rooms.put(ROOM_EIGHT, roomFactory.getRoom(ROOM_EIGHT, this.refLink));

        rooms.put(GRASS_FLOOR, roomFactory.getRoom(GRASS_FLOOR, this.refLink));
        rooms.put(SCENE1, roomFactory.getRoom(SCENE1, this.refLink));
        rooms.put(SCENE2, roomFactory.getRoom(SCENE2, this.refLink));
        rooms.put(SCENE3, roomFactory.getRoom(SCENE3, this.refLink));
        rooms.put(SCENE4, roomFactory.getRoom(SCENE4, this.refLink));
        if (Game.level == 1)
            currentRoomType = ROOM_ONE;
        else if (Game.level == 2)
            currentRoomType = SCENE1;
    }

    public MapManager(RefLinks refLink) {
        this.refLink = refLink;
        mapColl = new Rectangle(0, 0, 24, 24);
        rooms.put(FLOOR, roomFactory.getRoom(FLOOR, this.refLink));
        rooms.put(ROOM_ONE, roomFactory.getRoom(ROOM_ONE, this.refLink));
        rooms.put(ROOM_TWO, roomFactory.getRoom(ROOM_TWO, this.refLink));
        rooms.put(ROOM_THREE, roomFactory.getRoom(ROOM_THREE, this.refLink));
        rooms.put(ROOM_FOUR, roomFactory.getRoom(ROOM_FOUR, this.refLink));
        rooms.put(ROOM_FIVE, roomFactory.getRoom(ROOM_FIVE, this.refLink));
        rooms.put(ROOM_SIX, roomFactory.getRoom(ROOM_SIX, this.refLink));
        rooms.put(ROOM_SEVEN, roomFactory.getRoom(ROOM_SEVEN, this.refLink));
        rooms.put(ROOM_EIGHT, roomFactory.getRoom(ROOM_EIGHT, this.refLink));
        currentRoomType = ROOM_ONE;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int[][] getCurrentRoom() {
        int aux;
        int[][] matrix = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try{
                    aux = rooms.get(currentRoomType).getCurrentTile(x, y);
                    matrix[y][x] = rooms.get(currentRoomType).getCurrentTile(x, y);
                    if(aux < 0 || aux > 40){
                        throw new InvalidTileID("invalid tile id");
                    }
                }
                catch (InvalidTileID ex){
                    System.out.println(ex.getError());
                }
            }
        }
        return matrix;
    }

    public void update() {
        if (Player.xRoom > 2400 && Game.level == 1 || loadLevel2) {
            currentRoomType = SCENE1;
            loadLevel2 = false;
        }
    }

    public void draw(Graphics g) {
        for (int y = 0; y < refLink.getGame().getHeight() / Tile.TILE_HEIGHT; y++) {
            for (int x = 0; x < refLink.getGame().getWidth() / Tile.TILE_WIDTH; x++) {
                if (Game.level == 1)
                    rooms.get(FLOOR).getRoomTiles(x, y).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
                if (Game.level == 2)
                    rooms.get(GRASS_FLOOR).getRoomTiles(x, y).Draw(g, (int) x * Tile.TILE_HEIGHT, (int) y * Tile.TILE_WIDTH);
                selectRoom();
                drawCurrentRoomTile(g, x, y);
            }
        }
        System.out.print("");
    }

    private void selectRoom() {
        if (!isPlayerInRoom(currentRoomType)) {
            rooms.get(currentRoomType).getTransitions().values().forEach(transition -> {
                RoomType target = transition.getTargetRoom();
                if (rooms.get(transition.getTargetRoom()).isPlayerInRoom()) {
                    transitionRoom(target);
                }
            });
        }
    }

    private static boolean isPlayerInRoom(RoomType currentRoomType) {
        return rooms.get(currentRoomType).isPlayerInRoom();
    }

    private void drawCurrentRoomTile(Graphics g, int x, int y) {
        rooms.get(currentRoomType).getRoomTiles(x, y).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);
    }

    private void transitionRoom(RoomType roomTypeTarget) {
        rooms.get(currentRoomType).setInRoom(false);
        rooms.get(roomTypeTarget).setInRoom(true);
        roomTransition.setSource(currentRoomType);
        roomTransition.setTarget(roomTypeTarget);
        roomTransition.setPlayerTransiting(true);
        currentRoomType = roomTypeTarget;
    }
//    private static boolean isPlayerInRoom(int xMin, int xMax, int yMin, int yMax) {
//        return Player.xRoom > xMin && Player.xRoom < xMax && Player.yRoom > yMin && Player.yRoom < yMax;
//    }
//
//    private void loadWorld() {
//        width = 25;
//        height = 15;
//
//        int[][] firstRoom = new int[width][height];
//        int[][] secondRoom = new int[width][height];
//        int[][] thirdRoom = new int[width][height];
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                firstRoom[x][y] = firstRoom(y, x);
//                secondRoom[x][y] = secondRoom(y, x);
//                thirdRoom[x][y] = thirdRoom(y, x);
//            }
//        }
//
//        rooms.put(FLOOR, new Room(generateFloor()));
//        rooms.put(ROOM_ONE, new Room(firstRoom));
//        rooms.put(ROOM_TWO, new Room(secondRoom));
//        rooms.put(ROOM_THREE, new Room(thirdRoom));
//        rooms.get(ROOM_ONE).setInRoom(true);
//
//        System.out.println("citire completa");
//    }
//
//    public int getRandomNumber(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }
//
//    private int[][] generateFloor() {
//        int[][] floorMap = new int[width][height];
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                floorMap[x][y] = getRandomNumber(0, 10);
//            }
//        }
//        return floorMap;
//    }
//
//    private int firstRoom(int x, int y) {
//        ///Definire statica a matricei de coduri de date.
//
//        final int[][] room1 = {
//                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
//        return room1[x][y];
//    }
//
//    private int secondRoom(int x, int y) {
//        ///Definire statica a matricei de coduri de date.
//
//        final int[][] room2 = {
//                {12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
//        return room2[x][y];
//    }
//
//    private int thirdRoom(int x, int y) {
//        ///Definire statica a matricei de coduri de date.
//
//        final int[][] room3 = {
//                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12},
//                {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}};
//        return room3[x][y];
//    }
}

