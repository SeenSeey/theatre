package com.example.theatre.Controllers;
import static com.example.theatre.Controllers.OtherUtils.*;
import com.example.theatre.Models.*;
import com.example.theatre.Service.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class EmployeeController {
    public TextField performanceNameField;
    public TextField performanceAuthorField;
    public TextField performanceTimeIntervalField;
    public Button addPerformanceButton;
    public Button updatePerformanceButton;
    public Button deletePerformanceButton;
    public TextField TimeEventField;
    @FXML
    private TableView<Performance> performanceTableView;
    @FXML
    private TableColumn<Performance, String> performanceNameColumn;
    @FXML
    private TableColumn<Performance, String> performanceAuthorColumn;
    @FXML
    private TableColumn<Performance, String> performanceTimeIntervalColumn;

    @FXML
    private TableView<Director> directorTableView;
    @FXML
    private TableColumn<Director, String> directorNameColumn;
    @FXML
    private TableColumn<Director, String> directorSurnameColumn;
    @FXML
    private TableColumn<Director, String> directorEducationColumn;
    @FXML
    private TableColumn<Director, String> directorAwardColumn;
    @FXML
    private Button assignDirectorButton;
    @FXML
    private Button removeDirectorButton;
    @FXML
    private TableView<Director> assignedDirectorTableView;
    @FXML
    private TableColumn<Director, String> assignedDirectorNameColumn;
    @FXML
    private TableColumn<Director, String> assignedDirectorSurnameColumn;
    @FXML
    private TableColumn<Director, String> assignedDirectorEducationColumn;
    @FXML
    private TableColumn<Director, String> assignedDirectorAwardColumn;

    @FXML
    private TableView<Actor> actorTableView;
    @FXML
    private TableColumn<Actor, String> actorNameColumn;
    @FXML
    private TableColumn<Actor, String> actorSurnameColumn;
    @FXML
    private TableColumn<Actor, String> actorEducationColumn;
    @FXML
    private TableColumn<Actor, String> actorAwardColumn;
    @FXML
    private TextField actorSalaryField;
    @FXML
    private TextField actorRoleField;
    @FXML
    private TextField numberOfTicketsField;
    @FXML
    private Button assignActorButton;
    @FXML
    private Button addTicketsButton;
    @FXML
    private Button removeActorButton;
    @FXML
    private TableView<ActorContractDTO> assignedActorTableView;
    @FXML
    private TableColumn<ActorContractDTO, String> assignedActorNameColumn;
    @FXML
    private TableColumn<ActorContractDTO, String> assignedActorSurnameColumn;
    @FXML
    private TableColumn<ActorContractDTO, String> assignedActorEducationColumn;
    @FXML
    private TableColumn<ActorContractDTO, String> assignedActorAwardColumn;
    @FXML
    private TableColumn<ActorContractDTO, Double> assignedActorSalaryColumn;
    @FXML
    private TableColumn<ActorContractDTO, String> assignedActorRoleColumn;
    @FXML
    private TableView<Ticket> ticketTableView;
    @FXML
    private TableColumn<Ticket, String> ticketSeatNumberColumn;
    @FXML
    private TableColumn<Ticket, Date> ticketTimeEventColumn;
    @FXML
    private TableView<Theatre> theatreListTableView;
    @FXML
    private TableColumn<Theatre, String> allTheatreNameColumn;
    @FXML
    private TableColumn<Theatre, String> allTheatreLocationColumn;
    @FXML
    private Button assignTheatreButton;
    @FXML
    private Button removeTheatreButton;
    @FXML
    private TableView<Theatre> theatreTableView;
    @FXML
    private TableColumn<Theatre, String> assignedTheatreNameColumn;
    @FXML
    private DatePicker timeEventPicker;

    private final PerformanceService performanceService = new PerformanceService();
    private final DirectorService directorService = new DirectorService();
    private final ActorService actorService = new ActorService();
    private final TheatreService theatreService = new TheatreService();
    private final ContractService contractService = new ContractService();
    private final TicketService ticketService = new TicketService();

    public void initialize() {
        performanceNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        performanceAuthorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        performanceTimeIntervalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimeIntervalPerformance()));

        directorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        directorSurnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        directorEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEducation()));
        directorAwardColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAward()));

        assignedDirectorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        assignedDirectorSurnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        assignedDirectorEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEducation()));
        assignedDirectorAwardColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAward()));

        actorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        actorSurnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        actorEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEducation()));
        actorAwardColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAward()));

        assignedActorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        assignedActorSurnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        assignedActorEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEducation()));
        assignedActorAwardColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAward()));
        assignedActorSalaryColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSalaryForPerformance()).asObject());
        assignedActorRoleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));

        allTheatreNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        allTheatreLocationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        assignedTheatreNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        addTicketsButton.setOnAction(event -> addTickets());

        ticketSeatNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeatNumber()));
        ticketTimeEventColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTimeEvent()));

        loadPerformances();
        loadDirectors();
        loadActors();
        loadTheatres();
        loadTickets();

        performanceTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadPerformanceDetails(newValue);
            }
        });


        addPerformanceButton.setOnAction(event -> addPerformance());
        updatePerformanceButton.setOnAction(event -> updatePerformance());
        deletePerformanceButton.setOnAction(event -> deletePerformance());

        assignDirectorButton.setOnAction(event -> assignDirectorToPerformance());
        removeDirectorButton.setOnAction(event -> removeDirectorFromPerformance());

        assignActorButton.setOnAction(event -> assignActorToPerformance());
        removeActorButton.setOnAction(event -> removeActorFromPerformance());

        assignTheatreButton.setOnAction(event -> assignTheatreToPerformance());
        removeTheatreButton.setOnAction(event -> removeTheatreFromPerformance());
    }

    private void loadPerformances() {
        ObservableList<Performance> performances = FXCollections.observableArrayList(performanceService.getAllPerformances());
        performanceTableView.setItems(performances);
    }

    private void loadDirectors() {
        ObservableList<Director> directors = FXCollections.observableArrayList(directorService.getAllDirectors());
        directorTableView.setItems(directors);
    }

    private void loadActors() {
        ObservableList<Actor> actors = FXCollections.observableArrayList(actorService.getAllActors());
        actorTableView.setItems(actors);
    }

    private void loadTheatres() {
        ObservableList<Theatre> theatres = FXCollections.observableArrayList(theatreService.getAllTheatres());
        theatreListTableView.setItems(theatres);
    }

    private void loadPerformanceDetails(Performance performance) {
        performanceNameField.setText(performance.getName());
        performanceAuthorField.setText(performance.getAuthor());
        performanceTimeIntervalField.setText(performance.getTimeIntervalPerformance());

        Integer directorId = performance.getDirectorId();
        if (directorId != null) {
            Optional<Director> director = directorService.getDirectorById(directorId);
            director.ifPresent(value -> assignedDirectorTableView.setItems(FXCollections.observableArrayList(value)));
        } else {
            assignedDirectorTableView.getItems().clear();
        }

        List<ActorContractDTO> actorContracts = contractService.getContractsByPerformanceId(performance.getId()).stream()
                .map(contract -> new ActorContractDTO(contract.getActor(), contract))
                .collect(Collectors.toList());
        assignedActorTableView.setItems(FXCollections.observableArrayList(actorContracts));

        Integer theatreId = performance.getTheatreId();
        if (theatreId != null) {
            Optional<Theatre> theatre = theatreService.getTheatreById(theatreId);
            theatre.ifPresent(value -> theatreTableView.setItems(FXCollections.observableArrayList(value)));
        } else {
            theatreTableView.getItems().clear();
        }
    }

    private void addPerformance() {
        Performance performance = new Performance();
        performance.setName(performanceNameField.getText());
        performance.setAuthor(performanceAuthorField.getText());
        performance.setTimeIntervalPerformance(performanceTimeIntervalField.getText());
        performanceService.savePerformance(performance);
        loadPerformances();
    }

    private void updatePerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null) {
            selectedPerformance.setName(performanceNameField.getText());
            selectedPerformance.setAuthor(performanceAuthorField.getText());
            selectedPerformance.setTimeIntervalPerformance(performanceTimeIntervalField.getText());
            performanceService.updatePerformance(selectedPerformance.getId(), selectedPerformance);
            loadPerformances();
        }
    }

    private void deletePerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null) {
            performanceService.deletePerformance(selectedPerformance.getId());
            loadPerformances();
        }
    }

    private void assignDirectorToPerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        Director selectedDirector = directorTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null && selectedDirector != null) {
            selectedPerformance.setDirectorId(selectedDirector.getId());
            performanceService.updatePerformance(selectedPerformance.getId(), selectedPerformance);
            loadPerformanceDetails(selectedPerformance);
        }
    }

    private void removeDirectorFromPerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null) {
            selectedPerformance.setDirectorId(null);
            performanceService.updatePerformance(selectedPerformance.getId(), selectedPerformance);
            loadPerformanceDetails(selectedPerformance);
        }
    }

    private void assignActorToPerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        Actor selectedActor = actorTableView.getSelectionModel().getSelectedItem();
        String role = actorRoleField.getText();
        double salary = Double.parseDouble(actorSalaryField.getText());
        if (selectedPerformance != null && selectedActor != null) {
            Contract contract = new Contract();
            contract.setPerformanceId(selectedPerformance.getId());
            contract.setActorId(selectedActor.getId());
            contract.setRole(role);
            contract.setSalaryForPerformance(salary);
            contractService.saveContract(contract);
            loadPerformanceDetails(selectedPerformance);
        }
    }

    private void removeActorFromPerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        ActorContractDTO selectedActorDTO = assignedActorTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null && selectedActorDTO != null) {
            List<Contract> contracts = contractService.getContractsByPerformanceAndActor(selectedPerformance.getId(), selectedActorDTO.getActor().getId());
            for (Contract contract : contracts) {
                contractService.deleteContract(contract.getId());
            }
            loadPerformanceDetails(selectedPerformance);
            loadPerformances();
        }
    }


    private void assignTheatreToPerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        Theatre selectedTheatre = theatreListTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null && selectedTheatre != null) {
            selectedPerformance.setTheatreId(selectedTheatre.getId());
            performanceService.updatePerformance(selectedPerformance.getId(), selectedPerformance);
            loadPerformanceDetails(selectedPerformance);
        }
    }

    private void removeTheatreFromPerformance() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null) {
            selectedPerformance.setTheatreId(null);
            performanceService.updatePerformance(selectedPerformance.getId(), selectedPerformance);
            loadPerformanceDetails(selectedPerformance);
        }
    }

    @FXML
    private void addTickets() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null && selectedPerformance.getTheatre() != null) {
            int totalSeats = selectedPerformance.getTheatre().getNumberOfSeats();
            int currentTickets = ticketService.getTicketsByPerformanceId(selectedPerformance.getId()).size();
            int numberOfTickets;
            String timeEvent;

            try {
                numberOfTickets = Integer.parseInt(numberOfTicketsField.getText());
                timeEvent = TimeEventField.getText();
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid input");
                return;
            }

            if (timeEventPicker.getValue() == null) {
                showAlert("Error", "Invalid date");
                return;
            }

            LocalDate date = timeEventPicker.getValue();
            LocalTime time;
            try {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                time = LocalTime.parse(timeEvent, timeFormatter);
            } catch (DateTimeParseException e) {
                showAlert("Error", "Invalid time format, please use HH:mm");
                return;
            }

            LocalDateTime dateTime = LocalDateTime.of(date, time);
            Timestamp timestamp = Timestamp.valueOf(dateTime);


            for (int i = 1; i <= numberOfTickets; i++) {
                Ticket ticket = new Ticket();
                ticket.setPerformance(selectedPerformance);
                ticket.setSeatNumber(String.valueOf(currentTickets + i));
                ticket.setTimeEvent(timestamp);
                ticketService.saveTicket(ticket);
            }
            loadTickets();

        } else {
            showAlert("Error", "No performance selected");
        }

    }

    private void loadTickets() {
        Performance selectedPerformance = performanceTableView.getSelectionModel().getSelectedItem();
        if (selectedPerformance != null) {
            List<Ticket> tickets = ticketService.getTicketsByPerformanceId(selectedPerformance.getId());
            ticketTableView.setItems(FXCollections.observableArrayList(tickets));
        } else {
            ticketTableView.getItems().clear();
        }
    }
    @FXML
    private void handleBack(ActionEvent event) {
        OtherUtils.changeScene(event, "/com/example/theatre/hello-view.fxml", "?");
    }
}