package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 3;

    private Timer timer;
    private int delay = 8;
    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this::actionPerformed);
        timer.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.BLUE);
        g.fillRect(0,0,695,560);
        //borders
        g.setColor(Color.WHITE);
        g.fillRect(0,0,5, 590);
        g.fillRect(0,0,695, 5);
        g.fillRect(690,0,5, 590);
        g.setColor(Color.RED);
        g.fillRect(5,560,685, 5);


        //the paddle
        g.setColor(Color.WHITE);
        g.fillRect(playerX, 530,100,8);

        //the ball
        g.setColor(Color.WHITE);
        g.fillRoundRect(350,470, 20,20,20, 20);
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(playerX>=585){
                playerX = 585;
            }else moveRight();
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<16){
                playerX = 10;
            }else moveLeft();
        }
    }

    private void moveLeft() {
        play = true;
        playerX -=20;
    }

    private void moveRight() {
        play = true;
        playerX +=20;

    }
}
