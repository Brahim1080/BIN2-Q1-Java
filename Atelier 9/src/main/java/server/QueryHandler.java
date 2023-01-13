package server;

import domaine.Query;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class QueryHandler extends Thread {

    private Query query;


    public QueryHandler(Query query) {
        this.query = query;
    }


    @Override
    public void run() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(query.getUrl());

            try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
                System.out.println(response1.getCode() + " " + response1.getReasonPhrase());
                HttpEntity entity1 = response1.getEntity();
                String body = EntityUtils.toString(entity1);
                System.out.println(body);
                EntityUtils.consume(entity1);


            } catch (ParseException | IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
