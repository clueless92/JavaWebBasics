public class Test {

    public static void main(String[] args) {
        String type = "Content-Type: text/html" +
                System.lineSeparator() +
                System.lineSeparator();
        String output =
                "<html>" +
                    "<head>" +
                        "<title>CGI</title>" +
                    "</head>" +
                    "<body>" +
                        "<b>Hello from CGI!</b>" +
                    "</body>" +
                "</html>";
        System.out.println(type);
        System.out.println(output);

    }
}
