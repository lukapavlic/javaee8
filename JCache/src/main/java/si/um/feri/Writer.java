package si.um.feri;

import java.util.Collection;

import javax.cache.Cache;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;

public class Writer implements CacheWriter<String, String> {

    public void write(Cache.Entry<? extends String, ? extends String> entry) throws CacheWriterException {
        System.out.println("Vpis vnosa : " + entry.getKey());
    }

    public void writeAll(Collection<Cache.Entry<? extends String, ? extends String>> entries) throws CacheWriterException {
        for (Cache.Entry entry : entries) {
            System.out.println("Vpis vnosa : " + entry.getKey());
        }
    }

    public void delete(Object key) throws CacheWriterException {

    }

    public void deleteAll(Collection<?> keys) throws CacheWriterException {

    }
}
