package gui;

import application.Main;
import model.dao.BalancaPesoDao;
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
                float ultimoValor = 0F;
                while (SerialConnection.lerPeso() != null){
                    String dados = SerialConnection.lerPeso();
                    float valor = Float.parseFloat(dados == "" ? "0" : dados);
                    if(valor > 100){
                        jFormattedTextPeso.setText(String.valueOf(valor)+" KG");
                        if(ultimoValor != valor){
                            BalancaPesoDao.insert(Float.valueOf(valor));
                            ultimoValor = valor;
                        }
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
