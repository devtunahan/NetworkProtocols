package server;

import common.BinaryTransmitter;
import common.ObjectTransmitter;
import model.Airplane;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(3000);Socket clientConnection = serverSocket.accept();) {
        //receive data
            Collection<Airplane> airplanes = new ObjectTransmitter().receive(clientConnection);
            System.out.println(airplanes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
