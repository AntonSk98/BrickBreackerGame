package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame jFrame = new JFrame();
        Gameplay gameplay = new Gameplay();
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setUndecorated(true);
        jFrame.setTitle("Breakout ball");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gameplay);
    }
}
