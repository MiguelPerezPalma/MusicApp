package AADDUA2.Music;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {
	private TextField Nombtx;
	private TextField Contratx;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
