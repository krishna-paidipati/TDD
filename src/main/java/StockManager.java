public class StockManager {
    private ExternalISBNDataService externalWebISBNDataService;
    private ExternalISBNDataService externalDBISBNDataService;

    public void setExternalDBISBNDataService(ExternalISBNDataService externalDBISBNDataService) {
        this.externalDBISBNDataService = externalDBISBNDataService;
    }
    public void setExternalWebISBNDataService(ExternalISBNDataService externalWebISBNDataService) {
        this.externalWebISBNDataService = externalWebISBNDataService;
    }
    public String getLocatorCode(String isbn) {
        Book book = externalDBISBNDataService.lookup(isbn);
        if (book == null) book = externalWebISBNDataService.lookup(isbn);
        StringBuilder builder = new StringBuilder();
        builder.append(isbn.substring(isbn.length()-4));
        builder.append(book.getAuthor().substring(0,1));
        builder.append(book.getTitle().split(" ").length);
        return builder.toString();
    }
}
