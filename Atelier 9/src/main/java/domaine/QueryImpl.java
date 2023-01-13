package domaine;

 class QueryImpl implements Query {
    private String url;
    private QueryMethod httpMethod;


    public QueryImpl(String url, QueryMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

     public QueryImpl() {
     }

     @Override
    public String getUrl() {
        return url;
    }

    @Override
    public QueryMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setHttpMethod(QueryMethod httpMethod) {
        this.httpMethod = httpMethod;
    }




}
