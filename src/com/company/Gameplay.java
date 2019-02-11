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
    private int totalBricks = 21;

    private Timer timer;
    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -2;
    private int ballYdir = -2;
    private MapGeneration map;
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 8;
        timer = new Timer(delay, this);
        timer.start();
        map=new MapGeneration(3,7);
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
        //drawing a map
        map.draw((Graphics2D)g);
        //the score
        g.setColor(Color.RED);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("score "+score, 550,30);


        //the paddle
        g.setColor(Color.WHITE);
        g.fillRect(playerX, 530,100,8);

        //the ball
        g.setColor(Color.WHITE);
        g.fillRoundRect(ballposX,ballposY, 20,20,20, 20);
        //gameover
        if(ballposY>570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over! Score: "+score, 190,300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press enter to restart! ", 230,350);
        }
        //victory
        if(totalBricks<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You have won! Score: "+score, 200,300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press enter to restart! ", 230,350);
        }
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX,530,100,8)))
            ballYdir = - ballYdir;
        if(play){
            ballposX += ballXdir;
            ballposY += ballYdir;
            A: for (int i=0; i<map.getMap().length; i++)
                for(int j=0; j<map.getMap()[0].length; j++){
                    if (map.getMap()[i][j]>0){
                        int brickX = j*map.getBrickWidth()+80;
                        int brickY = i*map.getBrickHight()+50;
                        int brickWidth = map.getBrickWidth();
                        int brickHeight = map.getBrickHight();
                        Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rectangle;
                        if (ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score+=5;
                            if(ballposX+19<= brickRect.x || ballposX+1>=brickRect.x+brickRect.width)
                                ballXdir = - ballXdir;
                            else ballYdir = - ballYdir;
                            break A;
                        }
                    }
                }
            if(ballposX < 0) // left
                ballXdir = - ballXdir; //top
            if(ballposY < 0)
                ballYdir = - ballYdir;
            if(ballposX > 670)
                ballXdir = - ballXdir; //right

        }
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -2;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGeneration(3,7);
                repaint();
            }
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
