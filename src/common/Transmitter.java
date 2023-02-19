package common;

import model.Airplane;

import java.net.Socket;
import java.util.Collection;

public interface Transmitter {
    void send(Collection<Airplane> airplanes, Socket s) throws Exception;
    Collection<Airplane> receive(Socket s) throws Exception;
}
