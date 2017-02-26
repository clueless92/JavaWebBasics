package bg.tilchev.bythecake;

import bg.tilchev.WebUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddCake {

    private static final List<CakeDto> cakes = new ArrayList<>();

    public static void main(String[] args) {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\add_cake.html";
        String html = WebUtils.readWholeFile(path);
        String type = "Content-Type: text/html" + System.lineSeparator();
        System.out.println(type);
        System.out.println(html);
        AddCake.addCake();
    }

    // post
    private static void addCake() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
            System.out.println("<p>" + entry.getKey() + ": ");
            System.out.println(entry.getValue() + "</p>");
        }
        String name = parameterMap.get("name");
        BigDecimal price = new BigDecimal(parameterMap.get("price"));
        CakeDto cake = new CakeDto(name, price);
        cakes.add(cake);
        final String filePath = "D:\\Programs\\Apache24\\cgi-bin\\database.csv";
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.write(cake.getName() + "," + cake.getPrice() + "\n");
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
}
