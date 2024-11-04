package Repository;
import java.io.*;
import java.util.ArrayList;

import Model.Exception.MyException;
import Model.PrgState;
import java.util.Scanner;

public class MemoryRepo implements IRepo {
    private final ArrayList<PrgState> elems;
    private final String logFilePath;
    int current = 0;
    public MemoryRepo(String logFilePath) {
        this.logFilePath = logFilePath;
        elems = new ArrayList<>();
    }
    @Override
    public void add(PrgState elem) {
        elems.add(elem);
    }
    @Override
    public boolean remove(PrgState elem) {
        return elems.remove(elem);
    }
    @Override
    public boolean isEmpty()
    {
        return elems.isEmpty();
    }
    @Override
    public int size(){
        return elems.size();
    }
    @Override
    public PrgState getCrtPrg()
    {
        return elems.get(current);
    }
    @Override
    public void setCrt(int index){
        current = index;
    }
    @Override
    public void logPrgStateExec() throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(elems.get(current).toString());
            logFile.close();
        } catch(IOException e)
        {
            throw new MyException(e.getMessage());
        }
    }
}
