package game;

import game.input.KeyManager;
import game.input.MouseManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefLinks {
    private final Game game;

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }

}
