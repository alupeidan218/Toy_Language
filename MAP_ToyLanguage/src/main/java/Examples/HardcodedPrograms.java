package Examples;

import Controller.Controller;
import Model.Exp.*;
import Model.PrgState;
import Model.Stmt.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepo;
import Repository.MemoryRepo;

import java.util.ArrayList;
import java.util.List;

public class HardcodedPrograms {
    public static final List<IStmt> hardcodedPrograms = new ArrayList<IStmt>(List.of(
            new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new StringValue("ababa"))), new PrintStmt(new
                            VarExp("v")))),
    // int a;int b; a=2+3*5;b=a+1;Print(b)
            new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                    new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),
                            new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), "*"), "+")),
                            new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new
                                    IntValue(1)), "+")), new PrintStmt(new VarExp("b")))))),
    //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
    new CompStmt(new VarDeclStmt("a", new BoolType()),
            new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                    IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                    VarExp("v")))))),
    //int x; x=10; int y; y=5*(x-2);bool cond; cond=true;int result;
    //(If cond Then result=y/2 Else result=y+7);Print(result)
    new CompStmt(
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
                                                    new PrintStmt(new VarExp("result")))))))))),

    // string varf;
    // varf = "test.in";
    // openRFile(varf);
    // int varc;
    // readFile(varf, varc); print(varc);
    // readFile(varf, varc); print(varc);
    // closeRFile(varf);
    new CompStmt(new VarDeclStmt("varf", new StringType()),
            new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\alupe\\Documents\\GitHub\\MAP_Laboratory\\MAP_ToyLanguage\\test.in"))),
                    new CompStmt(new OpenRStmt(new VarExp("varf")),
                            new CompStmt(new VarDeclStmt("varc", new IntType()),
                                    new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                    new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                            new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                    new CloseRFileStmt(new VarExp("varf")))))))))),

    // int a = 3; int b = 5;
    // bool var_cond = a < b;
    // print(var_cond)
    new CompStmt(new VarDeclStmt("a", new IntType()),
            new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(3))),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))),
                                    new CompStmt(new VarDeclStmt("var_cond", new BoolType()),
                                            new CompStmt(new AssignStmt("var_cond", new RelationExp(new VarExp("a"), new VarExp("b"), "<")),
                                                    new PrintStmt(new VarExp("var_cond")))))))),

    // Ref int v; new(v,20);
    // Ref Ref int a; new(a,v);
    // print(rH(v)); print(rH(rH(a))+5)
    new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                            new CompStmt(new NewStmt("a", new VarExp("v")),
                                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                            new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                    new ValueExp(new IntValue(5)), "+"))))))),

    // Ref int v; new(v,20);
    // Ref Ref int a; new(a,v);
    // print(v); print(a)
    new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                            new CompStmt(new NewStmt("a", new VarExp("v")),
                                    new CompStmt(new PrintStmt(new VarExp("v")),
                                            new PrintStmt(new VarExp("a"))))))),

    // Ref int v; new(v,20);
    // print(rH(v));
    // wH(v,30);
    // print(rH(v)+5);
    new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                            new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                    new PrintStmt(new ArithExp(new ReadHeapExp(new VarExp("v")),
                                            new ValueExp(new IntValue(5)), "+")))))),

    // int i = 0;
    // while(i < 5) print(i);
    new CompStmt(new VarDeclStmt("i", new IntType()),
            new CompStmt(new AssignStmt("i", new ValueExp(new IntValue(6))),
                    new WhileStmt(new CompStmt(new PrintStmt(new VarExp("i")),
                            new AssignStmt("i", new ArithExp(new VarExp("i"),
                                    new ValueExp(new IntValue(1)), "+"))),
                            new RelationExp(new VarExp("i"), new ValueExp(new IntValue(5)), "<")))),

    // Ref int v; new(v,20);
    // Ref Ref int a; new(a,v);
    // new(v,30); print(rH(rH(a)));
    new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                            new CompStmt(new NewStmt("a", new VarExp("v")),
                                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                            new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))))))))),

    // int v; Ref int a; v = 10; new(a, 22);
    // fork(wH(a, 30); v=32; print(v); print(rH(a)));
    // print(v); print(rH(a));
    new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                            new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                    new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                    new CompStmt(new PrintStmt(new VarExp("v")),
                                                            new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                            new CompStmt(new PrintStmt(new VarExp("v")),
                                                    new PrintStmt(new ReadHeapExp(new VarExp("a")))))))))

    ));
}
