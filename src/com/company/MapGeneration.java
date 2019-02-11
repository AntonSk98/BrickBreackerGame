package com.company;

import java.awt.*;

public class MapGeneration {
    private int map[][];
    private int brickWidth;
    private int brickHight;

    public int[][] getMap() {
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHight() {
        return brickHight;
    }

    MapGeneration(int row, int column){
        map = new int [row][column];
        for (int i=0; i<map.length; i++)
            for (int j=0; j<map[0].length; j++)
                 map[i][j]=1;
        brickHight=150/row;
        brickWidth=540/column;
    }
    public void draw(Graphics2D g){
        for (int i=0; i<map.length; i++)
            for (int j=0; j<map[0].length; j++){
                if(map[i][j]>0){
                    g.setColor(Color.WHITE);
                    g.fillRect(j*brickWidth+80, i*brickHight+60, brickWidth, brickHight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLUE);
                    g.drawRect(j*brickWidth+80, i*brickHight+60, brickWidth, brickHight);
                }
            }
    }
    public void setBrickValue(int value, int row, int column){
        map[row][column] = value;
    }

}
