package dataStructures.stack;

public interface StackInterface<E> {

    public boolean isEmpty();

    public int size();

    public void push(E e);

    public E pop() throws StackException;

    public E peek() throws StackException;

    public E bottom() throws StackException;

    public int getIndex(E e) throws StackException;

    public boolean isInStack(E e) throws StackException;

}//End StackInterface interface
