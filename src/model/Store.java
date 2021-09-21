package model;

import dataStructures.queue.Queue;

public class Store {

    private Queue<Client> clients;

    public Store() {
        clients = new Queue<>();
    }//End Store

    public Queue<Client> getClients() {
        return clients;
    }//End getClients

    public void setClients(Queue<Client> clients) {
        this.clients = clients;
    }//End setClients

}//End Store class
