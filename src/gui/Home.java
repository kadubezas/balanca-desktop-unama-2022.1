package gui;

import application.Main;
import utils.SerialConnection;

import javax.swing.*;

public class Home {
    private JPanel paneHome;
    private JButton jButtonIniciar;
    private JButton jButtonConfigurar;
    private JFormattedTextField jFormattedTextPeso;
    private JButton jButtonParar;

    Thread pesar;

    public Home(){initializeNodes();}

    private void initializeNodes(){
        jButtonConfigurar.addActionListener(actionEvent -> {
            Main.createConfiguration();
        });

        jButtonIniciar.addActionListener(actionEvent -> {
            pesar();
        });

        jButtonParar.addActionListener(actionEvent -> {
                pararPesagem();
        });
    }

    public JPanel getPaneHome() {
        return paneHome;
    }

    private void pesar(){
        pesar = new Thread(new Runnable() {
            @Override
            public void run() {
                while (SerialConnection.lerPeso() != null){
                    String dados = SerialConnection.lerPeso();
                    double valor = Double.parseDouble(dados == "" ? "0" : dados);
                    if(valor > 50){
                        jFormattedTextPeso.setText(String.valueOf(valor)+" KG");
                    }else {
                        jFormattedTextPeso.setText("Valor Baixo");
                    }
                }
            }
        });
        pesar.start();
    }

    private void pararPesagem(){
        if(pesar != null){
            pesar.stop();
            jFormattedTextPeso.setText("PARADO");
        }
    }

}
