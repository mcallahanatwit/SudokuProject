package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start (Stage s) {
	s.setWidth(800);
	s.setHeight(600);
	s.show();
	}
	public static void main(String[] args ) {
	launch(args);
	}
	}
