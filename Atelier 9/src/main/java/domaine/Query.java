package domaine;

public interface Query {
    String getUrl();

    QueryMethod getHttpMethod();

    void setUrl(String url);

    void setHttpMethod(QueryMethod httpMethod);

    public enum QueryMethod {
        GET, POST;
    }
}
