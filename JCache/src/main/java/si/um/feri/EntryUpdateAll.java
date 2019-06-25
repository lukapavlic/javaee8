package si.um.feri;

import java.util.Iterator;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
/*
 * Posodobitev vnosov, male crke spremenimo v velike
 */
public class EntryUpdateAll {
	
	public static void main(String[] args) {

        CachingProvider cachingProvider = Caching.getCachingProvider();

        CacheManager manager = cachingProvider.getCacheManager();

        MutableConfiguration<String, String> cacheConfig =
                new MutableConfiguration<String, String>()
                        .setStoreByValue(true)
                        .setTypes(String.class, String.class);

        Cache<String, String> cache = manager.createCache("knjige", cacheConfig);
        
        cache.put("Peter Klepec", "France Bevk");
		cache.put("Piki Jakob", "Kajetan Kovi�");
		cache.put("Moj de�nik je lahko balon", "Ela Peroci");
		cache.put("Mavri�na ribica", "Marcus Pfister");
		
		Iterator<Cache.Entry<String, String>> iterator_syso = cache.iterator();
        while (iterator_syso.hasNext()) {
            Cache.Entry<String, String> cacheEntry = iterator_syso.next();
            String value = cacheEntry.getValue();
            String key = cacheEntry.getKey();
            System.out.println("Naslov: "+key+", avtor: "+value);
        }
        
        Iterator<Cache.Entry<String, String>> iterator = cache.iterator();
        while (iterator.hasNext()) {
            Cache.Entry<String, String> cacheEntry = iterator.next();
            String value = cacheEntry.getValue();
            String key = cacheEntry.getKey();
            String upperCase = value.toUpperCase();
            cache.replace(key, value, upperCase);
        }
        System.out.println();
        System.out.println("Z veliko:");
        Iterator<Cache.Entry<String, String>> iterator_syso_upper = cache.iterator();
        while (iterator_syso_upper.hasNext()) {
            Cache.Entry<String, String> cacheEntry = iterator_syso_upper.next();
            String value = cacheEntry.getValue();
            String key = cacheEntry.getKey();
            System.out.println("Naslov: "+key+", avtor: "+value);
        }

    }
	
}
