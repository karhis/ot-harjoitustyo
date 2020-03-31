package sudoku;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 *
 * @author Otso
 */
public class SudokuUi extends Application {
    @Override
    public void start(Stage ikkuna) {
        Button aloita = new Button("Aloita peli");
        
        FlowPane nappiryhma = new FlowPane();
        nappiryhma.getChildren().add(aloita);
        Scene aloitus = new Scene(nappiryhma);
     ikkuna.setScene(aloitus);
     ikkuna.setTitle("Sudoku");
     ikkuna.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    
}
