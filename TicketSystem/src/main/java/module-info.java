module cs411.ticketsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs411.ticketsystem to javafx.fxml;
    exports cs411.ticketsystem;
}