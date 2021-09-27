package dataStructures.queue;

import dataStructures.node.Node;

public class Queue<E> implements QueueInterface<E> {

    private int size;
    private Node<E> front;
    private Node<E> rear;

    public Queue() {
        size = 0;
        front = null;
        rear = null;
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
    public void enqueue(E e) {
        Node<E> toEnqueue = new Node<E>(e);
        toEnqueue.setPosition(size);
        if(rear == null) {
            rear = toEnqueue;
        } else {
            rear.setPrevious(toEnqueue);
            rear = rear.getPrevious();
        }//End if/else
        size ++;
        if(size == 1)
            front = rear;
    }//End enqueue

    @Override
    public E dequeue() throws QueueException {
        if(front == null)
            throw new QueueException("Unable to dequeue the front element because the queue is empty");
        Node<E> toDequeue = front;
        front = front.getPrevious();
        size --;
        return toDequeue.getItem();
    }//End dequeue

    private void decreaseIndexes(Node<E> aux) {
        if(aux != null) {
            aux.setPosition(aux.getPosition() - 1);
            decreaseIndexes(aux.getPrevious());
        }//End if
    }//End decreaseIndexes

    @Override
    public E front() throws QueueException {
        if(front == null)
            throw new QueueException("Unable to get the front element because the queue is empty");
        return front.getItem();
    }//End front

    @Override
    public E rear() throws QueueException {
        if(rear == null)
            throw new QueueException("Unable to get the rear element because the queue is empty");
        return rear.getItem();
    }//End rear

    @Override
    public int getIndex(E e) throws QueueException {
        if(!isInQueue(e))
            throw new QueueException("Unable to get the index because the element does not exist in the queue");
        if(isEmpty())
            throw new QueueException("Unable to get the index of the element because the queue is empty");
        return getIndex(e, front);
    }//End getIndex

    private int getIndex(E e, Node<E> aux) {
        if(aux.getItem() == e)
            return aux.getPosition();
        else
            return getIndex(e, aux.getPrevious());
    }//End getIndex

    @Override
    public boolean isInQueue(E e) {
        return isInQueue(e, front);
    }//End isInList

    private boolean isInQueue(E e, Node<E> aux) {
        if(aux == null) {
            return false;
        } else {
            if(aux.getItem() == e)
                return true;
            else
                return isInQueue(e, aux.getPrevious());
        }//End if/else
    }//End

    public Queue<E> reverse() {
        Queue<E> reversed = new Queue<E>();
        Node<E> aux = front;
        while(aux != null) {
            reversed.enqueue(aux.getItem());
            aux = aux.getPrevious();
        }//End while
        return reversed;
    }//End reverse

}//End Node class
