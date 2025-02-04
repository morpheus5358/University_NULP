package Lab2.task1;
import java.util.*;

public class GraphMethods {
    private final List<Top> tops;
    private final List<Node> nodes;

    public GraphMethods() {
        tops = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    public void addTop(String name, int value) {
        for (Top top : tops) {
            if (top.getName().equals(name) ) {
                System.out.println("Top with the name '" + name + "' already exists!");
                return; // Якщо така вершина вже є, не додаємо її
            }
        }
        final Top top = new Top(name, value);
        tops.add(top);
    }

    public void addNode(String from, String to, boolean isDirected) {
        final Top topFrom = findTop(from);
        final Top topTo = findTop(to);

        if (topFrom != null && topTo != null) {
            for (Node node : nodes) {
                if ((node.getFrom().equals(topFrom) && node.getTo().equals(topTo)) ||
                        (node.getFrom().equals(topTo) && node.getTo().equals(topFrom))) {
                    System.out.println("Node between '" + from + "' and '" + to + "' already exists!");
                    return;
                }
            }
            final Node node = new Node(topFrom, topTo, isDirected);
            nodes.add(node);
        } else {
            System.out.println("One or both tops not found!");
        }
    }

    private Top findTop(String name) {
        for (final Top top : tops) {
            if (top.getName().equals(name) ) {
                return top;
            }
        }
        return null;
    }

    public void displayGraph() {
        System.out.println("Tops:");
        for (final Top top : tops) {
            System.out.println(top);
        }

        System.out.println("\nNodes:");
        for (final Node node : nodes) {
            System.out.println(node);
        }
    }

    public void displayGraphFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        int numberOfTops;
        // Перевірка, щоб кількість вершин була більше нуля
        do {
            System.out.println("Enter the number of tops (must be greater than 0):");
            numberOfTops = scanner.nextInt();
            if (numberOfTops <= 0) {
                System.out.println("Invalid number! The number of tops must be greater than 0.");
            }
        } while (numberOfTops <= 0);

        scanner.nextLine();

        for (int i = 0; i < numberOfTops; i++) {
            System.out.println("Enter top name and value (e.g., Name 10):");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String name = parts[0];
            int value = Integer.parseInt(parts[1]);
            addTop(name, value);
        }

        System.out.println("Enter the number of edges:");
        int numberOfEdges = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println("Enter node (From To Directed(1 or 0)):");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String from = parts[0];
            String to = parts[1];
            boolean isDirected = parts[2].equals("1");
            addNode(from, to, isDirected);
        }

        displayGraph();
    }
}




















    /*public int findShortestPathBFS(String startName, String endName) {
        Top start = findTop(startName);
        Top end = findTop(endName);

        if (start == null || end == null) {
            throw new IllegalArgumentException("One or both tops not found!");
        }

        Queue<Top> queue = new LinkedList<>();
        Map<Top, Integer> distances = new HashMap<>();
        Map<Top, Boolean> visited = new HashMap<>();

        for (Top top : tops) {
            distances.put(top, 0);
            visited.put(top, false);
        }

        distances.put(start, 0);
        visited.put(start, true);
        queue.add(start);

        // BFS алгоритм
        while (!queue.isEmpty()) {
            Top current = queue.poll();

            // Якщо знайшли кінцеву вершину, повертаємо відстань
            if (current.equals(end)) {
                return distances.get(current);
            }

            // Проходимо суміжні вершини
            for (Top neighbor : getNeighbors(current)) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    distances.put(neighbor, distances.get(current) + 1);
                    queue.add(neighbor);
                }
            }
        }

        return -1;
    }
    private List<Top> getNeighbors(Top top) {
        List<Top> neighbors = new ArrayList<>();
        for (Node node : nodes) {
            if (node.getFrom().equals(top)) {
                neighbors.add(node.getTo());
            } else if (!node.isDirected() && node.getTo().equals(top)) {
                neighbors.add(node.getFrom());
            }
        }
        return neighbors;
    }*/
