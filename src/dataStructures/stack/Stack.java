package stack;

import node.Node;

public class Stack<E> implements StackInterface<E> {

    private int size;
    private Node<E> peek;
    private Node<E> bottom;

    public Stack() {
        size = 0;
        peek = null;
        bottom = null;
    }//End Constructor

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }//End isEmpty

    @Override
    public int size() {
        return size;
    }//End size

    @Override
    public void push(E e) {
        Node<E> toPush = new Node<E>(e);
        toPush.setNext(peek);
        peek = toPush;
        size ++;
        if(size == 1)
            bottom = peek;
    }//End push

    @Override
    public E pop() throws StackException {
        if(peek == null)
            throw new StackException("Unable to pop the top element because the stack is empty");
        E toPop = peek.getItem();
        peek = peek.getNext();
        if(peek == null)
            bottom = null;
        size --;
        return toPop;
    }//End pop

    @Override
    public E top() throws StackException {
        if(peek == null)
            throw new StackException("Unable to get the top element because the stack is empty");
        return peek.getItem();
    }//End top

    @Override
    public E peek() throws StackException {
        if(bottom == null)
            throw new StackException("Unable to get the peek element because the stack is empty");
        return bottom.getItem();
    }//End peek

    public Stack<E> reverse() {
        Stack<E> reverse = new Stack<E>();
        Node<E> aux = peek;
        while(aux != null) {
            reverse.push(aux.getItem());
            aux = aux.getNext();
        }//End while
        return  reverse;
    }//End reverse

}//End Stack class
