import java.io.*;
import java.net.*;
import java.util.*;

public class MonitorByte{

	private long tempSaida;
	private InetAddress address;
	private int numPacket;
	private int port;

    public MonitorByte(InetAddress address, int numPacket, int port){
        this.tempSaida = System.currentTimeMillis();
        this.address = address;
        this.numPacket = numPacket;
        this.port = port;
    }

	public MonitorByte(byte[] data){
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInput in = null;
        Object o = null;
        try {
            in = new ObjectInputStream(bis);
            o = in.readObject(); 

            MonitorByte pm = (MonitorByte) o;

            this.numPacket = pm.getNumPacket();
            this.tempSaida = pm.getTempSaida();
            this.address = pm.getAddress();
            this.port = pm.getPort();
        }
        catch(Exception ex){
            System.out.println("BytesErro");
        }
    }

    synchronized public byte[] converteByte(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] data = null;
        try {
            out = new ObjectOutputStream(bos);   
            out.writeObject(this);
            out.flush();
            data = bos.toByteArray();
        } 
        catch (Exception ex) {
            System.out.println("converteByteErro");
        }
        return data;
    }

    synchronized public int getNumPacket(){
    	return numPacket;
    }

    synchronized public int getPort(){
    	return port;
    }

    synchronized public long getTempSaida(){
    	return tempSaida;
    }

    synchronized public InetAddress getAddress(){
    	return address;
    }

    synchronized void setTempSaida(long tempSaida){
        this.tempSaida = tempSaida;
    }
    
    public void setNumPacket(int numPacket){
        this.numPacket = numPacket;
    }

    public void setPort( int port){
        this.port = port;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

}
