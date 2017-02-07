package texboard;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TeXParser parser = new TeXParser();
        HwpConverter converter = new HwpConverter();
        ClipboardManager manager = new ClipboardManager();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input TeX formula : ");
        String formula = scanner.nextLine();

        MLNode node = parser.parse("$" + formula + "$");
        System.out.println("Parsed the formula and generated TeXNode instance : " + node.toString());

        String hwp = converter.convert(node);
        System.out.println("Converted the formula to Hwp style formula : " + hwp);

        manager.copyString(hwp);
        System.out.println("Copied the formula to clipboard.");
    }
}
