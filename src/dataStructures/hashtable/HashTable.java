package dataStructures.hashtable;

import java.util.Arrays;
import java.util.Objects;

public class HashTable<K, V> implements HashTableInterface<K, V> {

    private int count;
    private int capacity;
    private static final int PREDEFINED_CAPACITY = 100;
    private HashNode<K, V>[] entries;

    public HashTable() {
        entries = new HashNode[PREDEFINED_CAPACITY];
        this.capacity = PREDEFINED_CAPACITY;
        Arrays.fill(entries, null);
    }//End Constructor1

    public HashTable(int capacity) {
        entries = new HashNode[capacity];
        this.capacity = capacity;
        Arrays.fill(entries, null);
    }//End Constructor2

    public int size() {
        return count;
    }//End getSize

    public int getCapacity() {
        return capacity;
    }//End getCapacity

    private int hashCode(K key) {
        return Objects.hashCode(key);
    }//End hashCode

    private int h(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % capacity;
        index = (index < 0) ? index * - 1 : index;
        return index;
    }//End h

    private int h(K key, int i) {
        return (h(key) + i) % capacity;
    }//End h

    @Override
    public void insert(K key, V value) throws HashTableException {
        int i = 0;
        do {
            int j = h(key, i);
            if(entries[j] == null) {
                entries[j] = new HashNode<K, V>(key, value);
                count ++;
                return;
            }//End if
            i ++;
        } while(i != capacity);
        throw new HashTableException("HashTable overflow");
    }//End insert

    @Override
    public V search(K key) {
        int i = 0;
        int j = 0;
        do {
            j = h(key, i);
            if(entries[j] != null) {
                if (entries[j].getKey().equals(key))
                    return entries[j].getValue();
            }//End if
            i ++;
        } while(entries[j] != null || i != capacity);
        return null;
    }//End search

    @Override
    public boolean remove(K key) {
        if (search(key) != null) {
            int i = 0;
            do {
                int j = h(key, i);
                if(entries[j].getKey() == key) {
                    entries[j] = null;
                    count --;
                    return true;
                }//End if
                i ++;
            } while (i != capacity);
        }//End if
        return false;
    }//End remove

}//End HashTable class
