package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import data.Stats;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sudoku.main.Sudoku;

/**
 *
 * @author Otso
 */
public class SudokuUi extends Application {
    private long counter = 0;
    GridPane box;
    private Date date;
    private Timeline timeline;
    
    private void timer(Stage stage) {
        date = Calendar.getInstance().getTime();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            counter = Calendar.getInstance().getTime().getTime() - date.getTime();
            stage.setTitle(
                        "Sudoku: " + String.valueOf(TimeUnit.SECONDS.convert(counter, TimeUnit.MILLISECONDS)));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    @Override
    public void start(Stage ikkuna) {
        Stats history = new Stats();
        Sudoku sudoku = new Sudoku(38);
        sudoku.generate();

        //Set title
        ikkuna.setTitle("Sudoku");

        //Sudoku
        GridPane box = new GridPane();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                box.add(new Button(sudoku.returnCell(x, y)), x, y);
            }
        }
        box.setHgap(2);
        box.setVgap(2);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 10;" + 
                      "-fx-background-color: #32521a;" +
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 1;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: black;"
        );
        
        
        //BorderPane
        BorderPane window = new BorderPane();

        window.setCenter(box);

        Scene game = new Scene(window);

        //Start button
        Button aloita = new Button("Start");
        aloita.setOnAction((ActionEvent event) -> {
            ikkuna.setScene(game);
            timer(ikkuna);
        });

        Scene startScreen = new Scene(aloita, 200, 100);
        ikkuna.setScene(startScreen);
        ikkuna.show();
        //Stats
        HBox stats = new HBox();
        stats.setSpacing(10);
        stats.getChildren().add(new Label("Games: " + String.valueOf(history.gameStats())));
        stats.getChildren().add(new Label("Won: " + String.valueOf(history.wonStats())));
        window.setBottom(stats);

        //Buttons
        HBox topRow = new HBox();
        Button checkAnswer = new Button("Check");
        checkAnswer.setOnAction((ActionEvent check) -> {
            if (sudoku.checkAnswer() == true) {
                history.gameLost();
                history.gameWon();
            }
        });
        Button giveUp = new Button("Give up");
        topRow.getChildren().add(checkAnswer);
        topRow.getChildren().add(giveUp);
        giveUp.setOnAction((ActionEvent event) -> {
            history.gameLost();
            timeline.stop();
            ikkuna.setTitle("Sudoku");
            ikkuna.setScene(startScreen);
        });
        topRow.setSpacing(10);

        window.setTop(topRow);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
