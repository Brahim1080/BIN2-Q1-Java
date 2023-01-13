package domain;

public class DomaineFactoryImpl implements DomaineFactory {

    @Override
    public Request createRequest(){
        return new RequestImpl();
    }
}
