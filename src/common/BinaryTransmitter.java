package common;

import common.Transmitter;
import model.Airplane;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class BinaryTransmitter implements Transmitter {
    @Override
    public void send(Collection<Airplane> airplanes, Socket s) throws Exception {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()))) {
            dos.write(airplanes.size());
            for (Airplane airplane : airplanes) {
                dos.writeUTF(airplane.getName());
                dos.write(airplane.getSeats());
                dos.writeFloat(airplane.getvMax());
                dos.writeUTF(airplane.getManufacturer());
                dos.writeFloat(airplane.getFuelCapacity());
            }
            dos.flush();
        }
    }

    @Override
    public Collection<Airplane> receive(Socket s) throws Exception {
        Collection<Airplane> airplanes = new ArrayList<>();

        String name;
        int seats;
        float vMax;
        String manufacturer;
        float fuelCapacity;

        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()))) {
            int size = dis.readInt();

            for (int i = 0; i < size; i++) {
                name = dis.readUTF();
                seats = dis.readInt();
                vMax = dis.readFloat();
                manufacturer = dis.readUTF();
                fuelCapacity = dis.readFloat();

                airplanes.add(new Airplane(name, seats, vMax, manufacturer, fuelCapacity));
            }

        }

        return airplanes;
    }
}
