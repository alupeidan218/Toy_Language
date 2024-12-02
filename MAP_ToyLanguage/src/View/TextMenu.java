package View;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TextMenu {
    private final Map<Integer, Command> commands;
    public TextMenu() {
        commands = new TreeMap<>();
    }
    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }
    private void printMenu() {
        for(Command com : commands.values()) {
            String line = String.format("%4d: %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }
    public void show() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                printMenu();
                System.out.println("Input the option: ");
                Integer key = Integer.valueOf(scanner.nextLine());
                Command com = commands.get(key);
                if (com == null) {
                    System.out.println("Invalid option");
                    continue;
                }
                com.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
