package si.um.feri;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryUpdatedListener;

public class EntryListener
		implements CacheEntryCreatedListener<String, String>, CacheEntryUpdatedListener<String, String> {
	//Poslusalec, ki izvede izpis ob posodobitvi 
	public void onUpdated(Iterable<CacheEntryEvent<? extends String, ? extends String>> events)
			throws CacheEntryListenerException {
		for (CacheEntryEvent entryEvent : events) {
			System.out.println("posodobljeno : " + entryEvent.getKey() + ", vrednost : " + entryEvent.getValue());
		}

	}
	//Poslusalec, ki izvede izpis ob vnosu 
	public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends String>> events)
			throws CacheEntryListenerException {
		for (CacheEntryEvent entryEvent : events) {
            System.out.println("ustvarjeno : " + entryEvent.getKey() + ", vrednost : " + entryEvent.getValue());
        }

	}

}