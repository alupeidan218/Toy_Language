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
public interface Repository {
    public void add(Participant p);
    public void remove(int id);
    public void update(int id);
    public boolean inRepository(int id);
    public Participant[] getParticipantsPresented();
}
