package si.um.feri;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

@Named("JBatchBean")
@SessionScoped
public class JBatchBean implements Serializable {
	
	public void startBatch() {
		try {
			startNewBatchJob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//zagon Batch opravila
	private long startNewBatchJob() throws Exception {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties props = new Properties();
		Map<Integer, String> map = new HashMap();
		map.put(1, "Mali princ");
		props.setProperty("knjigaInputDataFileName", map.toString()); //pot do datoteke iz katere se berejo podatki
		return jobOperator.start("job", props);
	}
	//prekinitev Batch opravila
	public void stopBatch() throws Exception {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		jobOperator.stop(1);
	}

}
