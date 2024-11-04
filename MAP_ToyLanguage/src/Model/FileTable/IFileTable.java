package Model.FileTable;

import Model.Exception.MyException;

import java.io.BufferedReader;
import java.util.List;

public interface IFileTable {
    public void openFile(String name) throws MyException;
    public void closeFile(String name) throws MyException;
    public int readFile(String name) throws MyException;
    public String toString();
    public BufferedReader lookup(String name) throws MyException;
    public List<String> getFileList();
    public void clear();
}
