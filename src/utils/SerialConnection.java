package utils;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPortTimeoutException;
import model.entities.PortParameters;
import model.services.PropertiesServices;

import java.util.ArrayList;
import java.util.List;

public class SerialConnection {

    private static SerialPort serialPort;

    private static PortParameters parameters = PropertiesServices.loadProperties();

    public SerialConnection() {

    }

    public static List<String> portsCom(){
        List<String> portas = new ArrayList<>();
        for(String p: SerialPortList.getPortNames()){
            portas.add(p);
        }
        return portas;
    }

    public static String lerPeso(){
        String peso = "0";
        try{
            serialPort = new SerialPort(parameters.getPortName());
            serialPort.openPort();
            serialPort.setParams(parameters.getBaundRate(), parameters.getDataBits(), parameters.getStopBits(), parameters.getParity());
//            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            byte [] buffer = serialPort.readBytes(8,1000);
            String valor = new String(buffer);
            peso = convertStringToDigit(valor);
            return peso;
        }catch (SerialPortException | SerialPortTimeoutException e){
            e.printStackTrace();
        }finally {
            try {
                serialPort.closePort();
            }catch (SerialPortException e){
                e.printStackTrace();
            }
        }
        return peso;
    }

    public boolean isOpned(){
        return serialPort.isOpened();
    }

    public void closePort() throws SerialPortException {
        serialPort.closePort();
    }

    private static String convertStringToDigit(String valor) {
        StringBuffer buffer = new StringBuffer();
        char [] chars = valor.toCharArray();
        boolean stop = false;
        for (Character cr : chars) {
            if(cr == 'E') stop = true;
            if (Character.isDigit(cr) && stop == false) {
                buffer.append(cr);
            }
        }
        return buffer.toString();
    }
}
