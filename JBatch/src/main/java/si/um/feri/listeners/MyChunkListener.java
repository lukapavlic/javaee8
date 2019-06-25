package si.um.feri.listeners;

import javax.batch.api.chunk.listener.AbstractChunkListener;
import javax.inject.Named;

@Named("MyChunkListener")
public class MyChunkListener extends AbstractChunkListener {

    public MyChunkListener() {
    	
    };

    @Override()
    public void beforeChunk() throws Exception {
    	
    };

    @Override()
    public void afterChunk() throws Exception {
    	
    };
}