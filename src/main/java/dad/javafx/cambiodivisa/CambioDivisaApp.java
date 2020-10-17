package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {
	
	//ELEMENTOS DE LA INTERFAZ
		private TextField moneyText1;
		private TextField moneyText2;
		private ComboBox<Divisa> diviCombo1;
		private ComboBox<Divisa> diviCombo2;
		private Button changeButton;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//AJUSTES TEXT-FIELDS
		moneyText1 = new TextField("0");
		moneyText1.setMaxWidth(50);
		
		moneyText2 = new TextField("0");
		moneyText2.setEditable(false);
		moneyText2.setMaxWidth(50);
		
		//AQUI PREPARO LAS DIVISAS PARA METERLAS EN LOS COMBOS
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		Divisa libra = new Divisa("Libra", 0.8873);
		
		ObservableList<Divisa> divisas = FXCollections.observableArrayList();
		divisas.addAll(euro,dolar,yen,libra);
		
		diviCombo1 = new ComboBox<Divisa>(divisas);
		diviCombo1.setValue(euro);
		diviCombo2 = new ComboBox<Divisa>(divisas);
		diviCombo2.setValue(euro);
		
		//AJUSTES BOTON
		changeButton = new Button("Cambiar");
		changeButton.setOnAction(e -> change());
		
		//ORGANIZACION EN CAJAS
		HBox hbox1 = new HBox(moneyText1,diviCombo1);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(5);
		HBox hbox2 = new HBox(moneyText2,diviCombo2);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(5);
		
		VBox root = new VBox(hbox1,hbox2,changeButton);
		root.setSpacing(5);
		root.setFillWidth(false);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root,320,200);
		
		primaryStage.setTitle("CambioDivisaApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void change() {
		try {
			moneyText2.setText(String.valueOf(Divisa.fromTo(diviCombo1.getValue(), diviCombo2.getValue(), 
					Double.parseDouble(moneyText1.getText()))));
			
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("CUIDADO!!!");
			alert.setHeaderText("ANDE VAS MAQUINA!!!");
			alert.setContentText("SOLO PUEDES INTRODUCIR NUMEROS Y TAMPOCO PUEDE ESTAR VACIO!!!");
			
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
