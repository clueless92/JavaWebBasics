package bg.tilchev.bythecake;

import bg.tilchev.WebUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class BrowseCakes {

    public static void main(String[] args) {
        String path = "D:\\OneDrive\\Workspace\\Java\\JavaWebBasics\\" +
                "IntroductionExercises\\res\\html\\browse_cakes.html";
        String html = WebUtils.readWholeFile(path);
        String type = "Content-Type: text/html" + System.lineSeparator();
        System.out.println(type);
        System.out.println(html);
        BrowseCakes.browseCakes();
    }

    // get
    private static void browseCakes() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        String name = parameterMap.get("name");
        List<CakeDto> cakes = new ArrayList<>();
        String filePath = "D:\\Programs\\Apache24\\cgi-bin\\database.csv";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String cakeName = tokens[0];
                BigDecimal cakePrice = new BigDecimal(tokens[1]);
                CakeDto cake = new CakeDto(cakeName, cakePrice);
                cakes.add(cake);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (CakeDto cake : cakes) {
            String cakeName = cake.getName();
            BigDecimal cakePrice = cake.getPrice();
            if(cakeName.toLowerCase().contains(name.toLowerCase())){
                System.out.println("<p>Name: "+cakeName+" Price: " + cakePrice +"</p>");
            }
        }
    }
}
