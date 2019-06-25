package si.um.feri;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.spi.CachingProvider;

public class CacheEntryListener {

	public static final boolean IS_OLD_VALUE_REQUIRED = false;
	public static final boolean IS_SYNCHRONOUS = true;
	public static final Factory<? extends CacheEntryEventFilter> NO_FILTER = null;

	public static void main(String[] args) {

		// privzeti cache provider
		CachingProvider cachingProvider = Caching.getCachingProvider();

		// privzeti cache manager
		CacheManager manager = cachingProvider.getCacheManager();

		// definiramo cache
		MutableConfiguration<String, String> cacheConfig = new MutableConfiguration<String, String>()
				.setStoreByValue(true).setTypes(String.class, String.class);

		// ustvarimo cache
		Cache<String, String> cache = manager.createCache("knjige", cacheConfig);

		// poslusalec
		CacheEntryListenerConfiguration listenerConfiguration = new MutableCacheEntryListenerConfiguration(
				FactoryBuilder.factoryOf(EntryListener.class), NO_FILTER, IS_OLD_VALUE_REQUIRED, IS_SYNCHRONOUS);
		
		CacheEntryListenerConfiguration listenerConfigurationWithFilter =
                new MutableCacheEntryListenerConfiguration(FactoryBuilder.factoryOf(EntryListener.class),
                        FactoryBuilder.factoryOf(EntryFilter.class),
                        IS_OLD_VALUE_REQUIRED,
                        IS_SYNCHRONOUS);

		cache.registerCacheEntryListener(listenerConfiguration);
		cache.registerCacheEntryListener(listenerConfigurationWithFilter);
		
		// dodamo knjige v cache
		cache.put("Peter Klepec", "France Bevk");
		cache.put("Piki Jakob", "Kajetan Koviè");
		cache.put("Moj dežnik je lahko balon", "Ela Peroci");
		cache.put("Mavrièna ribica", "Marcus Pfister");

	}
}
