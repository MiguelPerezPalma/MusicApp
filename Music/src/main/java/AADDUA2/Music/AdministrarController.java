package AADDUA2.Music;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdministrarController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}