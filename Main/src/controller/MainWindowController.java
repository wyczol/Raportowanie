package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Person;

public class MainWindowController {
	private Main main;
	private Stage primaryStage;

	@FXML
	private TextField t_imie;

	@FXML
	private TextField t_nazwisko;

	@FXML
	private TextField t_nr_pokoju;

	@FXML
	private TextField t_godzina_rozpoczecia_pracy;

	@FXML
	private TextField t_godzina_zakonczenia_pracy;
	
	
	private int nrPozycji;

	@FXML
	private TableView<Person> tableView;
	@FXML
	private TableColumn<Person, String> imieColumn;
	@FXML
	private TableColumn<Person, String> nazwiskoColumn;
	@FXML
	private TableColumn<Person, String> pokojColumn;

	@FXML
	private ImageView planImageView;
	
	Person person;

	// lista gdzie można śledzić zmiany
	private ObservableList<Person> personList = FXCollections.observableArrayList();

	public void setMain(Main main, Stage primaryStage) {
		this.main = main;
		this.primaryStage = primaryStage;
		setTable();
		tableView.setItems(personList);
	}

	private void setTable() {

		personList.add(new Person("Jan", "Kowalski9", "110", 5, 14));
		personList.add(new Person("Ewa", "Nowak8", "120", 10, 18));
		personList.add(new Person("Zenon", "Doniak6", "130", 8, 14));
		personList.add(new Person("Anna", "Kos11", "140", 8, 19));
		personList.add(new Person("Jan", "Dreptak17", "150", 4, 21));

	}

	@FXML
	public void closeStage() {
		primaryStage.close();
	}

	public void initialize() {

		// wiązemy kolumne z odpowiednim polem klasy Person
		imieColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("imie"));
		nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("nazwisko"));
		pokojColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("numerPokoju"));

		tableView.getSelectionModel().selectedItemProperty().addListener(

				(bv, odlVal, newVal) -> {
					System.out.println(newVal.getNazwisko() + " , " + newVal.getImie());
					t_imie.setText(newVal.getImie());
					t_nazwisko.setText(newVal.getNazwisko());
					t_nr_pokoju.setText(newVal.getNumerPokoju());
					t_godzina_rozpoczecia_pracy.setText(newVal.getGodzinaRozpoczeciaTxt());
					t_godzina_zakonczenia_pracy.setText(newVal.getGodzinaZakonczeniaTxt());
					nrPozycji=personList.indexOf(newVal);
					if (newVal.getNumerPokoju() == "110") {
						Image image1 = new Image(getClass().getResourceAsStream("1.gif"));
						planImageView.setImage(image1);
					} else if (newVal.getNumerPokoju() == "120") {
						Image image2 = new Image(getClass().getResourceAsStream("2.gif"));
						planImageView.setImage(image2);
					} else if (newVal.getNumerPokoju() == "130") {
						Image image2 = new Image(getClass().getResourceAsStream("3.gif"));
						planImageView.setImage(image2);

					} else if (newVal.getNumerPokoju() == "140") {
						Image image2 = new Image(getClass().getResourceAsStream("4.gif"));
						planImageView.setImage(image2);
					} else if (newVal.getNumerPokoju() == "150") {
						Image image2 = new Image(getClass().getResourceAsStream("5.gif"));
						planImageView.setImage(image2);
					}



			System.out.println("Nr pozycji : "+personList.indexOf(newVal));		
					
					
				});

	}

	// funkcja sortująca
	class Sortowanie implements Comparator<Person> {

		@Override
		public int compare(Person p1, Person p2) {
			if ((p1.getGodzinaZakonczenia()
					- p1.getGodzinaRozpoczecia()) > ((p2.getGodzinaZakonczenia() - p2.getGodzinaRozpoczecia())))
				return 1;
			return 0;
		}

	}

	@FXML
	public void onRaport() {

		File plik = new File("raport_out.txt");

		try {
			PrintWriter zapis = new PrintWriter(plik);
			Collections.sort(personList, new Sortowanie());
			for (int i = 0; i < personList.size(); i++) {
				zapis.printf("%s %s %s %d %d %s\n", personList.get(i).getNazwisko(), personList.get(i).getImie(),
						personList.get(i).getNumerPokoju(), personList.get(i).getGodzinaRozpoczecia(),
						personList.get(i).getGodzinaZakonczenia(),
						", czas pracy : " + (personList.get(i).getGodzinaZakonczenia()
								- personList.get(i).getGodzinaRozpoczecia()));
			}

			zapis.close();
			System.out.println("Raport Wykonano");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void wczytajDane() {
		System.out.println("Wczytaj tabelę");

		String kolumna1;
		String kolumna2;
		String kolumna3;
		int kolumna4;
		int kolumna5;

		try {

			File plik = new File("plik_in.txt");

			Scanner odczyt = new Scanner(plik, "UTF-8");
			while (odczyt.hasNext()) {

				kolumna1 = odczyt.next();
				kolumna2 = odczyt.next();
				kolumna3 = odczyt.next();
				kolumna4 = odczyt.nextInt();
				kolumna5 = odczyt.nextInt();

				personList.add(new Person(kolumna1, kolumna2, kolumna3, kolumna4, kolumna5));
			}

			odczyt.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}catch(NoSuchElementException e1) {
			System.out.println("Koniec pobierania");
		}
		
		
		


		
	
System.out.println("Dane wczytano");
	}

	public void dodajOsobe() {
	
		
		personList.add(new Person(t_imie.getText(), t_nazwisko.getText(), t_nr_pokoju.getText(), Integer.parseInt(t_godzina_rozpoczecia_pracy.getText()) , Integer.parseInt(t_godzina_zakonczenia_pracy.getText())));

	}
	
	public void edytujOsobe() {

	
		//dodanie danych do listy
		personList.add(new Person(t_imie.getText(), t_nazwisko.getText(), t_nr_pokoju.getText(), Integer.parseInt(t_godzina_rozpoczecia_pracy.getText()) , Integer.parseInt(t_godzina_zakonczenia_pracy.getText())));
		//usuniecie bieżącej pozycji
		personList.remove(nrPozycji);
		tableView.refresh();
		

	}
	
	
	public void zapiszOsoby() {
		File plik = new File("plik_in.txt");

		try {
			PrintWriter zapis = new PrintWriter(plik);
			Collections.sort(personList, new Sortowanie());
			for (int i = 0; i < personList.size(); i++) {
				zapis.printf("%s %s %s %d %d\n", 
						personList.get(i).getImie(), 
						personList.get(i).getNazwisko(),
						personList.get(i).getNumerPokoju(), 
						personList.get(i).getGodzinaRozpoczecia(),
						personList.get(i).getGodzinaZakonczenia()
						);
			}

			zapis.close();
			System.out.println("Wykonano zapis osób do pliku ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}