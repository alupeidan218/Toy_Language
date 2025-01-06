package View;
import Controller.*;
import Model.ExeStack.ExeStack;
import Model.FileTable.FileTable;
import Model.Heap.Heap;
import Model.Output.Output;
import Model.PrgState;
import Model.Stmt.*;
import Model.Exp.*;
import Model.SymTable.SymTable;
import Model.Type.*;
import Model.Value.*;
import Repository.*;

public class Interpreter {
    public static void main(String[] args) {
        // int v; v=2; Print(v)
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new StringValue("ababa"))), new PrintStmt(new
                        VarExp("v"))));
        PrgState prg1 = new PrgState(ex1);
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
        PrgState prg2 = new PrgState(ex2);
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
        PrgState prg3 = new PrgState(ex3);
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
        PrgState prg4 = new PrgState(ex4);
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
        PrgState prg5 = new PrgState(ex5);
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
        PrgState prg6 = new PrgState(ex6);
        IRepo repo6 = new MemoryRepo("log6.txt");
        repo6.add(prg6);
        Controller ctr6 = new Controller(repo6);
        // Ref int v; new(v,20);
        // Ref Ref int a; new(a,v);
        // print(rH(v)); print(rH(rH(a))+5)
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                        new ValueExp(new IntValue(5)), "+")))))));
        PrgState prg7 = new PrgState(ex7);
        IRepo repo7 = new MemoryRepo("log7.txt");
        repo7.add(prg7);
        Controller ctr7 = new Controller(repo7);
        // Ref int v; new(v,20);
        // Ref Ref int a; new(a,v);
        // print(v); print(a)
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        PrgState prg8 = new PrgState(ex8);
        IRepo repo8 = new MemoryRepo("log8.txt");
        repo8.add(prg8);
        Controller ctr8 = new Controller(repo8);
        // Ref int v; new(v,20);
        // print(rH(v));
        // wH(v,30);
        // print(rH(v)+5);
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(new ReadHeapExp(new VarExp("v")),
                                                new ValueExp(new IntValue(5)), "+"))))));
        PrgState prg9 = new PrgState(ex9);
        IRepo repo9 = new MemoryRepo("log9.txt");
        repo9.add(prg9);
        Controller ctr9 = new Controller(repo9);
        // int i = 0;
        // while(i < 5) print(i);
        IStmt ex10 = new CompStmt(new VarDeclStmt("i", new IntType()),
                new CompStmt(new AssignStmt("i", new ValueExp(new IntValue(6))),
                        new WhileStmt(new CompStmt(new PrintStmt(new VarExp("i")),
                                                    new AssignStmt("i", new ArithExp(new VarExp("i"),
                                                            new ValueExp(new IntValue(1)), "+"))),
                                      new RelationExp(new VarExp("i"), new ValueExp(new IntValue(5)), "<"))));
        PrgState prg10 = new PrgState(ex10);
        IRepo repo10 = new MemoryRepo("log10.txt");
        repo10.add(prg10);
        Controller ctr10 = new Controller(repo10);
        // Ref int v; new(v,20);
        // Ref Ref int a; new(a,v);
        // new(v,30); print(rH(rH(a)));
        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
        PrgState prg11 = new PrgState(ex11);
        IRepo repo11 = new MemoryRepo("log11.txt");
        repo11.add(prg11);
        Controller ctr11 = new Controller(repo11);
        // int v; Ref int a; v = 10; new(a, 22);
        // fork(wH(a, 30); v=32; print(v); print(rH(a)));
        // print(v); print(rH(a));
        IStmt ex12 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
        PrgState prg12 = new PrgState(ex12);
        IRepo repo12 = new MemoryRepo("log12.txt");
        repo12.add(prg12);
        Controller ctr12 = new Controller(repo12);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand(0, "exit"));
        menu.addCommand(new RunExampleCommand(1, ex1.toString(), ctr1));
        menu.addCommand(new RunExampleCommand(2, ex2.toString(), ctr2));
        menu.addCommand(new RunExampleCommand(3, ex3.toString(), ctr3));
        menu.addCommand(new RunExampleCommand(4, ex4.toString(), ctr4));
        menu.addCommand(new RunExampleCommand(5, ex5.toString(), ctr5));
        menu.addCommand(new RunExampleCommand(6, ex6.toString(), ctr6));
        menu.addCommand(new RunExampleCommand(7, ex7.toString(), ctr7));
        menu.addCommand(new RunExampleCommand(8, ex8.toString(), ctr8));
        menu.addCommand(new RunExampleCommand(9, ex9.toString(), ctr9));
        menu.addCommand(new RunExampleCommand(10, ex10.toString(), ctr10));
        menu.addCommand(new RunExampleCommand(11, ex11.toString(), ctr11));
        menu.addCommand(new RunExampleCommand(12, ex12.toString(), ctr12));
        menu.show();
    }
}
