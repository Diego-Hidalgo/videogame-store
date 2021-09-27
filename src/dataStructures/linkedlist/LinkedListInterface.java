package dataStructures.linkedlist;

public interface LinkedListInterface<E> {

    public boolean isEmpty();

    public int size();

    public void add(int index, E e);

    public void add(E e);

    public E remove(int index);

    public void remove(E e) throws LinkedListException;

    public E get(int index);

    public int getIndex(E e) throws LinkedListException;

    public boolean isInList(E e);

}//End LinkedListInterface interface
