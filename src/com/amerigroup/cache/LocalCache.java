package com.amerigroup.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.google.common.cache.Cache;

/**
 * Wrapper for Guava cache element
 * 
 * @author AD79272
 *
 * @param <K>
 * @param <V>
 */
public class LocalCache<K, V>
{

	private Cache<K, V> cache;

	public static <K, V> LocalCache<K, V> from(Cache<K, V> cache)
	{
		return new LocalCache<K, V>(cache);
	}

	private LocalCache(Cache<K, V> cache)
	{
		this.cache = cache;
	}

	public void invalidateAll()
	{
		cache.invalidateAll();
	}

	public void invalidateAll(Iterable<K> keys)
	{
		cache.invalidateAll(keys);
	}

	public void invalidate(K key)
	{
		cache.invalidate(key);
	}

	public long size()
	{
		return cache.size();
	}

	public ConcurrentMap<K, V> asMap()
	{
		return cache.asMap();
	}

	public void put(K key, V val)
	{
		cache.put(key, val);
	}

	public V get(K key)
	{
		return cache.getIfPresent(key);
	}

	public void putAll(Map<? extends K, ? extends V> map)
	{
		cache.putAll(map);
	}

	public LocalCacheStats stats()
	{
		return LocalCacheStats.from(cache.stats());
	}

}
