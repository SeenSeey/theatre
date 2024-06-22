package com.example.theatre.Controllers;

import com.example.theatre.Models.*;
import com.example.theatre.Service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class ViewerController {

    @FXML
    private TableView<Theatre> theatreTableView;
    @FXML
    private TableColumn<Theatre, String> theatreNameColumn;
    @FXML
    private TableColumn<Theatre, String> theatreLocationColumn;

    @FXML
    private TableView<Performance> performanceTableView;
    @FXML
    private TableColumn<Performance, String> performanceNameColumn;
    @FXML
    private TableColumn<Performance, String> performanceAuthorColumn;
    @FXML
    private TableColumn<Performance, String> performanceTimeIntervalColumn;
    @FXML
    private ComboBox<Date> performanceDateComboBox;
    @FXML
    private TextField viewerNameField;
    @FXML
    private TextField viewerSurnameField;
    @FXML
    private TextField viewerPhoneNumberField;
    @FXML
    private DatePicker viewerDateOfBirthPicker;

    private final TheatreService theatreService = new TheatreService();
    private final PerformanceService performanceService = new PerformanceService();
    private final TicketService ticketService = new TicketService();
    private final ViewerService viewerService = new ViewerService();

    @FXML
    public void initialize() {
        theatreNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        theatreLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        performanceNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        performanceAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        performanceTimeIntervalColumn.setCellValueFactory(new PropertyValueFactory<>("timeIntervalPerformance"));

        loadTheatres();

        theatreTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadPerformances(newSelection.getId());
            }
        });

        performanceTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadPerformanceDates(newSelection.getId());
            }
        });
    }

    private void loadTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        ObservableList<Theatre> theatreList = FXCollections.observableArrayList(theatres);
        theatreTableView.setItems(theatreList);
    }

    private void loadPerformances(int theatreId) {
        List<Performance> performances = performanceService.getPerformancesByTheatreId(theatreId);
        ObservableList<Performance> performanceList = FXCollections.observableArrayList(performances);
        performanceTableView.setItems(performanceList);
    }

    private void loadPerformanceDates(int performanceId) {
        List<Date> dates = ticketService.getDatesByPerformanceId(performanceId);
        ObservableList<Date> dateList = FXCollections.observableArrayList(dates);
        performanceDateComboBox.setItems(dateList);
    }

    @FXML
    private void handleBookTicket() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        Date selectedDate = performanceDateComboBox.getValue();
        String name = viewerNameField.getText();
        String surname = viewerSurnameField.getText();
        String phoneNumber = viewerPhoneNumberField.getText();
        Date dateOfBirth = java.sql.Date.valueOf(viewerDateOfBirthPicker.getValue());

        if (selectedPerformance != null && selectedDate != null && !name.isEmpty() && !surname.isEmpty() && !phoneNumber.isEmpty() && dateOfBirth != null) {
            Viewer viewer = new Viewer();
            viewer.setName(name);
            viewer.setSurname(surname);
            viewer.setPhoneNumber(phoneNumber);
            viewer.setDateOfBirth(dateOfBirth);
            viewerService.saveViewer(viewer);


            Ticket bookedTicket = ticketService.bookTicket(selectedPerformance.getId(), selectedDate, viewer.getId());
            if (bookedTicket != null) {
                showBookingConfirmation(selectedPerformance, bookedTicket);
                ticketService.removeBookedTicket(bookedTicket);
            } else {
                showBookingError();
            }
        } else {
            showBookingError();
        }
    }

    private void showBookingConfirmation(Performance performance, Ticket ticket) {
        Theatre theatre = performance.getTheatre();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking Confirmation");
        alert.setHeaderText("Ticket Booked Successfully");
        alert.setContentText("You have booked a ticket for the performance \"" + performance.getName() +
                "\" at the theatre \"" + theatre.getName() +
                "\" located at \"" + theatre.getLocation() + "\".\n" +
                "Please pay for your ticket at the box office.\n" +
                "Your seat number is " + ticket.getSeatNumber() + ".");
        alert.showAndWait();
    }

    private void showBookingError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Booking Error");
        alert.setHeaderText("Failed to Book Ticket");
        alert.setContentText("There was an error booking your ticket. Please try again.");
        alert.showAndWait();
    }
}
