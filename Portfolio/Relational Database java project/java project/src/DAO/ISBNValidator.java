package DAO;
/**
 * The purpose of this class is to validate isbn strings
 * @author Chris Hinson
 * code inspired By Steve Claridge
 * on https://www.moreofless.co.uk/validate-isbn-13-java/
 * and https://www.moreofless.co.uk/validate-isbn-10-java/
 * accessed on 10/12/2019
 */
public class ISBNValidator {
    /**
     * Constructor, no arguments to construct the isbn validator
     */
    public ISBNValidator(){}

    /**The purpose of this function is to validate isbn 13 strings and isbn 10 strings
     * @param isbn a string respresenting a potential isbn number
     * @return a boolean validating if that is an acceptable isbn or not
     */
    public boolean validateIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }

        //remove any hyphens
        isbn = isbn.replaceAll("-", "");

        //must be a 10 digit ISBN
        if (isbn.length() == 10) {
            try {
                int tot = 0;
                for (int i = 0; i < 9; i++) {
                    int digit = Integer.parseInt(isbn.substring(i, i + 1));
                    tot += ((10 - i) * digit);
                }

                String checksum = Integer.toString((11 - (tot % 11)) % 11);
                if ("10".equals(checksum)) {
                    checksum = "X";
                }
                return checksum.equals(isbn.substring(9));
            } catch (NumberFormatException nfe) {
                //to catch invalid ISBNs that have non-numeric characters in them
                return false;
            }
        }
        if (isbn.length() == 13) {
            try {
                int tot = 0;
                for (int i = 0; i < 12; i++) {
                    int digit = Integer.parseInt(isbn.substring(i, i + 1));
                    tot += (i % 2 == 0) ? digit * 1 : digit * 3;
                }

                //checksum must be 0-9. If calculated as 10 then = 0
                int checksum = 10 - (tot % 10);
                if (checksum == 10) {
                    checksum = 0;
                }

                return checksum == Integer.parseInt(isbn.substring(12));
            } catch (NumberFormatException nfe) {
                //to catch invalid ISBNs that have non-numeric characters in them
                return false;
            }
        } else{
            return false;
        }
    }
}