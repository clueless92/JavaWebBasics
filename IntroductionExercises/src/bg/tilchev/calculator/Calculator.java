package bg.tilchev.calculator;

import bg.tilchev.WebUtils;

import java.util.Map;

public class Calculator {

    public static void main(String[] args) {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\calculator.html";
        String html = WebUtils.readWholeFile(path);
        String type = "Content-Type: text/html" + System.lineSeparator();
        System.out.println(type);
        System.out.println(html);
        Calculator.calculate();
    }

    // get
    private static void calculate() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        double n1 = Double.parseDouble(parameterMap.get("n1"));
        String operator = parameterMap.get("operator");
        double n2 = Double.parseDouble(parameterMap.get("n2"));
        double r = 0d;
        String result = null;
        switch (operator) {
            case "%2F":
                r = n1 / n2;
                break;
            case "*":
                r = n1 * n2;
                break;
            case "%2B":
                r = n1 + n2;
                break;
            case "-":
                r = n1 - n2;
                break;
            default:
                result = "Invalid Sign";
                break;
        }
        if(result == null) {
            result = "Result: " + r;
        }
        System.out.println("<p>" + result + "</p>");
    }
}
