package View;
import Controller.*;
import Model.PrgState;
import Model.Stmt.*;
import Model.Exp.*;
import Model.Type.*;
import Model.Value.*;
import Model.ADT.Stack.*;
import Model.ADT.List.*;
import Model.ADT.Dictionary.*;
import Repository.*;
import Model.Exception.MyException;

import java.util.Scanner;

public class View {
    public static void displayMenu()
    {
        System.out.println("1. Choose a program");
        System.out.println("2. Execute a program (completely)");
        System.out.println("3. Switch debugging mode (display flag)");
        System.out.println("4. Exit");
    }
    public static void displayPrograms()
    {
        System.out.println("1. int v; v=2;Print(v)");
        System.out.println("2. int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("4. int x; x=10; int y; y=5*(x-2);bool cond; cond=true;int result;\n" +
                              "(If cond Then result=y/2 Else result=y+7);Print(result)");
    }
    public static void main(String[] args) {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        IRepo repo = new MemoryRepo();
        // int v; v=2; Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        // int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),
                                new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), "*"), "+")),
                                new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)), "+")), new PrintStmt(new VarExp("b"))))));
        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        //int x; x=10; int y; y=5*(x-2);bool cond; cond=true;int result;
        //(If cond Then result=y/2 Else result=y+7);Print(result)
        IStmt ex4 = new CompStmt(
                new VarDeclStmt("x", new IntType()),
                    new CompStmt(new AssignStmt("x",
                                 new ValueExp(new IntValue(10))),
                                    new CompStmt(new VarDeclStmt("y", new IntType()),
                                    new CompStmt(new AssignStmt("y", new ArithExp(new ValueExp(new IntValue(5)), new ArithExp(new VarExp("x"),
                        new ValueExp(new IntValue(2)), "-"), "*")), new CompStmt(new VarDeclStmt("cond",
                            new BoolType()), new CompStmt(new AssignStmt("cond", new ValueExp(new BoolValue(true))),
                               new CompStmt(new VarDeclStmt("result", new IntType()),
                                       new CompStmt(
                                            new IfStmt(new VarExp("cond"),
                                                    new AssignStmt("result",
                                                                        new ArithExp(new VarExp("y"),
                                                                                     new ValueExp(new IntValue(2)),
                                                                                "/")),
                                                    new AssignStmt("result",
                                                                        new ArithExp(new VarExp("y"),
                                                                                    new ValueExp(new IntValue(7)),
                                                                                "+"))),
                                       new PrintStmt(new VarExp("result"))))))))));
        repo.add(new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1));
        repo.add(new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2));
        repo.add(new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3));
        repo.add(new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex4));
        Controller cont = new Controller(repo);
        while (running) {
            displayMenu();
            try {
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        displayPrograms();
                        int index = sc.nextInt();
                        if(index <= 0 || index > repo.size())
                            throw new MyException("Option out of range");
                        cont.changePrg(index-1);
                        break;
                    case 2:
                        cont.allStep();
                        break;
                    case 3:
                        cont.switchDisplayFlag();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        throw new MyException("Invalid option");
                }
            } catch (MyException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
