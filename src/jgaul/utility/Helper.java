package jgaul.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import jgaul.DAO.ClientScheduleQuery;
import jgaul.model.Country;
import jgaul.model.Division;
import jgaul.model.User;


public abstract class Helper {

    public static final ObservableList<Country> allCountries = FXCollections.observableArrayList();
    public static final ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public static final ObservableList<Division> americaDivision = FXCollections.observableArrayList();
    public static final ObservableList<Division> canadaDivision = FXCollections.observableArrayList();
    public static final ObservableList<Division> ukDivision = FXCollections.observableArrayList();
    private static String currentUser;

    public static void initializeConstantFields() {
        ClientScheduleQuery.selectAllCountries(allCountries);
        ClientScheduleQuery.selectAllDivisions(allDivisions);

        for (Division division : allDivisions) {
            int divisionNum = division.getCountryID();
            switch (divisionNum) {
                case 1 -> americaDivision.add(division);
                case 2 -> ukDivision.add(division);
                case 3 -> canadaDivision.add(division);
            }
        }
    }

    public static boolean checkForBlankString(String errorMessage, String toCheck) {
        if (toCheck.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Text Field is Blank");
            alert.setHeaderText(errorMessage);
            alert.setContentText("Fill in all fields to proceed.");
            alert.show();
            return true;
        }
        return false;
    }

    public static boolean checkForNullValue(String errorMessage, Object object) {
        if (object == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A Text Field is Blank");
            alert.setHeaderText(errorMessage);
            alert.setContentText("Fill in all fields to proceed.");
            alert.show();
            return true;
        }
        return false;
    }

    public static void setCurrentUser(User user) {
        currentUser = user.getUsername();
    }

    public static String getCurrentUser() {
        return currentUser;
    }


}
