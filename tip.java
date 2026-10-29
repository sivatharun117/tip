
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Tip Calculator");

        Label billLabel = new Label("Enter Bill Amount:");
        TextField billField = new TextField();
        billField.setPromptText("Enter bill amount");

        Label tipLabel = new Label("Tip Percentage: 15%");
        Slider tipSlider = new Slider(0, 30, 15);
        tipSlider.setShowTickLabels(true);
        tipSlider.setShowTickMarks(true);
        tipSlider.setMajorTickUnit(5);
        tipSlider.setBlockIncrement(1);

        Label tipAmountLabel = new Label("Tip Amount: Rs. 0.00");
        Label totalLabel = new Label("Total Amount: Rs. 0.00");
        Label messageLabel = new Label();

        Button calculateButton = new Button("Calculate Tip");
        Button clearButton = new Button("Clear");

        // Update percentage when slider changes
        tipSlider.valueProperty().addListener(
            (obs, oldValue, newValue) -> {
                tipLabel.setText(
                    "Tip Percentage: " + newValue.intValue() + "%"
                );
            }
        );

        // Calculate tip when button is clicked
        calculateButton.setOnAction(event -> {
            try {
                double bill = Double.parseDouble(
                    billField.getText().trim()
                );

                if (!Double.isFinite(bill) || bill < 0) {
                    messageLabel.setText(
                        "Enter a valid non-negative bill amount."
                    );
                    return;
                }

                double percentage = tipSlider.getValue();
                double tip = bill * percentage / 100;
                double total = bill + tip;

                tipAmountLabel.setText(
                    String.format("Tip Amount: Rs. %.2f", tip)
                );

                totalLabel.setText(
                    String.format("Total Amount: Rs. %.2f", total)
                );

                messageLabel.setText("Calculation successful!");

            } catch (NumberFormatException e) {
                messageLabel.setText(
                    "Please enter a valid bill amount."
                );
            }
        });

        // Clear all inputs and results
        clearButton.setOnAction(event -> {
            billField.clear();
            tipSlider.setValue(15);
            tipAmountLabel.setText("Tip Amount: Rs. 0.00");
            totalLabel.setText("Total Amount: Rs. 0.00");
            messageLabel.setText("");
        });

        VBox root = new VBox(
            12,
            title,
            billLabel,
            billField,
            tipLabel,
            tipSlider,
            calculateButton,
            clearButton,
            tipAmountLabel,
            totalLabel,
            messageLabel
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 350, 450);

        stage.setTitle("JavaFX Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
