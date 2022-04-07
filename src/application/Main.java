package application;

import gui.Home;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame home = new JFrame("Balança Desktop");
        home.setContentPane(new Home().getPaneHome());
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(600,200);
        home.setResizable(false);
        home.setLocationRelativeTo(null);
        home.setVisible(true);
    }
}
