package si.um.feri;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/*
 * Zapiše podatke nazaj v definiran vir
 */
@Named("SimpleItemWriter")
public class SimpleItemWriter extends AbstractItemWriter {

	//@PersistenceContext
	//EntityManager em;

	public void writeItems(List list) throws Exception {
		for (Object obj : list) {
			System.out.println("KnjigaRecord: " + obj);
			//em.persist(obj);
		}
	}

}
