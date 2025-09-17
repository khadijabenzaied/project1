package Pack;


public class verification_functions {
    public static boolean verifEmail(String email){
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
    public static boolean veriflogin(String login){
        return login.length() > 4 && login.length() < 20 ;
    }
    public static boolean verifPassword(String password){
        return password.length() > 6  && password.length() < 20;
    }
    public static boolean isNumber(String inputS) {
        try {
            Double.parseDouble(inputS);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean verifyLicense(String input1S, String input2S) {
        int license1S, license2S;
        try {
            license1S = Integer.parseInt(input1S);
            license2S = Integer.parseInt(input2S);
        } catch (NumberFormatException ex) {
            return false;
        }

        return license1S >= 0 && license1S <= 400 && license2S >= 0 && license2S < 10000;
    }

    public static String replace(String str){
        if (str.isEmpty()) {
            str = "%";
        } else {
            str = "%" + str + "%";
        }
        return str;
    }

}


