package Main;

import Controller.Controller;
import Model.Exception.MyException;
import Repository.IRepo;
import Repository.MemoryRepo;
import gui.MainView;

public class Main {
    public static void main(String[] args) throws MyException {
        IRepo repository = new MemoryRepo("log.txt");
        Controller controller = new Controller(repository);
        MainView.setController(controller);
        MainView view = new MainView();
//      Interpreter view = new View(controller);
        view.run(args);
    }
}
