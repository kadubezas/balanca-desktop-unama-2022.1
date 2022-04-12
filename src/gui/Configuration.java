package gui;

import application.Main;
import model.entities.PortParameters;
import model.services.PropertiesServices;
import utils.SerialConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Configuration {
    private JPanel jPanelConfiguration;
    private JComboBox jComboBoxPorta;
    private JTextField jTextFieldBaundRate;
    private JTextField jTextFieldDataBits;
    private JTextField jTextFieldStopBits;
    private JTextField jTextFieldParity;
    private JButton btnLimpar;
    private JButton btnSalvar;

    PortParameters parameters = PropertiesServices.loadProperties();

    public Configuration(){
        initiateNodes();

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                instantiatePortParameters();
                PropertiesServices.writeProperties(parameters);
                Main.closeConfiguration();
            }
        });
    }

    public JPanel getjPanelConfiguration() {
        return jPanelConfiguration;
    }

    private void initiateNodes(){
        populateComboBoxPortName();
        updateitens();
    }

    private void populateComboBoxPortName(){
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBoxPorta.getModel();
        List<String> list = SerialConnection.portsCom();
        for (String port : list){
            comboBoxModel.addElement(port);
        }
        comboBoxModel.setSelectedItem(parameters.getPortName());
    }

    private void updateitens(){
        jTextFieldBaundRate.setText(parameters.getBaundRate().toString());
        jTextFieldDataBits.setText(parameters.getDataBits().toString());
        jTextFieldStopBits.setText(parameters.getStopBits().toString());
        jTextFieldParity.setText(parameters.getParity().toString());
    }

    private void instantiatePortParameters(){
        parameters.setPortName(jComboBoxPorta.getSelectedItem().toString());
        parameters.setBaundRate(Integer.parseInt(jTextFieldBaundRate.getText()));
        parameters.setDataBits(Integer.parseInt(jTextFieldDataBits.getText()));
        parameters.setStopBits(Integer.parseInt(jTextFieldStopBits.getText()));
        parameters.setParity(Integer.parseInt(jTextFieldParity.getText()));
    }
}
