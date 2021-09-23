package model;

import dataStructures.hashtable.HashTableException;
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

    private boolean searchShelfIdentifier(String identifier) {
        for(int i = 0; i < shelves.size(); i ++) {
            Shelf shelf = shelves.get(i);
            if(shelf.getIdentifier().equalsIgnoreCase(identifier))
                return true;
        }//End for
        return false;
    }//End validateShelfIdentifier

    public boolean registerShelves(String identifier) {
        if(searchShelfIdentifier(identifier)) {
            return false;
        } else {
            shelves.add(new Shelf(identifier));
            return true;
        }//End if/else
    }//End registerShelves

    public boolean registerVideoGame(String shelf, int code, int quantity, double price) {
        VideoGame toAdd = new VideoGame(code, quantity, shelf, price);
        for(int i = 0; i < shelves.size(); i ++) {
            Shelf current = shelves.get(i);
            if(current.getIdentifier().equalsIgnoreCase(shelf)) {
                try {
                    current.addGame(toAdd);
                    return true;
                } catch (HashTableException e) {
                    return false;
                }//End try/catch
            }//End if
        }//End for
        return false;
    }//End registerVideoGame

}//End Store class
