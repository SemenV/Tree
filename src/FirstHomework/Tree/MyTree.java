package FirstHomework.Tree;

import java.util.ArrayList;

public class MyTree {
    private Node root = new Node(); //Node with empty data

    public void setAllString(String... e){
        for (String element: e) {
            setString(element);
        }
    }

    public void setString(String str) {
        if (isPresent(str)) throw new IllegalArgumentException("Please stop");
        Node e = root;
        for (int i = 0; i < str.length(); i++) {
            Character iSymbol = str.charAt(i);
            Node x = new Node(iSymbol,e);
            e.addChild(x);
            e = e.getChild(iSymbol);
        }
    }

    public boolean isPresent(String str) {
        try {
            Node e = getLastNode(str);
            e.getParent();
        } catch (Exception e) {
            return false;
        }
        Node last = getLastNode(str);
        return last.getParent().getChildCount(str.charAt(str.length() - 1)) - last.getCountEnds() != 0;
    }

    public void removeString(String str) {
        if (!isPresent(str)) throw new IllegalArgumentException("Please stop");
        Node e = getLastNode(str);
        while (e.getParent() != null) {
            Character symbol = e.getName();
            e = e.getParent();
            e.removeChild(symbol);
        }
    }

    public ArrayList<String> getString(String line) {
        ArrayList<Node> nodeList = this.getEndsNodes(line);
        ArrayList<String> end = new ArrayList<String>();
        for (int i = 0; i < nodeList.size(); i++) {
            Node element = nodeList.get(i);
            StringBuilder str = new StringBuilder();
            while (element.getParent() != null) {
                str.append(element.getName());
                element = element.getParent();
            }
            end.add(str.reverse().toString());
        }
        return end;
    }


    private Node getLastNode(String str) {
        Node e = root;
        for (int i = 0; i < str.length(); i++) {
            Character iSymbol = str.charAt(i);
            e = e.getChild(iSymbol);
        }
        return e;
    }

    private ArrayList<Node> getEndsNodes(String str) {
        Node e = getLastNode(str);
        ArrayList<Node> ends = new ArrayList<Node>();
        ArrayList<Node> unused = new ArrayList<Node>();
        unused.add(e);
        Integer count = e.getCountEnds();
        if (isPresent(str)) count++;
        while (ends.size() != count) {
            for (int i = 0; i < unused.size(); i++) {
                Node k = unused.get(i);
                Character iSymbol = k.getName();
                if (k.getParent().getChildCount(iSymbol) - k.getCountEnds() != 0) {
                    ends.add(k);
                }
                unused.addAll(k.getChildren().values());
                unused.remove(k);
            }
        }
        return ends;
    }

}