package model;

import dataStructures.hashtable.HashTableException;
import dataStructures.linkedlist.LinkedList;
import dataStructures.queue.Queue;
import dataStructures.queue.QueueException;

public class Store {

    private Queue<Client> clients;
    private LinkedList<Cashier> cashiers;
    private LinkedList<Shelf> shelves;

    public Store() {
        clients = new Queue<>();
        cashiers = new LinkedList<>();
        shelves = new LinkedList<>();
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

    public boolean registerShelf(String identifier) {
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

    public boolean registerClient(String id) throws QueueException {
        if(searchClient(id) == null) {
            clients.enqueue(new Client(id));
            return true;
        } else {
            return false;
        }//End if/else
    }//End registerClient

    private VideoGame searchVideoGameInShelves(int code) {
        for(int i = 0; i < shelves.size(); i ++) {
            Shelf shelf = shelves.get(i);
            if(shelf.searchVideoGame(code) != null)
                return shelf.searchVideoGame(code);
        }//End for
        return null;
    }//End searchVideoGameInShelves

    private Client searchClient(String id) throws QueueException {
        Queue<Client> aux = clients.reverse();
        while(!aux.isEmpty()) {
            Client client = aux.dequeue();
            if(client.getId().equalsIgnoreCase(id))
                return client;
        }//End while
        return null;
    }//End searchCustomer

    public boolean addVideoGameToClient(String id, int code, int quantity) throws QueueException {
        Client client = searchClient(id);
        VideoGame aux = searchVideoGameInShelves(code);
        if(aux != null && client != null) {
            VideoGame toAdd = new VideoGame(code, quantity, aux.getShelf(), aux.getPrice());
            client.addVideoGameToList(toAdd);
            return true;
        } else {
            return false;
        }//End if/else
    }//End addVideoGameToClient

}//End Store class
