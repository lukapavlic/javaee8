package si.um.feri.listeners;

import javax.batch.api.chunk.listener.AbstractItemReadListener;
import javax.inject.Named;

@Named("MyItemReadListener")
public class MyItemReadListener extends AbstractItemReadListener {

    public MyItemReadListener() {
    	
    };

    @Override()
    public void beforeRead() throws Exception {
    	
    };

    @Override()
    public void afterRead(Object item) throws Exception {
    	
    };

    @Override()
    public void onReadError(Exception ex) throws Exception {
    	
    };
}
