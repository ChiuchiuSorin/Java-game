package game.maps.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transition {
    private boolean isPlayerTransiting;
    private RoomType source;
    private RoomType target;
}
