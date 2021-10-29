module AADDUA2.Music {
    requires javafx.controls;
    requires javafx.fxml;

    opens AADDUA2.Music to javafx.fxml;
    exports AADDUA2.Music;
}
