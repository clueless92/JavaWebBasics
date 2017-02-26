package bg.tilchev.login;

import bg.tilchev.WebUtils;

import java.util.Map;

public class Login {

    public static void main(String[] args) {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\login.html";
        String html = WebUtils.readWholeFile(path);
        String type = "Content-Type: text/html" + System.lineSeparator();
        System.out.println(type);
        System.out.println(html);
        Login.login();
    }

    // post
    private static void login() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        String username = parameterMap.get("username");
        String password = parameterMap.get("password");
        System.out.println("<p>Hi, " + username + " your password is " + password + "</p>");
    }
}
