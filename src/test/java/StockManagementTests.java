import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    private ExternalISBNDataService externalWebISBNDataService;
    private ExternalISBNDataService externalDBISBNDataService;
    private StockManager stockManager;

    @BeforeEach
    public void setup() {
        externalWebISBNDataService = mock(ExternalISBNDataService.class);
        externalDBISBNDataService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setExternalWebISBNDataService(externalWebISBNDataService);
        stockManager.setExternalDBISBNDataService(externalDBISBNDataService);
    }

    @Test
    public void canGetACorrectLocatorCode(){
        String isbn = "1040177396";
        // standard stub implementation
        //ExternalISBNDataService externalWebISBNDataService = isbn -> new Book(isbn,"Of Mice And Men", "J. Steinbeck");
        // Mockito stub implementation
        when(externalWebISBNDataService.lookup(isbn)).thenReturn(new Book(isbn,"Of Mice And Men", "J. Steinbeck"));
        //ExternalISBNDataService externalDBISBNDataService = isbn -> null;
        when(externalDBISBNDataService.lookup(isbn)).thenReturn(null);

        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4",locatorCode);
    }
    @Test
    public void databaseIsUsedIfDataIsPresent() {
        String isbn = "1040177396";
        when(externalDBISBNDataService.lookup(isbn)).thenReturn(new Book(isbn,"abc","def"));
        String locatorCode = stockManager.getLocatorCode(isbn);
        //assertEquals("7396J4",locatorCode);
        verify(externalDBISBNDataService,times(1)).lookup(isbn);
        //verify(webService,never()).lookup(anyString());
        verify(externalWebISBNDataService,times(0)).lookup(anyString());

    }
    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        String isbn = "1040177396";
        when(externalDBISBNDataService.lookup(isbn)).thenReturn(null);
        when(externalWebISBNDataService.lookup(isbn)).thenReturn(new Book(isbn,"abc", "abc"));
        String locatorCode = stockManager.getLocatorCode(isbn);
        //we can also use atLeast(2), atMost(7), never() methods as well. times(1) is a default if no method is used
        verify(externalDBISBNDataService).lookup(isbn);
        //verify(dbService,times(1)).lookup(isbn);
        verify(externalWebISBNDataService).lookup(isbn);
        //verify(webService,times(1)).lookup(isbn);
    }
}
