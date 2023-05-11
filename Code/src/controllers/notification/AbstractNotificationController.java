package controllers.notification;

public abstract class AbstractNotificationController {

	/**
	 * 
	 * @param phoneNumber
	 * @param text
	 */
	public boolean sendSMS(string phoneNumber, String text) {
		// TODO - implement AbstractNotificationController.sendSMS
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param text
	 */
	public boolean sendMail(string mail, string text) {
		// TODO - implement AbstractNotificationController.sendMail
		throw new UnsupportedOperationException();
	}

}