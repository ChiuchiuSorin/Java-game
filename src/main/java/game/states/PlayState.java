package game.states;

import game.Game;
import game.RefLinks;
import game.graphics.Assets;
import game.items.Interactable;
import game.items.Number;
import game.items.Permanent;
import game.items.Player;
import game.maps.MapManager;
import game.maps.rooms.Room;
import game.maps.types.RoomTransition;
import game.ui.UIManager;

import java.awt.*;

import static game.items.typesEnums.ObjType.*;

public class PlayState extends State {
    private Player player;
    private MapManager map;
    private Interactable[] miscellaneous = new Interactable[2];
    private Number keyNumber;
    private Number lifeNumber;
    private Number scoreNumber;
    private Permanent life;

    public PlayState(RefLinks refLink) {
        super(refLink);
        map = new MapManager(refLink, Game.level);
        map = new MapManager(refLink, Game.level);

        player = new Player(refLink, 100, 100);
        miscellaneous[0] = new Interactable(refLink,760, -5,40,40,0,0, false, KEY);
        miscellaneous[1] = new Interactable(refLink,690, -10,36,36,0,0, false, CHEST);
        miscellaneous[1].setImage(Assets.chest[1]);
        keyNumber = new Number(refLink, 740, -3, 40, 40, KEY);
        lifeNumber = new Number(refLink, 40, 444, 40, 40, LIFE);
        scoreNumber = new Number(refLink, 660, -5, 40, 40, SCORE);
        life = new Permanent(refLink, 40,423,60,60,0,0,LIFE);
    }

    @Override
    public void update() {
        playerUpdate();
        keyNumber.Update();
        lifeNumber.Update();
        scoreNumber.Update();
        map.update();
        changeLevel();
        toMenu();
        endGame();
    }

    private void endGame() {
        if(Game.level == 2 && Player.xRoom > 3150 || Player.playerLife <= 0){
            State.setState(refLink.getGame().endGameState);
        }
    }

    private void toMenu() {
        if(refLink.getKeyManager().esc){
            State.setState(refLink.getGame().menuState);
            refLink.getGame().getWnd().getWndFrame().requestFocus();
            refLink.getMouseManager().setUiManager(refLink.getGame().menuState.getUiManager());
        }
    }

    private void changeLevel() {
        if(Game.level == 1 && Player.xRoom > 2400){
            Game.level = 2;
            refLink.getGame().saveGame();
            player.resetPos();
        }
    }

    private void playerUpdate() {
        changePlayerLocation();
        map.rooms.get(map.currentRoomType).update(player, map);
    }

    private void changePlayerLocation() {
        if (map.roomTransition.isPlayerTransiting()) {
            Room sourceRoom = map.rooms.get(map.roomTransition.getSource());
            RoomTransition targetRoomTransition = sourceRoom.getTransitions().get(map.roomTransition.getTarget());
            float x = targetRoomTransition.getNewPlayerXPos();
            float y = targetRoomTransition.getNewPlayerYPos();
            player.SetY(player.getY() + y);
            player.SetX(player.getX() + x);
            map.roomTransition.setPlayerTransiting(false);
        }
    }


    @Override
    public void draw(Graphics g) {
        map.draw(g);
        mapDraw(g);
        scoreNumber.Draw(g);
        miscellaneous[0].Draw(g);
        miscellaneous[1].Draw(g);
        keyNumber.Draw(g);
        life.Draw(g);
        lifeNumber.Draw(g);
    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    private void mapDraw(Graphics g) {
        map.rooms.get(map.currentRoomType).draw(g, player);
    }
}
