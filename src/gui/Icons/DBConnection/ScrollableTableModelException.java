/*
 * Created on 09-jul-2004
 */
package gui.Icons.DBConnection;

/**
 * Exception used in <code>ScrollableTableModel</code>.
 * @author Francesc Ros�s
 */
public class ScrollableTableModelException extends RuntimeException {
	/**
	 * 
	 */
	public ScrollableTableModelException() {
		super();
	}

	/**
	 * @param message
	 */
	public ScrollableTableModelException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ScrollableTableModelException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ScrollableTableModelException(String message, Throwable cause) {
		super(message, cause);
	}
}
