package Model.FileTable;

import Model.Exception.MyException;

import java.io.BufferedReader;
import java.util.List;

public interface IFileTable {
    void openFile(String name) throws MyException;
    void closeFile(String name) throws MyException;
    int readFile(String name) throws MyException;
    String toString();
    BufferedReader lookup(String name) throws MyException;
    List<String> getFileList();
    void clear();
}
