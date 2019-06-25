package si.um.feri.listeners;

import javax.batch.api.listener.AbstractJobListener;
import javax.inject.Named;

@Named("MyJobListener")
public class MyJobListener extends AbstractJobListener {

    public MyJobListener() {
    	
    };

    @Override()
    public void beforeJob() {
    	
    };

    @Override()
    public void afterJob() {
    	
    };
}