package data;

public interface CorpusLoader {
	public abstract Corpus load() throws CorpusLoaderException;
}
