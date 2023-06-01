package controllers.notification;

public abstract class AbstractNotificationController {

	/**
	 * 
	 * @param phoneNumber
	 * @param text
	 */
	public boolean sendSMS(String phoneNumber, String text) {
		// TODO - implement AbstractNotificationController.sendSMS
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param text
	 */
	public boolean sendMail(String mail, String text) {
		// TODO - implement AbstractNotificationController.sendMail
		throw new UnsupportedOperationException();
	}

}