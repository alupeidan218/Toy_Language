/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;
import Repository.*;
import Controller.*;
import Model.*;
/**
 *
 * @author alupe
 *  La o conferinta participa profesori, studenti si 
 *  specialisti din industrie. Sa se afiseze toti participantii
 *  care au prezentat o lucrare.
 */
public class View {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Repository repo = new MemoryRepository();
        repo.add(new Student(101));
        repo.add(new Student(113));
        repo.add(new Teacher(335));
        repo.add(new Specialist(298));
        Controller cont = new Controller(repo);
        cont.updateParticipant(101);
        cont.updateParticipant(335);
        System.out.println(cont.getPresented());
    }
}
