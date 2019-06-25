package si.um.feri;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

@CacheDefaults(cacheName = "knjige")
public class Annotations {

    @CacheResult()
    public String getKnjiga(String country){
        // Koda
        return "knjiga";
    }

    @CacheRemove()
    public void removeKnjiga(String country){
    	// Koda
    }

    @CacheRemoveAll()
    public void removeAllKnjige(){
    	// Koda
    }

    @CachePut()
    public void createKnjiga(@CacheKey String naslov, @CacheValue String avtor) {
    	// Koda
    }

}
