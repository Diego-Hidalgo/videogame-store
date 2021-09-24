package dataStructures.stack;

public interface StackInterface<E> {

    public boolean isEmpty();

    public int size();

    public void push(E e);

    public E pop() throws StackException;

    public E peek() throws StackException;

    public E bottom() throws StackException;

}//End StackInterface interface
