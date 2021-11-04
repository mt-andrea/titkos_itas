package titkosiras;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import panel.Panel;

/**
 *
 * @author Tóth József
 */
public class FXMLDocumentController implements Initializable {
    private boolean mentve=true;
        public boolean getMentve(){
            return mentve;
        }
@FXML
    private TextArea txtaNyilt;

    @FXML
    private Slider sldEltolas;

    @FXML
    private TextArea txaTitkos;

    @FXML
    private void megnyitas() {
        if (!mentve && !Panel.igennem("A módosítások elvesznek!", "Folytatod?")) {
            return;
        }
        FileChooser fajlvalaszto= new FileChooser();
        fajlvalaszto.setTitle("Megnyitás");
        ExtensionFilter szuro=new ExtensionFilter("Szöveges fájl", "*.txt");
        fajlvalaszto.getExtensionFilters().add(szuro);
        fajlvalaszto.setInitialDirectory(new File("."));
        Window ablak=txtaNyilt.getScene().getWindow();
        File f=fajlvalaszto.showOpenDialog(ablak);
        if (f!=null) {
            try (Scanner be = new Scanner(f.getAbsoluteFile(),"utf8")){
                txtaNyilt.clear();
                while (be.hasNextLine()) {
                    txtaNyilt.appendText(be.nextLine()+"\n");
                }mentve=true;
            } catch (Exception e) {
                Panel.hiba("Hiba",e.getMessage());
            }
        }
    }

    @FXML
    private void mentes() {
        FileChooser fajlvalaszto=new FileChooser();
        fajlvalaszto.setTitle("Mentés másként...");
        ExtensionFilter szuro=new ExtensionFilter("Szöveges fájl","*.txt");
        fajlvalaszto.getExtensionFilters().add(szuro);
        fajlvalaszto.setInitialDirectory(new File("."));
        Window ablak=txaTitkos.getScene().getWindow();
        File f=fajlvalaszto.showSaveDialog(ablak);
        if(f!=null){
            try (PrintWriter ki=new PrintWriter(f.getAbsoluteFile(),"utf8")){
                ki.print(txaTitkos.getText());
                mentve=true;
            } catch (IOException e) {
                Panel.hiba("Hiba",e.getMessage());
            }
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtaNyilt.textProperty().addListener((o,regi,uj)->{
        txaTitkos.setText(Caesar.kodol(uj, (int)sldEltolas.getValue()));
        mentve=false;});
        
        sldEltolas.valueProperty().addListener((o,regi,uj)->{
        txaTitkos.setText(Caesar.kodol(txtaNyilt.getText(), uj.intValue()));
        });
    }    
    
}
