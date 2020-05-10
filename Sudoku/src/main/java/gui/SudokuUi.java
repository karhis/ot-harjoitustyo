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
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
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
    final int[][] array = new int[9][9];
    private HashMap<Integer, Button> buttons;

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

    public void generateBox(GridPane box, Sudoku sudoku) {

        int iterator = 0;
        for (int x = 0; x < 9; x++) {

            for (int y = 0; y < 9; y++) {
                Button r = new Button(sudoku.returnCell(x, y));
                r.setId(String.valueOf(iterator));

                if ("  ".equals(sudoku.returnCell(x, y))) {
                    
                    r.setOnMouseClicked((MouseEvent t) -> {
                        if ("  ".equals(r.getText()) || "9".equals(r.getText())) {
                            r.setText("1");

                        } else {
                            r.setText(String.valueOf(Integer.valueOf(r.getText()) + 1));
                        }
                    });
                } else {
                    r.setStyle("-fx-background-color: #a19e9d");
                }


                buttons.put(iterator, r);
                iterator++;
                box.add(r, x, y);
            }
        }
        box.setHgap(2);
        box.setVgap(2);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 10;"
                + "-fx-background-color: #32521a;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 1;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: black;"
        );
    }
    
    public void statsBox(BorderPane window, Stats history) {
        //Stats
        HBox stats = new HBox();
        stats.setSpacing(10);
        stats.getChildren().add(new Label("Games: " + String.valueOf(history.gameStats())));
        stats.getChildren().add(new Label("Won: " + String.valueOf(history.wonStats())));
        window.setBottom(stats);  
        
    }

    @Override
    public void start(Stage screen) throws IOException {

        Stats history = new Stats();
        history.readWins();
        history.readGames();
        history.getStats();
        Sudoku sudoku = new Sudoku(38);
        sudoku.generate();
        int iterator = 0;
        buttons = new HashMap<Integer, Button>();

        //Set title
        screen.setTitle("Sudoku");

        //Sudoku
        GridPane box = new GridPane();
        generateBox(box, sudoku);

        //BorderPane
        BorderPane window = new BorderPane();
        window.setCenter(box);

        Scene game = new Scene(window);
        //Start button
        Button start = new Button("Start");
        start.setOnAction((ActionEvent event) -> {
            statsBox(window, history);
            screen.setScene(game);
            timer(screen);
        });

        Scene startScreen = new Scene(start, 200, 100);
        screen.setScene(startScreen);
        screen.show();
        

        //Buttons
        HBox topRow = new HBox();

        Button checkAnswer = new Button("Check");
        checkAnswer.setOnAction((ActionEvent check) -> {
            int z = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if ("  ".equals(buttons.get(z).getText())) {
                        sudoku.setNumber(i, j, 0);
                    } else {
                        sudoku.setNumber(i, j, Integer.valueOf(buttons.get(z).getText()));
                        z++;
                    }
                }
            }
            if (sudoku.checkAnswer() == true) {
                try {
                    history.gameWon();
                    //Victory button
                    Button youWon = new Button("Sudoku completed in " + String.valueOf(TimeUnit.SECONDS.convert(counter, TimeUnit.MILLISECONDS)) + " seconds!");
                    youWon.setStyle("-fx-background-color: #26c902; ");
                    Scene victory = new Scene(youWon, 200, 100);
                    screen.setScene(victory);
                    youWon.setOnAction((ActionEvent event) -> {
                        sudoku.resetGame();
                        sudoku.generate();
                        generateBox(box, sudoku);
                        screen.setTitle("Sudoku");
                        screen.setScene(startScreen);
                        timer(screen);
                    });
                } catch (IOException ex) {
                    Logger.getLogger(SudokuUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Button giveUp = new Button("Give up");
        topRow.getChildren().add(checkAnswer);
        topRow.getChildren().add(giveUp);
        giveUp.setOnAction((ActionEvent event) -> {
            try {
                history.gameLost();
                timeline.stop();
                sudoku.resetGame();
                sudoku.generate();
                generateBox(box, sudoku);
                screen.setTitle("Sudoku");
                screen.setScene(startScreen);
            } catch (IOException ex) {
                Logger.getLogger(SudokuUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        topRow.setSpacing(10);

        window.setTop(topRow);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
