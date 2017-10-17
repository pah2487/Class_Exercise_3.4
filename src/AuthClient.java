import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.util.Pair;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * Basic authentication in javafx
 * @author Patrick H.
 * @since 2017-10-15
 * @version 1.0
 */
public class AuthClient extends Application {
    @Override
    public void start(Stage primaryStage) {
       int attempts = 0;
        AtomicBoolean successlogin = new AtomicBoolean(false);
       do {
            attempts++;
            Dialog<Pair<String, String>> authprompt = new Dialog();
            authprompt.setTitle("Login");
            authprompt.setHeaderText("Please enter your username and password");

            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            authprompt.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField user = new TextField();
            user.setPromptText("Username");
            PasswordField pass = new PasswordField();
            pass.setPromptText("Password");

            grid.add(new Label("Username:"), 0, 0);
            grid.add(user, 1, 0);
            grid.add(new Label("Password:"), 0, 1);
            grid.add(pass, 1, 1);

            Node loginButton = authprompt.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

            user.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            authprompt.getDialogPane().setContent(grid);

            Platform.runLater(() -> user.requestFocus());

            authprompt.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(user.getText(), pass.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result = authprompt.showAndWait();

            result.ifPresent(usernamePassword -> {
                String inputuser = usernamePassword.getKey();
                String inputpass = usernamePassword.getValue();
                if (inputuser.equals("asdf") && inputpass.equals("1234")) {
                    successlogin.set(true);
                    TextInputDialog teamprompt = new TextInputDialog();
                    teamprompt.setTitle("Team Calculator");
                    teamprompt.setHeaderText("Figure out how many teams you get per number of players");
                    teamprompt.setContentText("Enter the number of players:");
                    teamprompt.showAndWait();

                    int players = Integer.parseInt(teamprompt.getResult());

                    if (players > 10) {
                        int teamsize = (players / 2);
                        Alert teamresult = new Alert(Alert.AlertType.INFORMATION);
                        teamresult.setTitle("Team Calculator");
                        teamresult.setHeaderText("you have " + teamsize + " teams");
                        teamresult.setContentText("from " + players + " players.");
                        teamresult.showAndWait();
                    } else if (players >= 3 && players < 10) {
                        int teamsize = ((players + 2) / 3);
                        Alert teamresult = new Alert(Alert.AlertType.INFORMATION);
                        teamresult.setTitle("Team Calculator");
                        teamresult.setHeaderText("you have " + teamsize + " team(s)");
                        teamresult.setContentText("from " + players + " players.");
                        teamresult.showAndWait();
                    } else {
                        String teamsize = "The number of people has to be at least 3.";
                        Alert teamresult = new Alert(Alert.AlertType.INFORMATION);
                        teamresult.setTitle("Team Calculator");
                        teamresult.setHeaderText(teamsize);
                        teamresult.setContentText(null);
                        teamresult.showAndWait();
                    }
                } else if (inputuser.equals("asdf")) {
                    Alert passfail = new Alert(Alert.AlertType.INFORMATION);
                    passfail.setTitle("Login");
                    passfail.setHeaderText("Invalid Password");
                    passfail.showAndWait();
                } else {
                    Alert userfail = new Alert(Alert.AlertType.INFORMATION);
                    userfail.setTitle("Login");
                    userfail.setHeaderText("Invalid Username and Password");
                    userfail.showAndWait();
                    }
            });
        } while (!successlogin.get() && attempts<3);
        if (!successlogin.get()){
            Alert loginfail = new Alert(Alert.AlertType.ERROR);
            loginfail.setTitle("Login");
            loginfail.setHeaderText("Please Contact your Administrator");
            loginfail.showAndWait();
        }
    }
}
