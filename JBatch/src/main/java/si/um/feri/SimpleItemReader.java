package si.um.feri;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
/*
 * Branje podatkov
 */
@Named("SimpleItemReader")
public class SimpleItemReader extends AbstractItemReader {

	@Inject
	private JobContext jobContext;
	private Integer recordNumber = 0;
	private Map<String, String> map = new HashMap();
	//Logika branja podatkov
	public void open(Serializable prevCheckpointInfo) throws Exception {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
		map.put(jobParameters.getProperty("knjigaInputDataFileName")
				.substring(1, jobParameters.getProperty("knjigaInputDataFileName").length() - 1).split("=")[0],
				jobParameters.getProperty("knjigaInputDataFileName")
						.substring(1, jobParameters.getProperty("knjigaInputDataFileName").length() - 1).split("=")[1]);
	}
	//vrne prebrano ali null
	public Object readItem() throws Exception {
		Object record = null;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Knjiga knjigaInputRecord = new Knjiga();
			knjigaInputRecord.setId(Integer.parseInt(entry.getKey()));
			knjigaInputRecord.setNaslov(entry.getValue());
			record = knjigaInputRecord;
			recordNumber++;
		}
		return record;
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return recordNumber;
	}

}