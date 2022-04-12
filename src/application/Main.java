package application;

import gui.Configuration;
import gui.Home;

import javax.swing.*;

public class Main {
    private static JFrame home = new JFrame("Balança Desktop");
    private static JFrame configuration = new JFrame("Configuração");
    public static void main(String[] args) {
        createHome();
    }

    public static void createConfiguration(){
        configuration.setContentPane(new Configuration().getjPanelConfiguration());
        configuration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configuration.setSize(600,300);
        configuration.setResizable(false);
        configuration.setLocationRelativeTo(null);
        configuration.setVisible(true);
    }

    public static void closeConfiguration(){
        configuration.dispose();
    }


    public static void createHome(){
        home.setContentPane(new Home().getPaneHome());
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(600,200);
        home.setResizable(false);
        home.setLocationRelativeTo(null);
        home.setVisible(true);
    }
}
