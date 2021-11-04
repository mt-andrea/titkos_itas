package titkosiras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panel.Panel;

/**
 *
 * @author Tóth József
 */
public class Titkosiras extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController dc=loader.getController();
        stage.setOnCloseRequest((e)->{
            if (!dc.getMentve() && !Panel.igennem("A módosítások elvesznek!","Folytatod?")) {
                e.consume();
            }
                });
        Scene scene = new Scene(root);
        stage.setTitle("Caesar kódolás");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
