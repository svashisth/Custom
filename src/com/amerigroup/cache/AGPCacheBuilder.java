package com.amerigroup.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

/**
 * Wrapper for Guava cache. More details about Guava cache are here https://github.com/google/guava/wiki/CachesExplained
 * 
 * @author AD79272
 *
 */
public class AGPCacheBuilder
{
	//Container for all cache elements
	private static Map<String, LocalCache<?, ?>> cacheMap = new ConcurrentHashMap<String, LocalCache<?, ?>>();

	/**
	 * Create a size and time based cache. Cache entries after defined 'duration' and over defined size would be
	 * evicted.
	 * 
	 * @param builder
	 * @return LocalCache
	 */
	private static <K, V> LocalCache<K, V> getCache(Builder<K, V> builder)
	{
		Cache<K, V> cache = CacheBuilder.newBuilder().expireAfterAccess(builder.duration, builder.timeUnit)
				.initialCapacity(builder.initialCapacity).maximumSize(builder.size).build();
		LocalCache<K, V> localCache = LocalCache.from(cache);
		cacheMap.put(builder.cacheName, localCache);
		return localCache;
	}

	/**
	 * Create a size and time based loading cache. The entries can be loaded in cache at runtime using a
	 * LocalCacheLoader (TBD) Cache entries after defined 'duration' and over defined size would be evicted.
	 * 
	 * @param builder
	 * @return LocalCache
	 */
	private static <K, V> LocalCache<K, V> getLoadingCache(Builder<K, V> builder)
	{
		Cache<K, V> cache = CacheBuilder.newBuilder().expireAfterAccess(builder.duration, builder.timeUnit)
				.initialCapacity(builder.initialCapacity).maximumSize(builder.size).build(builder.loader);
		LocalCache<K, V> localCache = LocalCache.from(cache);
		cacheMap.put(builder.cacheName, localCache);
		return localCache;
	}

	/**
	 * Clear all the caches
	 */
	public static void clearAll()
	{
		for (LocalCache<?, ?> cache : cacheMap.values())
		{
			cache.invalidateAll();
		}
	}

	/**
	 * cleans cache by name
	 * 
	 * @param cacheName name of cache
	 */
	public static void clear(String cacheName)
	{
		cacheMap.get(cacheName).invalidateAll();
	}

	/**
	 * cleans cache by name
	 * 
	 * @param cacheName name of cache
	 */
	public static long getCacheSize(String cacheName)
	{
		return cacheMap.get(cacheName).size();
	}

	/**
	 * cleans cache by name
	 * 
	 * @param cacheName name of cache
	 */
	public static Set<String> getAllCacheNames()
	{
		return cacheMap.keySet();
	}

	/**
	 * return internal cache structure as map
	 * 
	 * @param cacheName
	 * @return map of entries
	 */
	public static ConcurrentMap<?, ?> getCacheAsMap(String cacheName)
	{
		return cacheMap.get(cacheName).asMap();
	}

	static class Builder<K, V>
	{
		private CacheLoader<? super K, V> loader;
		private long size;
		private int initialCapacity;
		private String cacheName;
		private TimeUnit timeUnit;
		private long duration;

		private Builder(String cacheName)
		{
			super();
			this.cacheName = cacheName;
		}

		public Builder<K, V> setLoader(CacheLoader<? super K, V> loader)
		{
			this.loader = loader;
			return this;
		}

		public Builder<K, V> setSize(long size)
		{
			this.size = size;
			return this;
		}

		public Builder<K, V> setInitialCapacity(int initialCapacity)
		{
			this.initialCapacity = initialCapacity;
			return this;
		}

		public Builder<K, V> setTimeUnit(TimeUnit timeUnit)
		{
			this.timeUnit = timeUnit;
			return this;
		}

		public Builder<K, V> setCacheDuration(long duration)
		{
			this.duration = duration;
			return this;
		}

		public LocalCache<K, V> build()
		{
			if (loader == null)
				return getCache(this);
			else
				return getLoadingCache(this);
		}
	}
	/*
	public static void main(String[] args) throws InterruptedException
	{
		String cacheName = "myCache";
		// Create a cache with 10 seconds as TTL.
		LocalCache<String, String> cache = new AGPCacheBuilder.Builder<String,String>(cacheName).setInitialCapacity(100).setSize(1000).setCacheDuration(10).setTimeUnit(TimeUnit.SECONDS).build();
		System.out.println("main\t" + cache);
		System.out.println("main\t" + AGPCacheBuilder.getCacheAsMap(cacheName));
		for(int i = 1; i<2500; i++){
			cache.put("a-"+i, "val"+i*i);
		}
		System.out.println("main\t" + AGPCacheBuilder.getCacheAsMap(cacheName));
	//		Thread.sleep(10000);
	//		System.out.println("main\t" + AGPCacheBuilder.getCacheAsMap(cacheName));	//Cache should be empty now
		System.out.println("main\t" + AGPCacheBuilder.getAllCacheNames());
		
		cache.invalidateAll();
		System.out.println("main\t" + AGPCacheBuilder.getCacheAsMap(cacheName));
		cache.put("a-2000", "val-20");
		String val = cache.get("a-2000");
		System.out.println("main\t" + val); 
	}
	*/
}
