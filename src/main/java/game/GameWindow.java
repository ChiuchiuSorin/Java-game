package game;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private static JFrame wndFrame;
    private String wndTitle;
    private int wndWidth;
    private int wndHeight;

    private Canvas canvas;

    public GameWindow(String title, int width, int height) {
        wndTitle = title;
        wndWidth = width;
        wndHeight = height;
    }

    public void BuildGameWindow() {
        if (wndFrame != null)
            return;
        System.setProperty("java.awt.headless", "false");
        wndFrame = new JFrame(wndTitle);
        wndFrame.setSize(wndWidth, wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);
        wndFrame.setVisible(true);
        System.out.println(wndFrame.getAlignmentX());

        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));

        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        wndFrame.add(canvas);
        wndFrame.pack();
    }

    public int getWndWidth() {
        return wndWidth;
    }

    public int getWndHeight() {
        return wndHeight;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getWndFrame() {
        return wndFrame;
    }
}
