package com.cyx.gobang.five.Utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class RandomUtils {
    
    private static final Logger logger = LogManager.getLogger(RandomUtils.class.getName());
    private static Random random = new Random();

    static {
	RandomUtils.random.setSeed(System.currentTimeMillis() * RandomUtils.random.hashCode() * RandomUtils.random.getClass().hashCode());
    }
    
    /**
     * 比率形式
     * @param probability
     * @param gailv
     * @return
     */
    public static boolean isGenerate(int probability, int gailv) {
	if (probability == gailv) {
	    return true;
	}
	if (gailv == 0) {
	    return false;
	}
	int random_seed = random.nextInt(probability);
	return random_seed + 1 <= gailv;
    }
    
    /**
     * 集合里面随机选取一个
     * @param collection
     * @return
     */
    public static <T> T randomElement(Collection<T> collection) {
	if (collection == null || collection.isEmpty()) {
	    return null;
	}
	int t = (int) (collection.size() * Math.random());
	int i = 0;
	for (Iterator<T> item = collection.iterator(); i <= t && item.hasNext();) {
	    T next = item.next();
	    if (i == t) {
		return next;
	    }
	    i++;
	}
	return null;
    }
    
    /**
     * 集合里面由比率随机选取一个元素
     * @param collection
     * @param probability
     * @param gailv
     * @return
     */
    public static <T> T choiceElement(Collection<T> collection, int probability, int gailv){
	if (collection == null || collection.isEmpty()) {
	    return null;
	}
	Iterator<T> item = collection.iterator();
	T next = null;
	while(item.hasNext()){
	    next = item.next();
	    if(isGenerate(probability, gailv)){
		return next;
	    }
	}
	return next;
	
    }
}
