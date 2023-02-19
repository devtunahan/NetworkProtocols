package common;

import model.Airplane;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

public class ObjectTransmitter implements Transmitter{
    @Override
    public void send(Collection<Airplane> airplanes, Socket s) throws Exception {
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));) {
            oos.writeObject(airplanes);
            oos.flush();
        }
    }

    @Override
    public Collection<Airplane> receive(Socket s) throws Exception {
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(s.getInputStream()))) {
            return (Collection<Airplane>) ois.readObject();
        }
    }
}
