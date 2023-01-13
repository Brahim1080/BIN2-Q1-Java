package main;

import domain.DomaineFactoryImpl;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(new DomaineFactoryImpl().createRequest());
        try {
            server.listenKeyboard();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
