package bg.tilchev.bythecake;

import bg.tilchev.WebUtils;

public class Home {

    public static void main(String[] args) {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\home.html";
        String html = WebUtils.readWholeFile(path);
        String type = "Content-Type: text/html" + System.lineSeparator();
        System.out.println(type);
        System.out.println(html);
    }
}
