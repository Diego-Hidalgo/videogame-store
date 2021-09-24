package dataStructures.stack;

import dataStructures.node.Node;

public class Stack<E> implements StackInterface<E> {

    private int size;
    private Node<E> top;
    private Node<E> bottom;

    public Stack() {
        size = 0;
        top = null;
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
        toPush.setNext(top);
        top = toPush;
        size ++;
        if(size == 1)
            bottom = top;
    }//End push

    @Override
    public E pop() throws StackException {
        if(top == null)
            throw new StackException("Unable to pop the peek element because the stack is empty");
        E toPop = top.getItem();
        top = top.getNext();
        if(top == null)
            bottom = null;
        size --;
        return toPop;
    }//End pop

    @Override
    public E peek() throws StackException {
        if(top == null)
            throw new StackException("Unable to get the peek element because the stack is empty");
        return top.getItem();
    }//End peek

    @Override
    public E bottom() throws StackException {
        if(bottom == null)
            throw new StackException("Unable to get the bottom element because the stack is empty");
        return bottom.getItem();
    }//End peek

    public Stack<E> reverse() {
        Stack<E> reverse = new Stack<E>();
        Node<E> aux = top;
        while(aux != null) {
            reverse.push(aux.getItem());
            aux = aux.getNext();
        }//End while
        return  reverse;
    }//End reverse

}//End Stack class
