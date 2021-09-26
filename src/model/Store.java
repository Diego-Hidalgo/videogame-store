package model;

import dataStructures.hashtable.HashTableException;
import dataStructures.linkedlist.LinkedList;
import dataStructures.queue.Queue;
import dataStructures.queue.QueueException;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private Queue<Client> clients;
    private LinkedList<Cashier> cashiers;
    private LinkedList<Shelf> shelves;

    public Store() {
        clients = new Queue<>();
        cashiers = new LinkedList<>();
        shelves = new LinkedList<>();
    }//End Store

    public int getShelvesSize() {
        return shelves.size();
    }//End getShelvesSize

    public List<String> getShelvesIdAsList() {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < shelves.size(); i ++) {
            list.add(shelves.get(i).getIdentifier());
        }//End for
        return list;
    }//End getShelvesAsList

    public int getRegisteredGamesAmount() {
        int amount = 0;
        for(int i = 0; i < shelves.size(); i ++) {
            Shelf shelf = shelves.get(i);
            amount += shelf.getVideoGames().size();
        }//End for
        return amount;
    }//End getRegisteredGamesAmount

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

    public boolean registerVideoGame(String shelf, int code, String name, int quantity, double price) {
        VideoGame toAdd = new VideoGame(code, name, quantity, shelf, price);
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

    private Client searchClient(String id) throws QueueException {
        Queue<Client> aux = clients.reverse();
        while(!aux.isEmpty()) {
            Client client = aux.dequeue();
            if(client.getId().equalsIgnoreCase(id))
                return client;
        }//End while
        return null;
    }//End searchCustomer

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

    public boolean addVideoGameToClient(String id, int code, int quantity) throws QueueException {
        Client client = searchClient(id);
        VideoGame aux = searchVideoGameInShelves(code);
        if(aux != null && client != null) {
            VideoGame toAdd = new VideoGame(code, aux.getName(), quantity, aux.getShelf(), aux.getPrice());
            client.addVideoGameToList(toAdd);
            return true;
        } else {
            return false;
        }//End if/else
    }//End addVideoGameToClient

    public void sortClientList(int option, String id) throws QueueException {
        Client client = searchClient(id);
        if(client != null) {
            switch(option) {
                case 1:
                    insertionSort(client.getGames());
                    break;
                case 2:
                    bubbleSort(client.getGames());
                    break;
            }//End switch
        }//End if
    }//End sortClientList

    private void insertionSort(LinkedList<VideoGame> list) {
        for(int i = 1; i < list.size(); i ++) {
            VideoGame key = list.get(i);
            int j = i - 1;
            while(j >= 0 && list.get(j).compareTo(key) > 0) {
                list.setElement(j + 1, list.get(j));
                j = j - 1;
            }//End while
            list.setElement(j + 1, key);
        }//End for
    }//End insertionSort

    private void bubbleSort(LinkedList<VideoGame> list) {
        for(int i = 0; i < list.size(); i ++) {
            for(int j = 1; j < (list.size() - i); j ++) {
                if(list.get(j - 1).compareTo(list.get(j)) > 0) {
                    VideoGame aux = list.get(j - 1);
                    list.setElement(j - 1, list.get(j));
                    list.setElement(j, aux);
                }//End if
            }//End for
        }//End for
    }//End bubbleSort

    public void pickUpGames(Client client) {
    	VideoGame game = null;
    	for (int i = 0; i < client.getGames().size(); i++) {
    		game = searchVideoGameInShelves(client.getGames().get(i).getCode());
    		if(game.getQuantity() != 0) {
    			searchVideoGameInShelves(client.getGames().get(i).getCode()).setQuantity(game.getQuantity()-1);
    			client.getShoppingCart().push(game);
    		}//End if
		}//End for
    }//End pickUpGames
    
    public void nextClientPickUpGames(Queue<Client> clients) throws QueueException {
    	int temp = clients.size();
    	for (int i = 0; i < temp; i++) {
			pickUpGames(clients.front());
			clients.enqueue(clients.dequeue());
		}//End for
    }//End nextClientPickUpGames

}//End Store class
