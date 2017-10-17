import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.*;

public class GroupSize extends Application {
    @Override
    public void start(Stage primarystage){

        TextInputDialog teamprompt = new TextInputDialog();
        teamprompt.setTitle("Team Calculator");
        teamprompt.setHeaderText("Figure out how many teams you get per number of players");
        teamprompt.setContentText("Enter the number of players:");
        teamprompt.showAndWait();

        int players = Integer.parseInt(teamprompt.getResult());

        if (players > 10){
            int teamsize = (players/2);
            Alert teamresult = new Alert(Alert.AlertType.INFORMATION);
            teamresult.setTitle("Team Calculator");
            teamresult.setHeaderText("you have " + teamsize + "teams");
            teamresult.setContentText("from " + players + "players.");
            teamresult.showAndWait();
        }
        else if (players>=3 && players<10) {
            int teamsize = ((players + 2)/3);
            Alert teamresult = new Alert(Alert.AlertType.INFORMATION);
            teamresult.setTitle("Team Calculator");
            teamresult.setHeaderText("you have " + teamsize + " team(s)");
            teamresult.setContentText("from " + players + " players.");
            teamresult.showAndWait();
        }
        else {
            String teamsize = "The number of people has to be at least 3.";
            Alert teamresult = new Alert(Alert.AlertType.INFORMATION);
            teamresult.setTitle("Team Calculator");
            teamresult.setHeaderText(teamsize);
            teamresult.setContentText(null);
            teamresult.showAndWait();
        }
    }
}
