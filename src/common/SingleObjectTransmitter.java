package common;

import model.Airplane;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class SingleObjectTransmitter implements Transmitter {

    @Override
    public void send(Collection<Airplane> airplanes, Socket s) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()))) {
            //    oos.writeObject(new Integer(airplanes.size()));
            oos.writeInt(airplanes.size());
            for (Airplane airplane : airplanes) {
                oos.writeObject(airplane);
            }
            oos.flush();
        }

    }

    @Override
    public Collection<Airplane> receive(Socket s) throws Exception {
        Collection<Airplane> airplanes = new ArrayList<>();


        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(s.getInputStream()))) {
            int size = ois.readInt();
            for (int i = 0; i < size; i++) {
                airplanes.add((Airplane) ois.readObject());
            }
        }
        return airplanes;    }

}

