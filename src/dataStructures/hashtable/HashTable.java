package dataStructures.hashtable;

import linkedlist.LinkedList;

public class HashTable<K, V> implements HashTableInterface<K, V> {

    private int count;
    private float loadFactor;
    private LinkedList<HashNode<K, V>>[] nodes;
    private static final int PREDEFINED_ARRAY_SIZE = 123;

    public HashTable() {
        count = 0;
        nodes = new LinkedList[PREDEFINED_ARRAY_SIZE];
    }//End Constructor1

    public HashTable(int arraySize) {
        count = 0;
        nodes = new LinkedList[arraySize];
    }//End Constructor2

    private int hashFunction() {
        //To Do
        return 0;
    }//End hasFunction

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }//End isEmpty

    @Override
    public int size() {
        return count;
    }//End size

    @Override
    public void insert(K key, V value) {
        //To Do
    }//End insert

    @Override
    public void search(K key) {
        //To Do
    }//End search

    @Override
    public void delete(K key) {
        //To Do
    }//End delete

}//End HashTable class
