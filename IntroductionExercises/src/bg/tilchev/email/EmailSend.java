package bg.tilchev.email;

import bg.tilchev.WebUtils;

import java.util.Map;

public class EmailSend {

    private static final String USERNAME = "suAdmin";
    private static final String PASSWORD = "aDmInPw17";

    public static void main(String[] args) {
        boolean redirect = EmailSend.hasError();
        EmailSend.printHttpHeaders(redirect);
        EmailSend.printHtml();
        EmailSend.sendEmail();
    }

    private static void printHtml() {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\email.html";
        String html = WebUtils.readWholeFile(path);
        System.out.printf(html, USERNAME);
    }

    // post
    private static boolean hasError() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        String username = parameterMap.get("username");
        String password = parameterMap.get("password");
        return !(USERNAME.equals(username) && PASSWORD.equals(password));
    }

    private static void printHttpHeaders(boolean shouldRedirect) {
        String type = "Content-Type: text/html";
        System.out.println(type);
        if (shouldRedirect) {
            String location = "Location: email_login.cgi?error=true";
            System.out.println(location);
        }
        System.out.println();
    }

    // get
    private static void sendEmail() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        String to = parameterMap.get("to");
        String subject = parameterMap.get("subject");
        String message = parameterMap.get("message");
        // send mail


        System.out.println("Email sent successfully!");
    }
}
