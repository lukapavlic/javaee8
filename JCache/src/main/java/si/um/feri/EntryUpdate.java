package si.um.feri;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import javax.cache.spi.CachingProvider;

public class EntryUpdate {
	public static void main(String[] args) {

        
        CachingProvider cachingProvider = Caching.getCachingProvider();

        
        CacheManager manager = cachingProvider.getCacheManager();

        
        MutableConfiguration<String, String> cacheConfig =
                new MutableConfiguration<String, String>()
                        .setStoreByValue(true)
                        .setTypes(String.class, String.class);

        
        Cache<String, String> cache = manager.createCache("knjige", cacheConfig);

        cache.put("Peter Klepec", "France Bevk");
		cache.put("Piki Jakob", "Kajetan Koviè");
		cache.put("Moj dežnik je lahko balon", "Ela Peroci");
		cache.put("Mavrièna ribica", "Marcus Pfister");

        // to upper Case
        cache.invoke("Peter Klepec", new EntryProcessor<String, String, Object>() {
            public Object process(MutableEntry<String, String> entry, Object... arguments) throws EntryProcessorException {
                if (entry.exists()) {
                    entry.setValue(entry.getValue().toUpperCase());
                }
                return null;
            }
        });

        // upper Case izpis
        System.out.println("Avtor Petra Klepca je : " + cache.get("Peter Klepec"));

    }
}
