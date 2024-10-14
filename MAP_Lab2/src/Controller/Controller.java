/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Repository.*;
import Model.*;
/**
 *
 * @author alupe
 */
public class Controller {
    private Repository repo;
    public Controller(Repository r)
    {
        this.repo = r;
    }
    
    public void addParticipant(Participant p)
    {
        if(repo.inRepository(p.getId()))
            throw new RuntimeException("Participant with this ID already exists!");
        repo.add(p);
    }
    
    public void removeParticipant(int id)
    {
        if(!repo.inRepository(id))
            throw new RuntimeException("Participant with this ID doesn't exist!");
        repo.remove(id);
    }
    
    public void updateParticipant(int id)
    {
        if(!repo.inRepository(id))
            throw new RuntimeException("Participant with this ID doesn't exist!");
        repo.update(id);
    }
    
    public String getPresented()
    {
        String presented = "";
        for(Participant p : this.repo.getParticipantsPresented())
        {
            if(p == null)
                break;
            presented += p.getInfo() + "\n";
        }
        return presented;
    }
}
