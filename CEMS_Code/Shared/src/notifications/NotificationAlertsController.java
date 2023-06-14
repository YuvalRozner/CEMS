package notifications;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

/**
 * The NotificationAlertsController class provides methods to show different types of alerts.
 * 
 * @author Mor Shmuel
 */
public class NotificationAlertsController{
	
	private Runnable onCancelAction;
    private Runnable onOkAction;

    /**
     * Shows an information alert with the specified label.
     *
     * @param label the content text for the alert
     */
	public void showInformationAlert(String label) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Alert");
		//alert.setHeaderText("Hello"); //set HeaderText we want
		alert.setHeaderText(null); //without HeaderText
		//alert.setContentText("Connect to the database successfully!");
		
		//Create a label for the content text
	    Label contentLabel = new Label(label);
	    contentLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");
	    
	    //Set the custom label as the content of the alert
	    alert.getDialogPane().setContent(contentLabel);
		alert.getDialogPane().setStyle("-fx-background-color: #F8FFFF; -fx-border-color: #92bce3; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 10px;");

        //Set button styles and add hover effects
        alert.getDialogPane().getButtonTypes().stream()
            .map(alert.getDialogPane()::lookupButton)
            .forEach(button -> {
                button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #4096EE; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
            });
		alert.showAndWait();
	}
	
    /**
     * Shows a warning alert with the specified label.
     *
     * @param label the content text for the alert
     */
	public void showWarningAlert(String label) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning alert");
		//alert.setHeaderText("Hello"); //set HeaderText we want
		alert.setHeaderText(null); //without HeaderText
		//alert.setContentText("Battery charge is low");
		
		//Create a label for the content text
	    Label contentLabel = new Label(label);
	    contentLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");
	    
	    //Set the custom label as the content of the alert
	    alert.getDialogPane().setContent(contentLabel);
		alert.getDialogPane().setStyle("-fx-background-color: #F8FFFF; -fx-border-color: #92bce3; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 10px;");
       
        //Set button styles and add hover effects
        alert.getDialogPane().getButtonTypes().stream()
            .map(alert.getDialogPane()::lookupButton)
            .forEach(button -> {
                button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #4096EE; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
            });
		
		alert.showAndWait();
	}
	
    /**
     * Shows an error alert with the specified label.
     *
     * @param label the content text for the alert
     */
	public void showErrorAlert(String label) {
		Alert alert = new Alert(AlertType.ERROR);

		alert.setTitle("Error alert");
		//alert.setHeaderText("Can not add user"); //set HeaderText we want
		alert.setHeaderText(null); //without HeaderText
		//alert.setContentText("The user does not exists");
		
		//Create a label for the content text
	    Label contentLabel = new Label(label);
	    contentLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");
	    
	    //Set the custom label as the content of the alert
	    alert.getDialogPane().setContent(contentLabel);
		alert.getDialogPane().setStyle("-fx-background-color: #F8FFFF; -fx-border-color: #92bce3; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 10px;");
        
        //Set button styles and add hover effects
        alert.getDialogPane().getButtonTypes().stream()
            .map(alert.getDialogPane()::lookupButton)
            .forEach(button -> {
                button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";");
                button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #4096EE; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
                button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
            });
		
		alert.showAndWait();
	}
	
    /**
     * Shows a confirmation alert with the specified label and header label.
     *
     * @param label the content text for the alert
     * @param headerLabel the header text for the alert
     */
	public void showConfirmationAlert(String label, String headerLabel) {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText(headerLabel);
      //alert.setContentText("C:/MyFile.txt");
      
	  //Create a label for the content text
	  Label contentLabel = new Label(label);
	  contentLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 14px;");
    
	  //Set the custom label as the content of the alert
	  alert.getDialogPane().setContent(contentLabel);
	  alert.getDialogPane().setStyle("-fx-background-color: #F8FFFF; -fx-border-color: #92bce3; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 10px;");
	
	  //Style the header text
	  alert.getDialogPane().lookup(".header-panel").setStyle("-fx-background-color: #92bce3; -fx-background-radius: 10px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");
	
	  //Set button styles and add hover effects
	  alert.getDialogPane().getButtonTypes().stream()
      .map(alert.getDialogPane()::lookupButton)
      .forEach(button -> {
          button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";");
          button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #4096EE; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
          button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #92bce3; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 7px; -fx-font-family: \"Comic Sans MS\";"));
      });

      Optional<ButtonType> option = alert.showAndWait();
      
      if (option.isPresent()) {
          if (option.get() == ButtonType.OK) {
              if (onOkAction != null) {
                  onOkAction.run();
              }
          } else if (option.get() == ButtonType.CANCEL) {
              if (onCancelAction != null) {
                  onCancelAction.run();
              }
          }
      }
   }
	
    /**
     * Sets the action to be executed when the cancel button is clicked.
     *
     * @param onCancelAction the action to be executed
     */
	public void setOnCancelAction(Runnable onCancelAction) {
        this.onCancelAction = onCancelAction;
    }

    /**
     * Sets the action to be executed when the OK button is clicked.
     *
     * @param onOkAction the action to be executed
     */
    public void setOnOkAction(Runnable onOkAction) {
        this.onOkAction = onOkAction;
    }
}