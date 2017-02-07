package texboard;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;

public class MLNode {
    private String type;
    private String value;
    private MLNode[] childs;

    public MLNode(Node node) {
        type = node.getNodeName();
        value = node.getTextContent();

        NodeList childNodes = node.getChildNodes();
        int childCount = childNodes.getLength();
        childs = new MLNode[childCount];

        for (int i = 0; i < childCount; i++) {
            childs[i] = new MLNode(childNodes.item(i));
        }
    }

    @Override
    public String toString() {
        if (childs.length == 0) {
            return "(" + type + " : " + value + ")";
        } else {
            return "(" + type + " : " + Arrays.toString(childs) + ")";
        }
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public MLNode[] getChilds() {
        return childs;
    }
}
