package main;

import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import server.ProxyServer;

public class main {
    public static void main(String[] args) {
        QueryFactory queryFactoryImpl = new QueryFactoryImpl();
        ProxyServer server = new ProxyServer(queryFactoryImpl);

        server.startServer();

    }
}
