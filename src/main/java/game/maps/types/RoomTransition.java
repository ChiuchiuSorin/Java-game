package game.maps.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static game.maps.types.RoomType.ROOM_ONE;
import static game.maps.types.RoomType.ROOM_TWO;

@Getter
@Setter
@AllArgsConstructor
public class RoomTransition {
    private RoomType targetRoom;
    private float newPlayerXPos;
    private float newPlayerYPos;
}
