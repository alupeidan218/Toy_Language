/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author alupe
 */
public class Specialist implements Participant {
    private int id = -1;
    private boolean presented = false;
    public Specialist(int id)
    {
        this.id = id;
    }
    @Override public int getId()
    {
        return this.id;
    }
    @Override public void setPresented()
    {
        this.presented = true;
    }
    @Override public boolean hasPresented()
    {
        return this.presented;
    }
    @Override public String getInfo()
    {
        return "Id: " + Integer.toString(this.id) + ", Type: Specialist";
    }
}
