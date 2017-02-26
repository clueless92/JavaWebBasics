package bg.tilchev.greetings;

import bg.tilchev.WebUtils;
import java.io.*;
import java.util.Map;

public class Greetings {

    private static final String GREETING_FILE_PATH = "greetings.txt";

    public static void main(String[] args) {
        printHttpHeader();
        printHtml();
        System.out.println(System.getProperty("user.dir"));
        writeParam();
        int lineCount = getFileLineCount();
        if (lineCount == 0) {
            printForm("First Name");
        } else if (lineCount == 1) {
            printForm("Last Name");
        } else if (lineCount == 2) {
            printForm("Age");
        } else {
            printGreeting();
            clearFileContents();
        }
    }

    private static void printGreeting() {
        String[] lines = WebUtils.readWholeFile(GREETING_FILE_PATH).split(System.lineSeparator());
        String greeting = String.format("<h1>Hello %s %s at age %s!</h1>",
                lines[0], lines[1], lines[2]);
        System.out.println(greeting);
    }

    private static void clearFileContents() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(GREETING_FILE_PATH, false));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getFileLineCount() {
        BufferedReader reader = null;
        int lineCount = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(GREETING_FILE_PATH)));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineCount;
    }

    // post
    private static void writeParam() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        String field = parameterMap.get("field");
        if (field == null) {
            return;
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(GREETING_FILE_PATH, true));
            bw.write(field);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printForm(String label) {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\greetings_form.html";
        String html = String.format(WebUtils.readWholeFile(path), label);
        System.out.println(html);
    }

    private static void printHtml() {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\greetings.html";
        String html = WebUtils.readWholeFile(path);
        System.out.println(html);
    }

    private static void printHttpHeader() {
        String type = "Content-Type: text/html" + System.lineSeparator();
        System.out.println(type);
    }
}
