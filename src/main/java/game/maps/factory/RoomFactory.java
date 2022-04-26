package game.maps.factory;

import game.RefLinks;
import game.maps.rooms.*;
import game.maps.types.RoomType;

public class RoomFactory extends AbstractRoomFactory {

    @Override
    public Room getRoom(RoomType roomType, RefLinks reflink) {
        Room map = null;

        switch (roomType){
            case FLOOR -> {
                map = Floor.loadWorld();
                break;
            }
            case GRASS_FLOOR -> {
                map = GrassFloor.loadWorld();
                break;
            }
            case ROOM_ONE -> {
                map = Room1.loadWorld(reflink);
                break;
            }
            case ROOM_TWO -> {
                map = Room2.loadWorld(reflink);
                break;
            }
            case ROOM_THREE -> {
                map = Room3.loadWorld(reflink);
                break;
            }
            case ROOM_FOUR -> {
                map = Room4.loadWorld(reflink);
                break;
            }
            case ROOM_FIVE -> {
                map = Room5.loadWorld(reflink);
                break;
            }
            case ROOM_SIX -> {
                map = Room6.loadWorld(reflink);
                break;
            }
            case ROOM_SEVEN -> {
                map = Room7.loadWorld(reflink);
                break;
            }
            case ROOM_EIGHT -> {
                map = Room8.loadWorld(reflink);
                break;
            }
            case SCENE1 -> {
                map = Scene1.loadWorld(reflink);
                break;
            }
            case SCENE2 -> {
                map = Scene2.loadWorld(reflink);
                break;
            }
            case SCENE3 -> {
                map = Scene3.loadWorld(reflink);
                break;
            }
            case SCENE4 -> {
                map = Scene4.loadWorld(reflink);
                break;
            }
            default -> {
                break;
            }
        }
        return map;
    }
}
