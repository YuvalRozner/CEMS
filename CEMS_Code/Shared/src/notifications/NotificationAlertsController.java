package notifications;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class NotificationAlertsController{
	
	private Runnable onCancelAction;
    private Runnable onOkAction;

	//Show a Information Alert
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
	
	//Show a Warning Alert
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
	
	//Show a Error Alert
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
	
	//Show a Confirmation Alert
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
	
	public void setOnCancelAction(Runnable onCancelAction) {
        this.onCancelAction = onCancelAction;
    }

    public void setOnOkAction(Runnable onOkAction) {
        this.onOkAction = onOkAction;
    }


}
