package model.entities;

public class PortParameters {
    private String portName;
    private Integer baundRate;
    private Integer dataBits;
    private Integer stopBits;
    private Integer parity;

    public PortParameters(){

    }

    public PortParameters(String portName, Integer baundRate, Integer dataBits, Integer stopBits, Integer parity) {
        this.portName = portName;
        this.baundRate = baundRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public Integer getBaundRate() {
        return baundRate;
    }

    public void setBaundRate(Integer baundRate) {
        this.baundRate = baundRate;
    }

    public Integer getDataBits() {
        return dataBits;
    }

    public void setDataBits(Integer dataBits) {
        this.dataBits = dataBits;
    }

    public Integer getStopBits() {
        return stopBits;
    }

    public void setStopBits(Integer stopBits) {
        this.stopBits = stopBits;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }
}


