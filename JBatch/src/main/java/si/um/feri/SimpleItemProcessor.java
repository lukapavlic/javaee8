package si.um.feri;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
/*
 * Poslovna logika za obdelavo prebranih podatkov
 */
@Named("SimpleItemProcessor")
public class SimpleItemProcessor implements ItemProcessor {

	@Inject
	private JobContext jobContext;
	//procesiranje podatkov
	public Object processItem(Object obj) throws Exception {
		Knjiga inputRecord = (Knjiga) obj;
		KnjigaNew knjigaRecord = new KnjigaNew();
		String avtor = null;
		String base = inputRecord.getNaslov();

		if (inputRecord.getNaslov().equalsIgnoreCase("Mi, kosovirji")) {
			avtor = "Svetlana MakaroviÄ�";
		} else if (inputRecord.getNaslov().equalsIgnoreCase("Mali princ")) {
			avtor = "	Antoine de Saint-ExupÃ©ry";
		} else if (inputRecord.getNaslov().equalsIgnoreCase("ÄŒrno belo")) {
			avtor = "Å½iga x. GombaÄ�";
		} else if (inputRecord.getNaslov().equalsIgnoreCase("Skodelica kave")) {
			avtor = "Ivan Cankar";
		}

		knjigaRecord.setId(inputRecord.getId());
		knjigaRecord.setNaslov(inputRecord.getNaslov());
		knjigaRecord.setAvtor(avtor);
		return knjigaRecord;
	}
}