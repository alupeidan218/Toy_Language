package View;
import Controller.*;
import Model.ExeStack.ExeStack;
import Model.FileTable.FileTable;
import Model.Output.Output;
import Model.PrgState;
import Model.Stmt.*;
import Model.Exp.*;
import Model.SymTable.SymTable;
import Model.Type.*;
import Model.Value.*;
import Model.ADT.Stack.*;
import Model.ADT.List.*;
import Model.ADT.Dictionary.*;
import Repository.*;
import Model.Exception.MyException;

import java.util.Scanner;

public class
Interpreter {
    public static void main(String[] args) {
        // int v; v=2; Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        PrgState prg1 = new PrgState(new ExeStack(), new SymTable(),
        new Output(), new FileTable(), ex1);
        IRepo repo1 = new MemoryRepo("log1.txt");
        repo1.add(prg1);
        Controller ctr1 = new Controller(repo1);
        // int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),
                                new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), "*"), "+")),
                                new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)), "+")), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new ExeStack(), new SymTable(),
                new Output(), new FileTable(), ex2);
        IRepo repo2 = new MemoryRepo("log2.txt");
        repo2.add(prg2);
        Controller ctr2 = new Controller(repo2);
        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        PrgState prg3 = new PrgState(new ExeStack(), new SymTable(),
                new Output(), new FileTable(), ex3);
        IRepo repo3 = new MemoryRepo("log3.txt");
        repo3.add(prg3);
        Controller ctr3 = new Controller(repo3);
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
        PrgState prg4 = new PrgState(new ExeStack(), new SymTable(),
                new Output(), new FileTable(), ex4);
        IRepo repo4 = new MemoryRepo("log4.txt");
        repo4.add(prg4);
        Controller ctr4 = new Controller(repo4);
        // string varf;
        // varf = "test.in";
        // openRFile(varf);
        // int varc;
        // readFile(varf, varc); print(varc);
        // readFile(varf, varc); print(varc);
        // closeRFile(varf);
        IStmt ex5 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\alupe\\Documents\\GitHub\\MAP_Laboratory\\MAP_ToyLanguage\\test.in"))),
                        new CompStmt(new OpenRStmt(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFileStmt(new VarExp("varf"))))))))));
        PrgState prg5 = new PrgState(new ExeStack(), new SymTable(),
                new Output(), new FileTable(), ex5);
        IRepo repo5 = new MemoryRepo("log5.txt");
        repo5.add(prg5);
        Controller ctr5 = new Controller(repo5);
        // int a = 3; int b = 5;
        // bool var_cond = a < b;
        // print(var_cond)
        IStmt ex6 = new CompStmt(new VarDeclStmt("a", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(3))),
                                new CompStmt(new VarDeclStmt("b", new IntType()),
                                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))),
                                                new CompStmt(new VarDeclStmt("var_cond", new BoolType()),
                                                        new CompStmt(new AssignStmt("var_cond", new RelationExp(new VarExp("a"), new VarExp("b"), "<")),
                                                                new PrintStmt(new VarExp("var_cond"))))))));
        PrgState prg6 = new PrgState(new ExeStack(), new SymTable(),
                new Output(), new FileTable(), ex6);
        IRepo repo6 = new MemoryRepo("log6.txt");
        repo6.add(prg6);
        Controller ctr6 = new Controller(repo6);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExampleCommand("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExampleCommand("6", ex6.toString(), ctr6));
        menu.show();
    }
}
