package data;

public class CorpusLoaderException extends Exception {
	private static final long serialVersionUID = 11121121;

	public CorpusLoaderException(String message) {
		super(message);
	}
	
	public CorpusLoaderException(String message, Throwable cause) {
		super(message, cause);
	}
}
