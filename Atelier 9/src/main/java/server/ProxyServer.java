package server;

import domaine.QueryFactory;
import domaine.Query;
import domaine.Query.QueryMethod;

import java.util.Scanner;


public class ProxyServer {
    private static Scanner scanner = new Scanner(System.in);

    private QueryFactory queryFactory;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void startServer() {
        while (true) {

            System.out.print("Entrez une url de site : ");
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            Query query = this.queryFactory.getQuery();
            query.setUrl(url);
            query.setHttpMethod(QueryMethod.GET);
            QueryHandler queryHandler = new QueryHandler(query);
            queryHandler.start();
            try {
                queryHandler.join(); //Attend la fin de ce thread pour passer à la suite de la boucle
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
