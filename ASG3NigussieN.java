import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ASG3NigussieN extends Application {

    // Declare inputs and outputs as fields so they can be accessed in the event handler
    private TextField txtDistance;
    private ComboBox<String> cmbDistanceUnit;
    private TextField txtGasolineCost;
    private ComboBox<String> cmbGasolineUnit;
    private TextField txtGasMileage;
    private ComboBox<String> cmbMileageUnit;
    private TextField txtNumberOfDays;
    private TextField txtHotelCost;
    private TextField txtFoodCost;
    private TextField txtAttractions;
    private TextField txtTotalTripCost;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the UI components that were declared as fields
        txtDistance = new TextField();
        cmbDistanceUnit = new ComboBox<>();
        cmbDistanceUnit.getItems().addAll("miles", "kilometers");
        cmbDistanceUnit.getSelectionModel().selectFirst();

        txtGasolineCost = new TextField();
        cmbGasolineUnit = new ComboBox<>();
        cmbGasolineUnit.getItems().addAll("dollars/gal", "dollars/liter");
        cmbGasolineUnit.getSelectionModel().selectFirst();

        txtGasMileage = new TextField();
        cmbMileageUnit = new ComboBox<>();
        cmbMileageUnit.getItems().addAll("miles/gallon", "kilometers/liter");
        cmbMileageUnit.getSelectionModel().selectFirst();

        txtNumberOfDays = new TextField();
        txtHotelCost = new TextField();
        txtFoodCost = new TextField();
        txtAttractions = new TextField();
        txtTotalTripCost = new TextField();
        txtTotalTripCost.setEditable(false);

        Button btnCalculate = new Button("Calculate");
        btnCalculate.setOnAction(e -> calculateCost());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.add(new Label("Distance:"), 0, 0);
        gridPane.add(txtDistance, 1, 0);
        gridPane.add(cmbDistanceUnit, 2, 0);

        gridPane.add(new Label("Gasoline Cost:"), 0, 1);
        gridPane.add(txtGasolineCost, 1, 1);
        gridPane.add(cmbGasolineUnit, 2, 1);

        gridPane.add(new Label("Gas Mileage:"), 0, 2);
        gridPane.add(txtGasMileage, 1, 2);
        gridPane.add(cmbMileageUnit, 2, 2);

        gridPane.add(new Label("Number Of Days:"), 0, 3);
        gridPane.add(txtNumberOfDays, 1, 3);

        gridPane.add(new Label("Hotel Cost:"), 0, 4);
        gridPane.add(txtHotelCost, 1, 4);

        gridPane.add(new Label("Food Cost:"), 0, 5);
        gridPane.add(txtFoodCost, 1, 5);

        gridPane.add(new Label("Attractions:"), 0, 6);
        gridPane.add(txtAttractions, 1, 6);

        gridPane.add(btnCalculate, 1, 7, 2, 1); // Span 2 columns for the button

        gridPane.add(new Label("Total Trip Cost"), 0, 8);
        gridPane.add(txtTotalTripCost, 1, 8);

        // Set alignment and size for the Calculate button
        btnCalculate.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(btnCalculate, true);

        // Set constraints for the combo boxes
        cmbDistanceUnit.setMaxWidth(Double.MAX_VALUE);
        cmbGasolineUnit.setMaxWidth(Double.MAX_VALUE);
        cmbMileageUnit.setMaxWidth(Double.MAX_VALUE);

        // Set alignment for the total trip cost field
        txtTotalTripCost.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(txtTotalTripCost, true);

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Trip Cost Estimator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateCost() {
        try {
            // Parse input values
            double distance = Double.parseDouble(txtDistance.getText());
            double gasolineCost = Double.parseDouble(txtGasolineCost.getText());
            double gasMileage = Double.parseDouble(txtGasMileage.getText());
            int numberOfDays = Integer.parseInt(txtNumberOfDays.getText());
            double hotelCost = Double.parseDouble(txtHotelCost.getText());
            double foodCost = Double.parseDouble(txtFoodCost.getText());
            double attractionsCost = Double.parseDouble(txtAttractions.getText());

            // Perform calculations (using the TripCost class logic)
            TripCost tripCost = new TripCost(
                    distance, gasolineCost, gasMileage, 
                    numberOfDays, hotelCost, foodCost, attractionsCost
                );
                double totalCost = tripCost.calculateTotalTripCost();

            // Display the result
            txtTotalTripCost.setText(String.format("$%.2f", totalCost));
        } catch (NumberFormatException ex) {
            // Handle invalid input
            txtTotalTripCost.setText("Invalid input!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // The TripCost class will be added here later
    public static class TripCost {
        private final double distance;
        private final double gasolineCost;
        private final double gasMileage;
        private final int numberOfDays;
        private final double hotelCost;
        private final double foodCost;
        private final double attractionsCost;

        public TripCost(
            double distance, double gasolineCost, double gasMileage,
            int numberOfDays, double hotelCost, double foodCost, double attractionsCost
        ) {
            this.distance = distance;
            this.gasolineCost = gasolineCost;
            this.gasMileage = gasMileage;
            this.numberOfDays = numberOfDays;
            this.hotelCost = hotelCost;
            this.foodCost = foodCost;
            this.attractionsCost = attractionsCost;
        }

        public double calculateTotalTripCost() {
            double gasolineTotalCost = (this.distance / this.gasMileage) * this.gasolineCost;
            double dailyTotalCost = (this.hotelCost + this.foodCost) * this.numberOfDays;
            return gasolineTotalCost + dailyTotalCost + this.attractionsCost;
        }
    }
}

// You can add the TripCost class code here or in a separate file depending on your project setup.
