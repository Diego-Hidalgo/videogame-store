package dataStructures.linkedlist;

import node.Node;

public class LinkedList<E> implements LinkedListInterface<E> {

    private int size;
    private Node<E> head;
    private Node<E> last;

    public LinkedList() {
        size = 0;
        head = null;
        last = null;
    }//End Constructor

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }//End isEmpty

    @Override
    public int size() {
        return size;
    }//End size

    public E getFirst() throws LinkedListException {
        if(head == null)
            throw new LinkedListException("Unable to get the first element because the list is empty");
        return head.getItem();
    }//End getFirst

    public E getLast() throws LinkedListException {
        if(last == null)
            throw new LinkedListException("Unable to get the last element because the list is empty");
        return last.getItem();
    }//End getLast

    private void increaseIndexes(Node<E> aux) {
        if(aux != null) {
            aux.setPosition(aux.getPosition() + 1);
            increaseIndexes(aux.getNext());
        }//End if
    }//End increaseIndexes

    public void addFirst(E e) {
        add(0, e);
    }//End addFirst

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node<E> toAdd = new Node<E>(e);
        toAdd.setPosition(index);
        if(index == 0) {
            toAdd.setNext(head);
            head.setPrevious(toAdd);
            head = toAdd;
            increaseIndexes(head.getNext());
        } else if(index == size) {
            add(toAdd, head);
        } else {
            add(index, toAdd, head);
        }//ENd if/else
        size ++;
    }//End add

    private void add(int index, Node<E> toAdd, Node<E> aux) {
        if(aux.getPosition() == index) {
            toAdd.setPrevious(aux.getPrevious());
            aux.getPrevious().setNext(toAdd);
            aux.setPrevious(toAdd);
            toAdd.setNext(aux);
            increaseIndexes(aux);
        } else {
            add(index, toAdd, aux.getNext());
        }//End if/else
    }//End add

    @Override
    public void add(E e) {
        Node<E> toAdd = new Node<E>(e);
        toAdd.setPosition(size);
        last = toAdd;
        if(head == null) {
            head = toAdd;
        } else {
            add(toAdd, head);
        }//End if/else
        size ++;
    }//End add

    private void add(Node<E> toAdd, Node<E> aux) {
        if(aux.getNext() == null) {
            aux.setNext(toAdd);
            toAdd.setPrevious(aux);
        } else {
            add(toAdd, aux.getNext());
        }//End if/else
    }//End add

    private void reduceIndexes(Node<E> aux) {
        if(aux != null) {
            aux.setPosition(aux.getPosition() - 1);
            reduceIndexes(aux.getNext());
        }//End if
    }//End reduceIndexes

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        size --;
        if(index == 0) {
            Node<E> removed = head;
            head = head.getNext();
            head.setPrevious(null);
            reduceIndexes(head);
            return removed.getItem();
        } else {
            return remove(index, head);
        }//End if/else
    }//End remove

    private E remove(int index, Node<E> aux) {
        if(aux.getPosition() == index) {
            if(aux.getPrevious() != null)
                aux.getPrevious().setNext(aux.getNext());
            if(aux.getNext() != null)
                aux.getNext().setPrevious(aux.getPrevious());
            reduceIndexes(aux.getNext());
            return aux.getItem();
        } else {
            return remove(index, aux.getNext());
        }//End if/else
    }//End remove

    @Override
    public void remove(E e) throws LinkedListException {
        if(head.getItem() == e) {
            head = head.getNext();
            head.setPrevious(null);
            reduceIndexes(head);
            size --;
        } else {
            if (isInList(e)) {
                size--;
                remove(e, head);
            } else {
                throw new LinkedListException("The element to remove does not exist in the linked list");
            }//End if/else
        }//End if/else
    }//End remove

    private void remove(E toRemove, Node<E> aux) {
        if(aux.getItem() == toRemove) {
            if(aux.getPrevious() != null)
                aux.getPrevious().setNext(aux.getNext());
            if(aux.getNext() != null)
                aux.getNext().setPrevious(aux.getPrevious());
            reduceIndexes(aux.getNext());
        } else {
            remove(toRemove, aux.getNext());
        }//End if/else
    }//End remove

    @Override
    public E get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return get(index, head);
    }//End get

    private E get(int index, Node<E> aux) {
        if(aux.getPosition() == index)
            return aux.getItem();
        else
            return get(index, aux.getNext());
    }//End get

    @Override
    public boolean isInList(E e) {
        return isInList(e, head);
    }//End isInList

    private boolean isInList(E e, Node<E> aux) {
        if(aux == null) {
            return false;
        } else {
            if(aux.getItem() == e)
                return true;
            else
                return isInList(e, aux.getNext());
        }//End if/else
    }//End isInList

}//End LinkedList class
