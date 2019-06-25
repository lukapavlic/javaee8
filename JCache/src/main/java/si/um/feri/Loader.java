package si.um.feri;

import java.util.Map;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;

public class Loader implements CacheLoader<String, String> {

    public String load(String key) throws CacheLoaderException {
        if ("Mavrièna ribica".equals(key)) {
            return "Marcus Pfister";
        }
        return null;
    }

    /**
     * Async Load Up of a Cache.
     */
    public Map<String, String> loadAll(Iterable<? extends String> keys) throws CacheLoaderException {

        //iteracija skozi kljuce in pridobivanje iz podatkovne baze
        return null;

    }
}
