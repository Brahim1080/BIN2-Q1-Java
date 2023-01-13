package code_theorie;

import java.util.function.Consumer;

public class InterfaceForEach implements Consumer {
    @Override
    public void accept(Object o) {
        System.out.println(o.toString());
    }
}
