package si.um.feri.listeners;

import java.util.List;

import javax.batch.api.chunk.listener.AbstractItemWriteListener;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Named;

import si.um.feri.Knjiga;

@Named("MyItemWriteListener")
public class MyItemWriteListener extends AbstractItemWriteListener {

    public MyItemWriteListener() {
    	
    };

    @Override()
    public void beforeWrite(List items) throws Exception {
    	
    };
    //poslušalec, ki izvede izpis v konzolo po vsakem zapisu SimpleItemWriterja
    @Override()
    public void afterWrite(List items) throws Exception {
    	for (Object item : items)
    	System.out.println("Vpisan: "+item);
    	
    	JobOperator jobOperator = BatchRuntime.getJobOperator();
		jobOperator.stop(1);
    };

    @Override()
    public void onWriteError(List items, Exception ex) throws Exception {
    	
    };
}
