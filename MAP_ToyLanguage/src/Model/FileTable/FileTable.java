package Model.FileTable;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.AlreadyDeclaredException;
import Model.Exception.FileException;
import Model.Exception.FileNotFoundException;
import Model.Exception.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileTable implements IFileTable {
    MyIDictionary<String, BufferedReader> table;
    public FileTable() {
        this.table = new MyDictionary<>();
    }
    public void openFile(String name) throws MyException {
        if(table.lookup(name) == null) {
            try {
                FileReader in = new FileReader(name);
                BufferedReader br = new BufferedReader(in);
                table.put(name, br);
            } catch(IOException e)
            {
                throw new FileNotFoundException(e.getMessage());
            }
        } else{
            throw new AlreadyDeclaredException("File already exists");
        }
    }
    public void closeFile(String name) throws MyException {
        BufferedReader br = table.lookup(name);
        if(br != null) {
            try {
                br.close();
                table.remove(name);
            } catch(IOException e) {
                throw new FileException(e.getMessage());
            }
        } else {
            throw new FileNotFoundException("File does not exist");
        }
    }

    public int readFile(String name) throws MyException {
        BufferedReader br = table.lookup(name);
        if(br != null) {
            try {
                String line = br.readLine();
                if(line != null){
                    return Integer.parseInt(line);
                } else {
                    throw new FileException("End of file reached");
                }
            } catch (IOException e)
            {
                throw new FileException(e.getMessage());
            }
        } else {
            throw new FileNotFoundException("File does not exist");
        }
    }
    public List<String> getFileList(){
        return table.getKeys();
    }

    public BufferedReader lookup(String name) throws MyException {
        return table.lookup(name);
    }

    public String toString() {
        return "File table: \n" + table.toString();
    }
    public void clear() {
        table.clear();
    }
}
