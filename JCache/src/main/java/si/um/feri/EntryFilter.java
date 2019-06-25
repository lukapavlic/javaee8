package si.um.feri;

import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListenerException;
/*
 * Lasten filter 
 */
public class EntryFilter implements CacheEntryEventFilter<String, String> {

	public boolean evaluate(CacheEntryEvent<? extends String, ? extends String> event)
			throws CacheEntryListenerException {
		return "Kajetan Kovi�".equals(event.getValue());
	}
}