package model.services;

import model.entities.PortParameters;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class PropertiesServices {

    private static final String URL_CONFIGURATION = "port.properties";

    public static PortParameters loadProperties(){
        try {
            Properties prop = new Properties();
            InputStream file = new FileInputStream(URL_CONFIGURATION);
            prop.load(file);
            PortParameters portParameters = instantiatePortParameters(prop);
            return portParameters;
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private static PortParameters instantiatePortParameters(Properties props){
        PortParameters portParameters = new PortParameters();

        portParameters.setPortName(props.getProperty("port_name"));
        portParameters.setBaundRate(Integer.parseInt(props.getProperty("baund_rate")));
        portParameters.setDataBits(Integer.parseInt(props.getProperty("data_bits")));
        portParameters.setStopBits(Integer.parseInt(props.getProperty("stop_bits")));
        portParameters.setParity(Integer.parseInt(props.getProperty("parity")));

        return portParameters;
    }

    public static void writeProperties(PortParameters portParameters){
        Properties prop = new Properties();
        try{
            FileOutputStream file = new FileOutputStream(URL_CONFIGURATION);

            prop.setProperty("port_name",portParameters.getPortName());
            prop.setProperty("baund_rate",portParameters.getBaundRate().toString());
            prop.setProperty("data_bits",portParameters.getDataBits().toString());
            prop.setProperty("stop_bits",portParameters.getStopBits().toString());
            prop.setProperty("parity",portParameters.getParity().toString());

            prop.store(file, null);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
