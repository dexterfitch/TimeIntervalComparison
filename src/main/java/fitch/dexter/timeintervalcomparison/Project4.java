package fitch.dexter.timeintervalcomparison;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Project4 extends Application {
    @Override
    public void start(Stage stage) {
        // Set App Window
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);

        // Elements
        Label startTimeLabel = new Label("Start Time");

        Label endTimeLabel = new Label("End Time");

        Label timeInterval1Label = new Label("Time Interval 1");

        TextField timeInterval1StartField = new TextField();
        timeInterval1StartField.setAlignment(Pos.CENTER);

        TextField timeInterval1EndField = new TextField();
        timeInterval1EndField.setAlignment(Pos.CENTER);

        Label timeInterval2Label = new Label("Time Interval 2");

        TextField timeInterval2StartField = new TextField();
        timeInterval2StartField.setAlignment(Pos.CENTER);

        TextField timeInterval2EndField = new TextField();
        timeInterval2EndField.setAlignment(Pos.CENTER);

        Button compareIntervalsButton = new Button("Compare Intervals");
        compareIntervalsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Label timeToCheckLabel = new Label("Time to Check");

        TextField timeToCheckField = new TextField();
        timeToCheckField.setAlignment(Pos.CENTER);

        Button checkTimeButton = new Button("Check Time");
        checkTimeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        TextField intervalReportField = new TextField();

        // Set Event Listeners
        compareIntervalsButton.setOnMouseClicked(e -> {
            try {
                Time timeInterval1Start = new Time(timeInterval1StartField.getText());
                Time timeInterval1End = new Time(timeInterval1EndField.getText());
                Time timeInterval2Start = new Time(timeInterval2StartField.getText());
                Time timeInterval2End = new Time(timeInterval2EndField.getText());

                Interval<Time> interval1 = new Interval<>(timeInterval1Start, timeInterval1End);
                Interval<Time> interval2 = new Interval<>(timeInterval2Start, timeInterval2End);

                if (interval2.subinterval(interval1)) {
                    intervalReportField.setText("Interval 1 is a sub-interval of interval 2");
                } else if (interval1.subinterval(interval2)) {
                    intervalReportField.setText("Interval 2 is a sub-interval of interval 1");
                } else if (interval1.overlaps(interval2) && interval2.overlaps(interval1)) {
                    intervalReportField.setText("The intervals overlap");
                } else {
                    intervalReportField.setText("The intervals are disjoint");
                }
            } catch (InvalidTime ex) {
                intervalReportField.setText(ex.getMessage());
            }
        });

        checkTimeButton.setOnMouseClicked(e -> {
            try {
                Time timeInterval1Start = new Time(timeInterval1StartField.getText());
                Time timeInterval1End = new Time(timeInterval1EndField.getText());
                Time timeInterval2Start = new Time(timeInterval2StartField.getText());
                Time timeInterval2End = new Time(timeInterval2EndField.getText());
                Interval<Time> interval1 = new Interval<>(timeInterval1Start, timeInterval1End);
                Interval<Time> interval2 = new Interval<>(timeInterval2Start, timeInterval2End);
                Time checkTime = new Time(timeToCheckField.getText());

                if (interval1.within(checkTime) && interval2.within(checkTime)) {
                    intervalReportField.setText(String.format("Both intervals contain the time %s", timeToCheckField.getText()));
                } else if (interval1.within(checkTime)) {
                    intervalReportField.setText(String.format("Only interval 1 contains the time %s", timeToCheckField.getText()));
                } else if (interval2.within(checkTime)) {
                    intervalReportField.setText(String.format("Only interval 2 contains the time %s", timeToCheckField.getText()));
                } else {
                    intervalReportField.setText(String.format("Neither interval contains the time %s", timeToCheckField.getText()));
                }
            } catch (InvalidTime ex) {
                intervalReportField.setText(ex.getMessage());
            }
        });

        // Set Elements in Pane
        pane.add(startTimeLabel, 1, 0);
        pane.add(endTimeLabel, 2, 0);

        pane.add(timeInterval1Label, 0, 1);
        pane.add(timeInterval1StartField, 1, 1);
        pane.add(timeInterval1EndField, 2, 1);

        pane.add(timeInterval2Label, 0, 2);
        pane.add(timeInterval2StartField, 1, 2);
        pane.add(timeInterval2EndField, 2, 2);

        pane.add(compareIntervalsButton, 0, 3);

        pane.add(timeToCheckLabel, 0, 4);
        pane.add(timeToCheckField, 1, 4);

        pane.add(checkTimeButton, 0, 5);

        pane.add(intervalReportField, 0, 6);

        // Adjust elements in GridPane flow
        GridPane.setColumnSpan(compareIntervalsButton, 3);
        GridPane.setColumnSpan(timeToCheckField, 2);
        GridPane.setColumnSpan(checkTimeButton, 3);
        GridPane.setColumnSpan(intervalReportField, 3);
        GridPane.setHalignment(startTimeLabel, HPos.CENTER);
        GridPane.setHalignment(endTimeLabel, HPos.CENTER);

        Scene scene = new Scene(pane, 420, 220);
        stage.setTitle("Time Interval Checker!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}