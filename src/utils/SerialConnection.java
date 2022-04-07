package utils;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPortTimeoutException;
import model.entities.PortParameters;

import java.util.ArrayList;
import java.util.List;

public class SerialConnection {

    private SerialPort serialPort;

    public SerialConnection(PortParameters parameters) {
        if (parameters == null) throw new IllegalStateException("Não pode ser nulo");
        try {
            serialPort = new SerialPort(parameters.getPortName());
            serialPort.openPort();
            serialPort.setParams(parameters.getBaundRate(), parameters.getDataBits(), parameters.getStopBits(), parameters.getParity());
        }catch (SerialPortException e){
            e.printStackTrace();
        }
    }

    public static List<String> portsCom(){
        List<String> portas = new ArrayList<>();
        for(String p: SerialPortList.getPortNames()){
            portas.add(p);
        }
        return portas;
    }

    public double lerPeso(){
        try{
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            byte [] buffer = serialPort.readBytes(8,1000);
            String valor = new String(buffer);
            double peso = Double.parseDouble(convertStringToDigit(valor));
            return peso;
        }catch (SerialPortException | SerialPortTimeoutException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void closePort() throws SerialPortException {
        serialPort.closePort();
    }

    private static String convertStringToDigit(String valor) {
        StringBuffer buffer = new StringBuffer();
        char [] chars = valor.toCharArray();

        for (Character cr : chars) {
            if (Character.isDigit(cr)) {
                buffer.append(cr);
            }
        }
        return buffer.toString();
    }
}
