package dataStructures.hashtable;

public interface HashTableInterface<K, V> {

    public void insert(K key, V value) throws HashTableException;

    public V search(K key);

    public boolean remove(K key);

}//End hashTableInterface interface
