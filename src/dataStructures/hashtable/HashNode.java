package dataStructures.hashtable;

public class HashNode<K, V> {

    private K key;
    private V value;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }//End HashNode

    public K getKey() {
        return key;
    }//End getKey

    public void setKey(K key) {
        this.key = key;
    }//End setKey

    public V getValue() {
        return value;
    }//End getValue

    public void setValue(V value) {
        this.value = value;
    }//End setValue

}//End HashNode class
