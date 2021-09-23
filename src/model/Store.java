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

    public void registerCashiers(int amount) {
        for(int i = 0; i < amount; i ++) {
            cashiers.add(new Cashier(false));
        }//End for
    }//End registerCashiers

}//End Store class
