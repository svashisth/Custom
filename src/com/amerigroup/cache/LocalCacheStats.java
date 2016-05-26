package com.amerigroup.cache;

import com.google.common.cache.CacheStats;

/**
 * Wrapper for Guava cache stats
 * 
 * @author AD79272
 *
 */
public final class LocalCacheStats
{
	private final long hitCount;
	private final long missCount;
	private final long loadSuccessCount;
	private final long loadExceptionCount;
	private final long totalLoadTime;
	private final long evictionCount;

	public static LocalCacheStats from(CacheStats cacheStats)
	{
		return new LocalCacheStats(cacheStats.hitCount(), cacheStats.missCount(), cacheStats.loadSuccessCount(),
				cacheStats.loadExceptionCount(), cacheStats.totalLoadTime(), cacheStats.evictionCount());
	}

	private LocalCacheStats(long hitCount, long missCount, long loadSuccessCount, long loadExceptionCount,
			long totalLoadTime, long evictionCount)
	{
		this.hitCount = hitCount;
		this.missCount = missCount;
		this.loadSuccessCount = loadSuccessCount;
		this.loadExceptionCount = loadExceptionCount;
		this.totalLoadTime = totalLoadTime;
		this.evictionCount = evictionCount;
	}

	public long requestCount()
	{
		return (this.hitCount + this.missCount);
	}

	public long hitCount()
	{
		return this.hitCount;
	}

	public double hitRate()
	{
		long requestCount = requestCount();
		return ((requestCount == 0L) ? 1.0D : this.hitCount / requestCount);
	}

	public long missCount()
	{
		return this.missCount;
	}

	public double missRate()
	{
		long requestCount = requestCount();
		return ((requestCount == 0L) ? 0.0D : this.missCount / requestCount);
	}

	public long loadCount()
	{
		return (this.loadSuccessCount + this.loadExceptionCount);
	}

	public long loadSuccessCount()
	{
		return this.loadSuccessCount;
	}

	public long loadExceptionCount()
	{
		return this.loadExceptionCount;
	}

	public double loadExceptionRate()
	{
		long totalLoadCount = this.loadSuccessCount + this.loadExceptionCount;
		return ((totalLoadCount == 0L) ? 0.0D : this.loadExceptionCount / totalLoadCount);
	}

	public long totalLoadTime()
	{
		return this.totalLoadTime;
	}

	public double averageLoadPenalty()
	{
		long totalLoadCount = this.loadSuccessCount + this.loadExceptionCount;
		return ((totalLoadCount == 0L) ? 0.0D : this.totalLoadTime / totalLoadCount);
	}

	public long evictionCount()
	{
		return this.evictionCount;
	}

	public LocalCacheStats minus(LocalCacheStats other)
	{
		return new LocalCacheStats(Math.max(0L, this.hitCount - other.hitCount), Math.max(0L, this.missCount
				- other.missCount), Math.max(0L, this.loadSuccessCount - other.loadSuccessCount), Math.max(0L,
				this.loadExceptionCount - other.loadExceptionCount), Math.max(0L, this.totalLoadTime
				- other.totalLoadTime), Math.max(0L, this.evictionCount - other.evictionCount));
	}

	public LocalCacheStats plus(LocalCacheStats other)
	{
		return new LocalCacheStats(this.hitCount + other.hitCount, this.missCount + other.missCount,
				this.loadSuccessCount + other.loadSuccessCount, this.loadExceptionCount + other.loadExceptionCount,
				this.totalLoadTime + other.totalLoadTime, this.evictionCount + other.evictionCount);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (evictionCount ^ (evictionCount >>> 32));
		result = prime * result + (int) (hitCount ^ (hitCount >>> 32));
		result = prime * result + (int) (loadExceptionCount ^ (loadExceptionCount >>> 32));
		result = prime * result + (int) (loadSuccessCount ^ (loadSuccessCount >>> 32));
		result = prime * result + (int) (missCount ^ (missCount >>> 32));
		result = prime * result + (int) (totalLoadTime ^ (totalLoadTime >>> 32));
		return result;
	}

	public boolean equals(Object object)
	{
		if (object instanceof LocalCacheStats)
		{
			LocalCacheStats other = (LocalCacheStats) object;
			return ((this.hitCount == other.hitCount) && (this.missCount == other.missCount)
					&& (this.loadSuccessCount == other.loadSuccessCount)
					&& (this.loadExceptionCount == other.loadExceptionCount)
					&& (this.totalLoadTime == other.totalLoadTime) && (this.evictionCount == other.evictionCount));
		}

		return false;
	}

	@Override
	public String toString()
	{
		return String
				.format("LocalCacheStats [hitCount=%s, missCount=%s, loadSuccessCount=%s, loadExceptionCount=%s, totalLoadTime=%s, evictionCount=%s]",
						hitCount, missCount, loadSuccessCount, loadExceptionCount, totalLoadTime, evictionCount);
	}

}
