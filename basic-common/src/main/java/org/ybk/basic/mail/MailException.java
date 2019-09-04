package org.ybk.basic.mail;


/**
 * Fatal infrastructure failure.  This exception should be called only
 * when the full system gave up.
 * @author  <a href="mailto:info@rumble.be>rumble</a>
 * @version 1.0.0
 */
public class MailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4764584999433377349L;

	public MailException() {
	}

	public MailException(String message) {
		super(message);
	}

	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailException(Throwable cause) {
		super(cause);
	}
    
}
