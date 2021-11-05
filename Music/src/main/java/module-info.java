module AADDUA2.Music {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens AADDUA2.Music to javafx.fxml;
    exports AADDUA2.Music;
}
