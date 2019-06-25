package si.um.feri;

import java.io.Serializable;
import java.util.Iterator;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("jCache")
@SessionScoped
public class JCacheBean implements Serializable {

	private String getCachedata;

	// privzet cache provider
	CachingProvider cachingProvider = Caching.getCachingProvider();

	// privzet cache manager
	CacheManager manager = cachingProvider.getCacheManager();

	public void setCache() {

		// definiramo cache
		MutableConfiguration<String, String> cacheConfig = new MutableConfiguration<String, String>()
				.setStoreByValue(true).setTypes(String.class, String.class);

		// ustvari cache - loader
		Cache<String, String> cache = manager.createCache("knjige", cacheConfig);

		cache.put("Peter Klepec", "France Bevk");
		cache.put("Piki Jakob", "Kajetan Koviè");
		cache.put("Moj dežnik je lahko balon", "Ela Peroci");
		cache.put("Mavrièna ribica", "Marcus Pfister");
	}

	public void catchCache() {
		// pridobivanje obstojecega cache
		Cache<String, String> cache_get = manager.getCache("knjige", String.class, String.class);
		Iterator<Cache.Entry<String, String>> allCacheEntries = cache_get.iterator();
		while (allCacheEntries.hasNext()) {
			Cache.Entry<String, String> currentEntry = allCacheEntries.next();
			System.out.println("Key: " + currentEntry.getKey() + " Value: " + currentEntry.getValue());
		}		
	}

	public String getGetCachedata() {
		return getCachedata;
	}

	public void setGetCachedata(String getCachedata) {
		this.getCachedata = getCachedata;
	}
}
