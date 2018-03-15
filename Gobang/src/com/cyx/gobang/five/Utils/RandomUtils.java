package com.cyx.gobang.five.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cyx.gobang.five.structs.BestPoint;

public class RandomUtils {

    private static final Logger logger = LogManager.getLogger(RandomUtils.class.getName());
    private static Random random = new Random();

    static {
	RandomUtils.random.setSeed(System.currentTimeMillis() * RandomUtils.random.hashCode() * RandomUtils.random.getClass().hashCode());
    }

    /**
     * 比率形式
     * 
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
     * 
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
     * 
     * @param collection
     * @param probability
     * @param gailv
     * @return
     */
    public static <T> T choiceElement(Collection<T> collection, int probability, int gailv) {
	if (collection == null || collection.isEmpty()) {
	    return null;
	}
	Iterator<T> item = collection.iterator();
	T next = null;
	while (item.hasNext()) {
	    next = item.next();
	    if (isGenerate(probability, gailv)) {
		return next;
	    }
	}
	return next;

    }

    public static List<BestPoint> randomSameElement(List<BestPoint> collection) {
	if (collection == null || collection.isEmpty()) {
	    return collection;
	}
	int index_min = 0;
	int index_max = 0;
	List<BestPoint> tem = new ArrayList<BestPoint>();
	tem.add(collection.get(0));
	for (int i = 0; i < collection.size(); i++) {
	    index_max = i;
	    if(index_min >= index_max){
		continue;
	    }
	    if (collection.get(index_min).getMaxScore() == collection.get(index_max).getMaxScore()) {
		//随机交换
		Random random = new Random();  
		swap(collection,index_max,random.nextInt(index_max - index_min + 1) + index_min);
	    } else {
		index_min = i;
	    }
	}
	return collection;
    }
    
    public static <T> List<T> swap(List<T> list,int i,int j){
	if(i == j){
	    return list;
	}
        final List<T> l=list;
        l.set(i, l.set(j, l.get(i)));
        return list;
    }
    
}
