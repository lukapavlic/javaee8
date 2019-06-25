package si.um.feri.listeners;

import javax.batch.api.chunk.listener.AbstractItemProcessListener;
import javax.inject.Named;

@Named("MyItemProcessorListener")
public class MyItemProcessorListener extends AbstractItemProcessListener {

    public MyItemProcessorListener() {
    	
    };

    @Override()
    public void beforeProcess(Object item) throws Exception {
    	
    };

    @Override()
    public void afterProcess(Object item, Object result) throws Exception {
    	
    };

    @Override()
    public void onProcessError(Object item, Exception ex) throws Exception {
    	
    };
}