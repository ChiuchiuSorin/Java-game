package game.maps.factory;

import game.RefLinks;
import game.maps.rooms.Room;
import game.maps.types.RoomType;

public abstract class AbstractRoomFactory {
    public abstract Room getRoom(RoomType roomType, RefLinks refLinks);
}
