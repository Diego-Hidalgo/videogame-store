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
        toPush.setPosition(size);
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

    @Override
    public int getIndex(E e) throws StackException {
        if(!isInStack(e))
            throw new StackException("Unable to get the index because the element does not exist in the stack");
        if(isEmpty())
            throw new StackException("Unable to get the index because the stack is empty");
        return getIndex(e, top);
    }//End getIndex

    private int getIndex(E e, Node<E> aux) {
        if(aux.getItem() == e)
            return aux.getPosition();
        else
            return getIndex(e, aux.getNext());
    }//End getIndex

    @Override
    public boolean isInStack(E e) {
        return isInStack(e, top);
    }//End isInList

    private boolean isInStack(E e, Node<E> aux) {
        if(aux == null) {
            return false;
        } else {
            if(aux.getItem() == e)
                return true;
            else
                return isInStack(e, aux.getNext());
        }//End if/else
    }//End isInList

    public Stack<E> reverse() {
        Stack<E> reverse = new Stack<E>();
        Node<E> aux = top;
        while(aux != null) {
            reverse.push(aux.getItem());
            aux = aux.getNext();
        }//End while
        return  reverse;
    }//End reverse

    @Override
    public String toString() {
        String info = "";
        Node<E> aux = top;
        while(aux != null) {
            info += "\n" + aux.getItem().toString();
            aux = aux.getNext();
        }//End while
        return info;
    }//End toString

}//End Stack class
