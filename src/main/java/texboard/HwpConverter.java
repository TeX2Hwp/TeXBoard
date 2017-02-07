package texboard;

public class HwpConverter {
    public HwpConverter() {
    }

    public String convert(MLNode node) {
        MLNode[] childs = node.getChilds();

        switch (node.getType()) {
            // root
            case "math":
                return convert(node.getChilds());

            // horizontal row
            case "mrow":
                return convert(node.getChilds());

            // fraction
            case "mfrac":
                return String.format("{ %s } over { %s }", convert(childs[0]), convert(childs[1]));

            // radicals
            case "msqrt":
                return String.format("sqrt { %s }", convert(childs[0]));
            case "mroot":
                return String.format("root { %s } of { %s }", convert(childs[1]), convert(childs[0]));

            // grouping with parenthesis
            case "mfenced":
                return String.format("( %s )", convert(childs));

            // indices
            case "msup":
                return String.format("{ %s } ^ { %s }", convert(childs[0]), convert(childs[1]));
            case "msub":
                return String.format("{ %s } _ { %s }", convert(childs[0]), convert(childs[1]));
            case "msubsup":
                return String.format("{ %s } _ { %s } ^ { %s }", convert(childs[0]), convert(childs[1]), convert(childs[2]));

            // bars and arrows
            case "munder":
                // TODO : convert munder
            case "mover":
                // TODO : convert mover
            case "munderover":
                // TODO : convert munderover

            default:
                if (node.getChilds().length == 0) {
                    return node.getValue();
                } else {
                    return convert(node.getChilds());
                }
        }
    }

    public String convert(MLNode[] nodes) {
        StringBuilder builder = new StringBuilder();
        int nodeCount = nodes.length;

        for (int i = 0; i < nodeCount - 1; i++) {
            builder.append(convert(nodes[i]));
            builder.append(" ");
        }

        if (nodeCount > 0) {
            builder.append(convert(nodes[nodeCount - 1]));
        }

        return builder.toString();
    }

    private String putSpaces(String string) {
        StringBuilder builder = new StringBuilder();
        int length = string.length();

        for (int i = 0; i < length - 1; i++) {
            builder.append(string.charAt(i));
            builder.append(" ");
        }

        if (length > 0) {
            builder.append(string.charAt(length - 1));
        }

        return builder.toString();
    }
}
