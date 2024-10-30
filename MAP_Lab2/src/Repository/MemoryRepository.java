/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Participant;

/**
 *
 * @author alupe
 */
public class MemoryRepository implements Repository {
    private Participant[] elems;
    private int capacity = 10;
    private int size = 0;
    public MemoryRepository()
    {
        this.elems = new Participant[this.capacity];
    }
    
    @Override public void add(Participant pAdded)
    {
        if(this.size == this.capacity)
            resize();
        this.elems[this.size++] = pAdded;
    }
    
    @Override public void remove(int id)
    {    
        for(int i = 0; i < this.size; i++)
        {
           if(this.elems[i].getId() == id){
               this.elems[i] = this.elems[this.size-1];
               this.size--;
               break;
           }
        }
    }
    
    @Override public void update(int id)
    {
        for(int i = 0; i < this.size; i++)
        {
            if(this.elems[i].getId() == id){
                this.elems[i].setPresented();
                break;
            }
        }
    }
    
    @Override public boolean inRepository(int id)
    {
        for(int i = 0; i < this.size; i++)
            if(this.elems[i].getId() == id)
                return true;
        return false;
    }
    
    @Override public Participant[] getParticipantsPresented()
    {
        Participant presented[] = new Participant[this.size];
        int j = 0;
        for(int i = 0; i < this.size; i++)
            if(this.elems[i].hasPresented())
                presented[j++] = this.elems[i];
        return presented;
    }
    
    public void resize()
    {
        this.capacity *= 2;
        Participant new_elems[] = new Participant[this.capacity];
        int i = 0;
        for (Participant elem : this.elems) {
            new_elems[i++] = elem;
        }
        this.elems = new_elems;
    }
}
