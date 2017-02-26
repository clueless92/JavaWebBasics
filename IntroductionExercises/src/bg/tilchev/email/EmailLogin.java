package bg.tilchev.email;

import bg.tilchev.WebUtils;

import java.util.Map;

public class EmailLogin {

    public static void main(String[] args) {
        printHttpHeader();
        printHtml();
    }

    private static void printHtml() {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\email_login.html";
        String html = WebUtils.readWholeFile(path);
        System.out.println(html);
        if (hasError()) {
            System.out.println("Invalid username or password!");
        }
    }

    private static void printHttpHeader() {
        String type = "Content-Type: text/html" + System.lineSeparator();
        System.out.println(type);
    }

    private static boolean hasError() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        String error = parameterMap.get("error");
        return "true".equals(error);
    }
}
