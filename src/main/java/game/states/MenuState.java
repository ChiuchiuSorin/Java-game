package game.states;

import game.Game;
import game.RefLinks;
import game.graphics.Assets;
import game.maps.MapManager;
import game.ui.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;
import lombok.Getter;

import java.awt.*;

@Getter
public class MenuState extends State{

    private UIManager uiManager;
    public static boolean started = false;

    public MenuState(RefLinks refLink) {
        super(refLink);
        uiManager = new UIManager(refLink);
        refLink.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(304, 10, 192, 128, Assets.startButton, new ClickListener() {
            @Override
            public void onClick() {
                started = true;
                State.setState(refLink.getGame().playState);
                refLink.getMouseManager().setUiManager(null);
                refLink.getGame().getWnd().getWndFrame().requestFocus();
                System.out.println("clicked start");
            }
        }));
        uiManager.addObject(new UIImageButton(304, 150, 192, 128, Assets.saveButton, new ClickListener() {
            @Override
            public void onClick() {
                started = true;
                System.out.println(Game.level);
                refLink.getGame().saveGame();
//                State.setState(refLink.getGame().playState);
//                refLink.getMouseManager().setUiManager(null);
//                refLink.getGame().getWnd().getWndFrame().requestFocus();
                System.out.println("game saved: "+ Game.level);

            }
        }));
        uiManager.addObject(new UIImageButton(304, 290, 192, 128, Assets.loadButton, new ClickListener() {
            @Override
            public void onClick() {
                started = true;
                System.out.println(Game.level);
                Game.level = refLink.getGame().loadGame();
                State.setState(refLink.getGame().playState);
                refLink.getMouseManager().setUiManager(null);
                refLink.getGame().getWnd().getWndFrame().requestFocus();
                if(Game.level == 2) {
                    MapManager.loadLevel2 = true;
                }
                System.out.println("game loaded: " + Game.level);
            }
        }));
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void draw(Graphics g) {
        uiManager.draw(g);
    }
}
