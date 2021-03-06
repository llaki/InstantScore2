package subscribtion;

import com.twilio.sdk.TwilioRestException;

public class MsgCodes {

	public static final String getErrorMessageForCode(TwilioRestException e) {
		int errorCode = e.getErrorCode();
		ErrorNotification errorNotif = ErrorNotification.getErrorNotificationViaErroCode(errorCode);
		return errorNotif.getMessageText();
	}
	
	private enum ErrorNotification {
		
		FROM_NUMBER_INVALID (21212, "The \"From\" phone number is invalid."),
		FROM_NOT_YOUR_OWNED_NUMBER(21606, "The \"From\" phone number is not owned by your account or is not SMS-capable."),
		FROM_QUEUE_FULL(21611, "The \"From\" phone number has an SMS message queue that is full."),
		TO_NUMBER_INVALID(21211, "The \"To\" phone number is invalid."),
		TO_NUMBER_REQUIRED(21604, "The \"To\" phone number is required."),
		CANNOT_ROUTE(21612, "Twillio cannot route to this number."),
		NO_INTERNATIONAL_PERMISSIONS(21408, "This account doesn't have the international permissions necessary to SMS this number."),
		BLACKLISTED_NUMBER(21610, "This number is blacklisted for your account."),
		INCAPABLE_NUMBER(21614, "This number is incapable of receiving SMS messages."),
		OTHER_CASE(-1, "Something went wrong.");
		
		
		private final int errorCode;

		private final String messageText;
		
		private ErrorNotification(int errorCode, String messageText) {
			this.errorCode = errorCode;
			this.messageText = messageText;
		}
		
		public int getErrorCode() {
			return errorCode;
		}
		
		public String getMessageText() {
			return messageText;
		}

		public static ErrorNotification getErrorNotificationViaErroCode(int errorCode) {
			for(ErrorNotification errorNotif : ErrorNotification.values()) {
				if(errorNotif.getErrorCode() == errorCode) {
					return errorNotif;
				}
			}
			return ErrorNotification.OTHER_CASE;
		}
		
	}

}
