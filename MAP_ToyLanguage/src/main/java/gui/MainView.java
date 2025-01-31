package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import Controller.Controller;

import java.io.IOException;

public class MainView extends Application {
    static Controller controller;

    public static void setController(Controller controller) {
        MainView.controller = controller;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainWindowLoader = new FXMLLoader();

        mainWindowLoader.setLocation(getClass().getResource("MainWindow.fxml"));
        mainWindowLoader.setControllerFactory(c -> new MainWindowController(controller));

        Parent mainWindowRoot = mainWindowLoader.load();
        MainWindowController mainWindowController = mainWindowLoader.getController();

        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        stage.setTitle("Interpreter MAP 2023 - 2024");
        stage.setScene(new Scene(mainWindowRoot));
        stage.show();

        Stage secondaryStage = new Stage();
        FXMLLoader setProgramLoader = new FXMLLoader();

        setProgramLoader.setControllerFactory(c -> new SelectProgramWindowController(controller, mainWindowController));
        setProgramLoader.setLocation(getClass().getResource("SelectProgramWindow.fxml"));

        Parent setProgramRoot = setProgramLoader.load();
        SelectProgramWindowController setProgramController = setProgramLoader.getController();

        secondaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        secondaryStage.setTitle("Select program");
        secondaryStage.setScene(new Scene(setProgramRoot));
        secondaryStage.show();
    }

    public static void run(String[] args) {
        launch(args);
    }
}