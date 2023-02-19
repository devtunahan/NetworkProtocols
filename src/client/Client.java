package client;

import common.BinaryTransmitter;
import common.ObjectTransmitter;
import model.Airplane;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

public class Client {
    public static void main(String[] args) {

        Collection<Airplane> airplanes = new ArrayList<>();
        airplanes.add(new Airplane("747", 300, 652.8f, "Boing", 3008229));
        airplanes.add(new Airplane("TR92", 200, 652.8f, "TR Airlines", 3927300));
        airplanes.add(new Airplane("A420", 400, 652.8f, "Airbus", 3028200));

        try(Socket serverConnection = new Socket("localhost",3000)){
            new ObjectTransmitter().send(airplanes,serverConnection);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
