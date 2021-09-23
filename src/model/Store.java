package model;

import dataStructures.linkedlist.LinkedList;
import dataStructures.queue.Queue;

public class Store {

    private Queue<Client> clients;
    private LinkedList<Cashier> cashiers;
    private LinkedList<Shelf> shelves;

    public Store() {
        clients = new Queue<>();
    }//End Store



}//End Store class
