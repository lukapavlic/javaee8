package si.um.feri;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class CachePrimer {

	public static void main(String[] args) {

		// privzet cache provider
		CachingProvider cachingProvider = Caching.getCachingProvider();

		//privzet cache manager
		CacheManager manager = cachingProvider.getCacheManager();

		// definiramo cache
		MutableConfiguration<String, String> cacheConfig = new MutableConfiguration<String, String>()
				.setStoreByValue(true).setTypes(String.class, String.class);

		// ustvari cache - loader
		Cache<String, String> cache = manager.createCache("knjige", cacheConfig);

		// pridobivanje obstojecega cache
		Cache<String, String> cache_get = manager.getCache("knjige", String.class, String.class);
				
		cache.put("Peter Klepec", "France Bevk");
		cache.put("Piki Jakob", "Kajetan Koviè");
		cache.put("Moj dežnik je lahko balon", "Ela Peroci");
		cache.put("Mavrièna ribica", "Marcus Pfister");

		System.out.println("Avtor knjige Peter Klepec : " + cache_get.get("Peter Klepec"));

	}

}
