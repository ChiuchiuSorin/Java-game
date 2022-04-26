package game;

import game.graphics.Assets;
import game.input.KeyManager;
import game.input.MouseManager;
import game.model.SaveGame;
import game.repository.SaveGameRepository;
import game.states.EndGameState;
import game.states.MenuState;
import game.states.PlayState;
import game.states.State;
import game.tiles.Tile;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferStrategy;

@Component
@Getter
public class Game implements Runnable {
    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;

    private Graphics g;
    public State playState = null;
    public State menuState;
    public State endGameState;
    public static int level = 1;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private RefLinks refLink;

    private Tile tile;

    @Autowired
    public SaveGameRepository saveGameRepository;

    public Game() {
        wnd = new GameWindow("Agony", 800, 480);

        runState = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    public void initGame() {
        wnd.BuildGameWindow();
        wnd.getWndFrame().addKeyListener(keyManager);
        wnd.getWndFrame().addMouseListener(mouseManager);
        wnd.getWndFrame().addMouseMotionListener(mouseManager);
        wnd.getCanvas().addMouseListener(mouseManager);
        wnd.getCanvas().addMouseMotionListener(mouseManager);
        Assets.Init();

//        SaveGame saveGame = new SaveGame();
//        saveGame.setId(1L);
//        saveGame.setLevel(1);
//        saveGameRepository.save(saveGame);
//
//        SaveGame loadLevel = saveGameRepository.findById(1L).get();
//        loadLevel.setLevel(2);
//        saveGameRepository.save(loadLevel);

        refLink = new RefLinks(this);
        endGameState = new EndGameState(refLink);
        playState = new PlayState(refLink);
        menuState = new MenuState(refLink);

        State.setState(menuState);
    }

    public void saveGame() {
        SaveGame saveGame = new SaveGame();
        saveGame.setId(1L);
        saveGame.setLevel(level);
        saveGameRepository.save(saveGame);
    }

    public int loadGame() {
        SaveGame loadLevel = saveGameRepository.findById(1L).get();
        return loadLevel.getLevel();
    }

    public void run() {
        initGame();
        long oldTime = System.nanoTime();
        long curentTime;

        final int FPS = 60;
        final double timeFrame = 1000000000 / FPS;

        while (runState) {
            curentTime = System.nanoTime();
            if ((curentTime - oldTime) > timeFrame) {
                update();
                draw();
                oldTime = curentTime;
//                if(Player.playerLife <= 0){
//                    update();
//                    draw();
//                    stopGame();
//                }
            }
        }
    }

    public synchronized Thread startGame() {
        if (!runState) {
            runState = true;

            gameThread = new Thread(this);

            gameThread.start();
            return gameThread;
        } else {
            return null;
        }
    }

    public synchronized void stopGame() {
        if (runState) {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            } catch (InterruptedException ex) {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        } else {
            /// Thread-ul este oprit deja.
            return;
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getHeight() {
        return wnd.getWndHeight();
    }

    public int getWidth() {
        return wnd.getWndWidth();
    }

    private void update() {
        keyManager.update();
        if (State.getState() != null) {
            State.getState().update();
        }
    }

    private void draw() {
        bs = wnd.getCanvas().getBufferStrategy();
        if (bs == null) {
            try {
                wnd.getCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.getWndWidth(), wnd.getWndHeight());
        if (State.getState() != null) {
            State.getState().draw(g);
        }
        bs.show();
        g.dispose();
    }
}
