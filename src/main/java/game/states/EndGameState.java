package game.states;

import game.RefLinks;
import game.graphics.Assets;
import game.items.Player;
import game.ui.UIManager;

import java.awt.*;

public class EndGameState extends State{
    public EndGameState(RefLinks refLink) {
        super(refLink);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        if(Player.playerLife <= 0)
            g.drawImage(Assets.endGameDeadPhoto,0,0,null);
        else
            g.drawImage(Assets.endGamePhoto,0,0,null);
    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }
}
