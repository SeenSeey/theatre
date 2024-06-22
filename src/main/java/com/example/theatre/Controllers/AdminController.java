package com.example.theatre.Controllers;

import com.example.theatre.Models.Actor;
import com.example.theatre.Models.BoxOffice;
import com.example.theatre.Models.Director;
import com.example.theatre.Models.Theatre;
import com.example.theatre.Service.ActorService;
import com.example.theatre.Service.BoxOfficeService;
import com.example.theatre.Service.DirectorService;
import com.example.theatre.Service.TheatreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController {
    @FXML
    private TextField theatreNameField;
    @FXML
    private TextField theatreLocationField;
    @FXML
    private TextField theatreSeatsField;
    @FXML
    private TextField theatrePhoneField;

    @FXML
    private TextField boxOfficeLocationField;
    @FXML
    private TextField boxOfficePhoneField;

    @FXML
    private TextField directorNameField;
    @FXML
    private TextField directorSurnameField;
    @FXML
    private TextField directorEducationField;
    @FXML
    private TextField directorAwardField;

    @FXML
    private TextField actorNameField;
    @FXML
    private TextField actorSurnameField;
    @FXML
    private TextField actorEducationField;
    @FXML
    private TextField actorAwardField;

    @FXML
    private TableView<Theatre> theatreTable;
    @FXML
    private TableColumn<Theatre, Integer> theatreIdColumn;
    @FXML
    private TableColumn<Theatre, String> theatreNameColumn;
    @FXML
    private TableColumn<Theatre, String> theatreLocationColumn;
    @FXML
    private TableColumn<Theatre, Integer> theatreSeatsColumn;
    @FXML
    private TableColumn<Theatre, String> theatrePhoneColumn;

    @FXML
    private TableView<BoxOffice> boxOfficeTable;
    @FXML
    private TableColumn<BoxOffice, Integer> boxOfficeIdColumn;
    @FXML
    private TableColumn<BoxOffice, String> boxOfficeLocationColumn;
    @FXML
    private TableColumn<BoxOffice, String> boxOfficePhoneColumn;

    @FXML
    private TableView<Director> directorTable;
    @FXML
    private TableColumn<Director, Integer> directorIdColumn;
    @FXML
    private TableColumn<Director, String> directorNameColumn;
    @FXML
    private TableColumn<Director, String> directorSurnameColumn;
    @FXML
    private TableColumn<Director, String> directorEducationColumn;
    @FXML
    private TableColumn<Director, String> directorAwardColumn;

    @FXML
    private TableView<Actor> actorTable;
    @FXML
    private TableColumn<Actor, Integer> actorIdColumn;
    @FXML
    private TableColumn<Actor, String> actorNameColumn;
    @FXML
    private TableColumn<Actor, String> actorSurnameColumn;
    @FXML
    private TableColumn<Actor, String> actorEducationColumn;
    @FXML
    private TableColumn<Actor, String> actorAwardColumn;

    private TheatreService theatreService = new TheatreService();
    private BoxOfficeService boxOfficeService = new BoxOfficeService();
    private DirectorService directorService = new DirectorService();
    private ActorService actorService = new ActorService();

    private ObservableList<Theatre> theatreList = FXCollections.observableArrayList();
    private ObservableList<BoxOffice> boxOfficeList = FXCollections.observableArrayList();
    private ObservableList<Director> directorList = FXCollections.observableArrayList();
    private ObservableList<Actor> actorList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        theatreIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        theatreNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        theatreLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        theatreSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        theatrePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        boxOfficeIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        boxOfficeLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        boxOfficePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        directorIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        directorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        directorSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        directorEducationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        directorAwardColumn.setCellValueFactory(new PropertyValueFactory<>("award"));

        actorIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        actorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        actorSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        actorEducationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        actorAwardColumn.setCellValueFactory(new PropertyValueFactory<>("award"));

        loadTheatreData();
        loadBoxOfficeData();
        loadDirectorData();
        loadActorData();

        theatreTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                filterBoxOfficesByTheatre(newSelection);
            }
        });
    }

    private void loadTheatreData() {
        theatreList.clear();
        theatreList.addAll(theatreService.getAllTheatres());
        theatreTable.setItems(theatreList);
    }

    private void loadBoxOfficeData() {
        boxOfficeList.clear();
        boxOfficeList.addAll(boxOfficeService.getAllBoxOffices());
        boxOfficeTable.setItems(boxOfficeList);
    }

    private void filterBoxOfficesByTheatre(Theatre theatre) {
        boxOfficeList.clear();
        boxOfficeList.addAll(boxOfficeService.getBoxOfficesByTheatre(theatre));
        boxOfficeTable.setItems(boxOfficeList);
    }

    private void loadDirectorData() {
        directorList.clear();
        directorList.addAll(directorService.getAllDirectors());
        directorTable.setItems(directorList);
    }

    private void loadActorData() {
        actorList.clear();
        actorList.addAll(actorService.getAllActors());
        actorTable.setItems(actorList);
    }

    @FXML
    private void addTheatre() {
        String name = theatreNameField.getText();
        String location = theatreLocationField.getText();
        String seatsText = theatreSeatsField.getText();
        String phone = theatrePhoneField.getText();

        if (!name.isEmpty() && !location.isEmpty() && !seatsText.isEmpty() && !phone.isEmpty()) {
            int seats = Integer.parseInt(seatsText);
            Theatre theatre = new Theatre(name, location, seats, phone);
            theatreService.saveTheatre(theatre);
            theatreList.add(theatre);
            clearTheatreFields();
        }
    }

    @FXML
    private void updateTheatre() {
        Theatre selectedTheatre = theatreTable.getSelectionModel().getSelectedItem();
        if (selectedTheatre != null) {
            String newName = theatreNameField.getText();
            String newLocation = theatreLocationField.getText();
            String newSeatsText = theatreSeatsField.getText();
            String newPhone = theatrePhoneField.getText();

            if (!newName.isEmpty() && !newLocation.isEmpty() && !newSeatsText.isEmpty() && !newPhone.isEmpty()) {
                int newSeats = Integer.parseInt(newSeatsText);
                selectedTheatre.setName(newName);
                selectedTheatre.setLocation(newLocation);
                selectedTheatre.setNumberOfSeats(newSeats);
                selectedTheatre.setPhoneNumber(newPhone);

                theatreService.updateTheatre(selectedTheatre);
                loadTheatreData();
                clearTheatreFields();
            }
        }
    }

    @FXML
    private void deleteTheatre() {
        Theatre selectedTheatre = theatreTable.getSelectionModel().getSelectedItem();
        if (selectedTheatre != null) {
            theatreService.deleteTheatre(selectedTheatre);
            theatreList.remove(selectedTheatre);
            clearTheatreFields();
        }
    }

    @FXML
    private void addBoxOffice() {
        String location = boxOfficeLocationField.getText();
        String phone = boxOfficePhoneField.getText();
        Theatre selectedTheatre = theatreTable.getSelectionModel().getSelectedItem();

        if (selectedTheatre != null && !location.isEmpty() && !phone.isEmpty()) {
            BoxOffice boxOffice = new BoxOffice(selectedTheatre, location, "8:00-20:00", phone);
            boxOfficeService.saveBoxOffice(boxOffice);
            boxOfficeList.add(boxOffice);
            clearBoxOfficeFields();
        }
    }

    @FXML
    private void updateBoxOffice() {
        BoxOffice selectedBoxOffice = boxOfficeTable.getSelectionModel().getSelectedItem();
        if (selectedBoxOffice != null) {
            String newLocation = boxOfficeLocationField.getText();
            String newPhone = boxOfficePhoneField.getText();

            if (!newLocation.isEmpty() && !newPhone.isEmpty()) {
                selectedBoxOffice.setLocation(newLocation);
                selectedBoxOffice.setPhoneNumber(newPhone);

                boxOfficeService.updateBoxOffice(selectedBoxOffice);
                loadBoxOfficeData();
                clearBoxOfficeFields();
            }
        }
    }

    @FXML
    private void deleteBoxOffice() {
        BoxOffice selectedBoxOffice = boxOfficeTable.getSelectionModel().getSelectedItem();
        if (selectedBoxOffice != null) {
            boxOfficeService.deleteBoxOffice(selectedBoxOffice);
            boxOfficeList.remove(selectedBoxOffice);
            clearBoxOfficeFields();
        }
    }

    @FXML
    private void addDirector() {
        String name = directorNameField.getText();
        String surname = directorSurnameField.getText();
        String education = directorEducationField.getText();
        String award = directorAwardField.getText();

        if (!name.isEmpty() && !surname.isEmpty() && !education.isEmpty() && !award.isEmpty()) {
            Director director = new Director(name, surname, education, award);
            directorService.saveDirector(director);
            directorList.add(director);
            clearDirectorFields();
        }
    }

    @FXML
    private void updateDirector() {
        Director selectedDirector = directorTable.getSelectionModel().getSelectedItem();
        if (selectedDirector != null) {
            String newName = directorNameField.getText();
            String newSurname = directorSurnameField.getText();
            String newEducation = directorEducationField.getText();
            String newAward = directorAwardField.getText();

            if (!newName.isEmpty() && !newSurname.isEmpty() && !newEducation.isEmpty() && !newAward.isEmpty()) {
                selectedDirector.setName(newName);
                selectedDirector.setSurname(newSurname);
                selectedDirector.setEducation(newEducation);
                selectedDirector.setAward(newAward);

                directorService.updateDirector(selectedDirector);
                loadDirectorData();
                clearDirectorFields();
            }
        }
    }

    @FXML
    private void deleteDirector() {
        Director selectedDirector = directorTable.getSelectionModel().getSelectedItem();
        if (selectedDirector != null) {
            directorService.deleteDirector(selectedDirector);
            directorList.remove(selectedDirector);
            clearDirectorFields();
        }
    }

    @FXML
    private void addActor() {
        String name = actorNameField.getText();
        String surname = actorSurnameField.getText();
        String education = actorEducationField.getText();
        String award = actorAwardField.getText();

        if (!name.isEmpty() && !surname.isEmpty() && !education.isEmpty() && !award.isEmpty()) {
            Actor actor = new Actor(name, surname, education, award);
            actorService.saveActor(actor);
            actorList.add(actor);
            clearActorFields();
        }
    }

    @FXML
    private void updateActor() {
        Actor selectedActor = actorTable.getSelectionModel().getSelectedItem();
        if (selectedActor != null) {
            String newName = actorNameField.getText();
            String newSurname = actorSurnameField.getText();
            String newEducation = actorEducationField.getText();
            String newAward = actorAwardField.getText();

            if (!newName.isEmpty() && !newSurname.isEmpty() && !newEducation.isEmpty() && !newAward.isEmpty()) {
                selectedActor.setName(newName);
                selectedActor.setSurname(newSurname);
                selectedActor.setEducation(newEducation);
                selectedActor.setAward(newAward);

                actorService.updateActor(selectedActor);
                loadActorData();
                clearActorFields();
            }
        }
    }

    @FXML
    private void deleteActor() {
        Actor selectedActor = actorTable.getSelectionModel().getSelectedItem();
        if (selectedActor != null) {
            actorService.deleteActor(selectedActor);
            actorList.remove(selectedActor);
            clearActorFields();
        }
    }

    private void clearTheatreFields() {
        theatreNameField.clear();
        theatreLocationField.clear();
        theatreSeatsField.clear();
        theatrePhoneField.clear();
    }

    private void clearBoxOfficeFields() {
        boxOfficeLocationField.clear();
        boxOfficePhoneField.clear();
    }

    private void clearDirectorFields() {
        directorNameField.clear();
        directorSurnameField.clear();
        directorEducationField.clear();
        directorAwardField.clear();
    }

    private void clearActorFields() {
        actorNameField.clear();
        actorSurnameField.clear();
        actorEducationField.clear();
        actorAwardField.clear();
    }
}
