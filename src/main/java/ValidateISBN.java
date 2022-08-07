public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int LONG_ISBN_MULTIPLIER = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;

    public boolean validate(String isbn) {
        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isThisAValidLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isThisAValidShortISBN(isbn);
        }else {
            throw new NumberFormatException("ISBN Numbers must be 10 or 13 digit long");
        }
    }

    private boolean isThisAValidShortISBN(String isbn) {
        int total = 0;
        for (int i = 0; i< SHORT_ISBN_LENGTH; i++){
            if (!Character.isDigit(isbn.charAt(i))) {
                if( i == 9 && (isbn.charAt(9)) == 'X') {
                    total += SHORT_ISBN_LENGTH;
                } else {
                    throw new NumberFormatException("ISBN characters can't be non digits ");
                }
            } else {
                //total += isbn.charAt(i)*(10-i);
                total += Character.getNumericValue(isbn.charAt(i))*(SHORT_ISBN_LENGTH -i);
            }
        }
        return total % SHORT_ISBN_MULTIPLIER == 0;
    }

    private boolean isThisAValidLongISBN(String isbn) {
        int total = 0;
        for (int i = 0; i< LONG_ISBN_LENGTH; i++){
            if (!Character.isDigit(isbn.charAt(i))) {
                throw new NumberFormatException("ISBN characters can't be non digits ");
            } else if (i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            }else{
                //total += isbn.charAt(i)*(10-i);
                total += Character.getNumericValue(isbn.charAt(i))*3;
            }
        }
        return total % LONG_ISBN_MULTIPLIER == 0;//total % LONG_ISBN_MULTIPLIER == 0 ? true : false;
    }
}
