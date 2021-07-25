package bean;

public class ImportHolder {
	private Import importItem;
	private final static ImportHolder IMPORT_INSTANCE= new ImportHolder();

	public Import getImportItem() {
		return importItem;
	}
	public void setImportItem(Import importItem) {
		this.importItem = importItem;
	}

	public static ImportHolder getImportInstance() {
		return IMPORT_INSTANCE;
	}
}
