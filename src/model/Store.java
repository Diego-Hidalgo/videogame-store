package model;

import dataStructures.hashtable.HashTableException;
import dataStructures.linkedlist.LinkedList;
import dataStructures.queue.Queue;
import dataStructures.queue.QueueException;
import dataStructures.stack.Stack;
import dataStructures.stack.StackException;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private Queue<Client> clients;
    private LinkedList<Cashier> cashiers;
    private LinkedList<Shelf> shelves;
    private Queue<Client> exitQueue;

    public Store() {
        clients = new Queue<>();
        cashiers = new LinkedList<>();
        shelves = new LinkedList<>();
        exitQueue = new Queue<>();
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

    public List<Integer> getGamesCodesAsList(int[] gamesInList) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < shelves.size(); i ++) {
            Shelf shelf = shelves.get(i);
            Object[] codes = shelf.getAllGamesCodes();
            for(Object code : codes) {
                if(!isInClientList((int) code, gamesInList))
                    list.add((Integer) code);
            }//End for
        }//End for
        return list;
    }//End getGamesCodesAsList

    public List<Client> getClientsAsList() {
        List<Client> list = new ArrayList<>();
        for(int i = 0; i < clients.size(); i ++) {
            try {
                Client client = clients.dequeue();
                list.add(client);
                clients.enqueue(client);
            } catch(QueueException ignored) {}
        }//End for
        return list;
    }//End getClientAsList

    public List<Client> getClientsExitAsList() {
        List<Client> list = new ArrayList<>();
        for(int i = 0; i < exitQueue.size(); i ++) {
            try {
                Client client = exitQueue.dequeue();
                list.add(client);
                exitQueue.enqueue(client);
            } catch(QueueException ignored) {}
        }//End for
        return list;
    }//End getClientsExitAsList

    public List<VideoGame> getClientGamesAsList(Client client) throws QueueException {
        List<VideoGame> list = new ArrayList<>();
        LinkedList<VideoGame> clientList = searchClient(client.getId()).getGames();
        for(int i = 0; i < clientList.size(); i ++) {
            list.add(clientList.get(i));
        }//End for
        return list;
    }//End getClientGamesAsList

    public List<VideoGame> getClientShoppingCartAsList(Client client) throws QueueException {
        List<VideoGame> list = new ArrayList<>();
        Stack<VideoGame> shoppingCart = searchClient(client.getId()).getCart().reverse().reverse();
        while(!shoppingCart.isEmpty()) {
            try {
                list.add(shoppingCart.pop());
            } catch(StackException ignored) {}
        }//End while
        return list;
    }//End getClientShoppingCartAsList

    public List<VideoGame> getClientShoppingBagAsList(Client client) throws QueueException {
        List<VideoGame> list = new ArrayList<>();
        Stack<VideoGame> shoppingBag = searchClient(client.getId()).getBag().reverse().reverse();
        while(!shoppingBag.isEmpty()) {
            try {
                list.add(shoppingBag.pop());
            } catch(StackException ignored) {}
        }//End while
        return list;
    }//End getClientShoppingBagAsList

    public boolean allClientsSorted() throws QueueException {
        Queue<Client> aux = clients.reverse().reverse();
        while(!aux.isEmpty()) {
            Client client = aux.dequeue();
            if(!client.getSorted())
                return false;
        }//End while
        return true;
    }//End allClientsSorted

    private boolean isInClientList(int value, int[] array) {
        for(int j : array) {
            if(j == value)
                return true;
        }//End for
        return false;
    }//End isInClientList

    public int getRegisteredGamesAmount() {
        int amount = 0;
        for(int i = 0; i < shelves.size(); i ++) {
            Shelf shelf = shelves.get(i);
            amount += shelf.getVideoGames().size();
        }//End for
        return amount;
    }//End getRegisteredGamesAmount

    public String getVideoGameInfo(int id) {
        VideoGame game = searchVideoGameInShelves(id);
        return game.getInfo();
    }//End getVideoGameInfo

    public int getVideoGameQuantity(int code) {
        VideoGame game = searchVideoGameInShelves(code);
        if(game != null)
            return game.getQuantity();
        else
            return -1;
    }//End getVideoGameQuantity

    public int getClientsAmount() {
        return clients.size();
    }//End getClientsAmount

    public void registerCashiers(int amount) {
        for(int i = 0; i < amount; i ++) {
            cashiers.add(new Cashier());
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
        if(searchVideoGameInShelves(code) != null)
            return false;
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

    public Client searchClient(String id) throws QueueException {
        Queue<Client> aux = clients.reverse().reverse();
        while(!aux.isEmpty()) {
            Client client = aux.dequeue();
            if(client.getId().equalsIgnoreCase(id))
                return client;
        }//End while
        return null;
    }//End searchCustomer

    public boolean registerClient(String id) throws QueueException {
        if(searchClient(id) == null) {
            Client client = new Client(id);
            client.setPosition(clients.size() + 1);
            clients.enqueue(client);
            return true;
        } else {
            return false;
        }//End if/else
    }//End registerClient

    private VideoGame searchVideoGameInShelves(int code) {
        for(int i = 0; i < shelves.size(); i ++) {
            Shelf shelf = shelves.get(i);
            VideoGame game = shelf.searchVideoGame(code);
            if(game != null)
                return game;
        }//End for
        return null;
    }//End searchVideoGameInShelves

    public boolean addVideoGameToClient(String id, int code, int quantity) throws QueueException {
        Client client = searchClient(id);
        VideoGame aux = searchVideoGameInShelves(code);
        if(aux != null && client != null && !client.searchGameInList(aux)) {
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
            client.setTime(clients.getIndex(client) + 1);
            client.setSorted(true);
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

    private <E extends Comparable<E>> void insertionSort(LinkedList<E> list) {
        for(int i = 1; i < list.size(); i ++) {
            E key = list.get(i);
            int j = i - 1;
            while(j >= 0 && list.get(j).compareTo(key) > 0) {
                list.setElement(j + 1, list.get(j));
                j = j - 1;
            }//End while
            list.setElement(j + 1, key);
        }//End for
    }//End insertionSort

    private <E extends Comparable<E>> void bubbleSort(LinkedList<E> list) {
        for(int i = 0; i < list.size(); i ++) {
            for(int j = 1; j < (list.size() - i); j ++) {
                if(list.get(j - 1).compareTo(list.get(j)) > 0) {
                    E aux = list.get(j - 1);
                    list.setElement(j - 1, list.get(j));
                    list.setElement(j, aux);
                }//End if
            }//End for
        }//End for
    }//End bubbleSort

    public void pickUpGames() {
        int temp = clients.size();
        for (int i = 0; i < temp; i++) {
            try {
                pickUpGames(clients.front());
                clients.enqueue(clients.dequeue());
            } catch(QueueException ignored) {}
        }//End for
        setCheckOutQueue();
    }//End nextClientPickUpGames

    private void pickUpGames(Client client) {
    	for (int i = 0; i < client.getGames().size(); i++) {
    	    int amount = client.getGames().get(i).getQuantity();
    		VideoGame game = searchVideoGameInShelves(client.getGames().get(i).getCode());
    		while(game.getQuantity() > 0 && amount > 0) {
    			game.setQuantity(game.getQuantity() - 1);
    			VideoGame toPush = new VideoGame(game.getCode(), game.getName(), 1, game.getShelf(), game.getPrice());
    			client.getCart().push(toPush);
    			client.setTime(client.getTime() + 1);
    			-- amount;
    		}//End if
		}//End for
    }//End pickUpGames

    private void setCheckOutQueue() {
        LinkedList<Client> aux = new LinkedList<Client>();
        int size = clients.size();
        for(int i = 0; i < size; i ++) {
            try {
                aux.add(clients.dequeue());
            } catch(QueueException ignored) {}
        }//End for
        insertionSort(aux);
        for(int i = 0; i < aux.size(); i ++) {
            Client client = aux.get(i);
            client.setPosition(clients.size() + 1);
            clients.enqueue(client);
        }//End for
    }//End checkOutQueue

    public void checkOut() {
        int temp = clients.size();
        int aux = clients.size();
        while(exitQueue.size() != temp) {
            while(!cashiersAreFull() && aux > 0) {
                try {
                    Client client = clients.dequeue();
                    assignClientToCashier(client);
                    clients.enqueue(client);
                    aux --;
                } catch (QueueException ignored) {}
            }//End while
            addGamesToBag();
        }//End while
    }//End checkOut

    private boolean cashiersAreFull() {
        for(int i = 0; i < cashiers.size(); i ++) {
            Cashier cashier = cashiers.get(i);
            if(!cashier.getInUse())
                return false;
        }//End for
        return true;
    }//End cashiersAreFull

    private void assignClientToCashier(Client toAssign) {
        for(int i = 0; i < cashiers.size(); i ++) {
            Cashier cashier = cashiers.get(i);
            if(!cashier.getInUse()) {
                cashier.setCurrent(toAssign);
                cashier.setInUse(true);
                return;
            }//End if
        }//End for
    }//End assignClientsToCashiers

    private void addGamesToBag() {
        for(int i = 0; i < cashiers.size(); i ++) {
            Cashier cashier = cashiers.get(i);
            if(cashier.getInUse()) {
                Client client = cashier.getCurrent();
                Stack<VideoGame> cart = client.getCart();
                try {
                    VideoGame game = cart.pop();
                    client.getBag().push(game);
                    client.setTotal(client.getTotal() + game.getPrice());
                } catch (StackException ignored) {}
                if(cart.isEmpty()) {
                    client.setPosition(exitQueue.size() + 1);
                    exitQueue.enqueue(client);
                    cashier.setInUse(false);
                    cashier.setCurrent(null);
                }//End if
            }//End if
        }//End for
    }//End addGamesToBag

    public String exitOrder() throws QueueException {
        String info = "";
        int size = exitQueue.size();
        for(int i = 0; i < size; i ++) {
            info +=  "\n[" + (i+1) + "]" + exitQueue.dequeue();
        }
        return info;
    }

}//End Store class
