package domaine;

public class QueryFactoryImpl implements QueryFactory {

    @Override
    public  QueryImpl getQuery(){
        return new QueryImpl();
    }
}
