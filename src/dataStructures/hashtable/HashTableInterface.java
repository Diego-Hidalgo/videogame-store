package dataStructures.hashtable;

public interface HashTableInterface<K, V> {

    public boolean isEmpty();

    public int size();

    public void insert(K key, V value);

    public void search(K key);

    public void delete(K key);

}//End HashTableInterface
