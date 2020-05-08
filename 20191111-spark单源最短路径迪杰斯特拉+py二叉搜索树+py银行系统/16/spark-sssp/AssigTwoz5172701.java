/**
 * There are four classes in this file: Edge, Graph, IntegerComparator, and AssigTwoz5172701.The Edge, Graph, and IntegerComparator all implement the Serializable interface.
 *
 * The Edge class represents each Edge in a directed graph, including the starting point, the ending point, target, and weight.
 *
 * The Graph class represents a directed Graph, which includes adjList(a list about Edge), verNum(the number of vertices), edgeNum(the number of edges),
 * start(the source vertex for calculating the single source shortest pathes), dis(an array of the distance between start and vertice i), and pre(an
 * array of the precursor node of vertice i about the source vertex.
 *
 * public method list:
 * Graph(int vnum): to create a directed Graph object;
 * void insertEdge(String startStr, String targetStr, String weightStr): to insert an edge into the directed graph object.
 * void traverse(): to view the directed graph;
 * void dijkstra(String startStr): to set the source vertex, and calculate the shortest path and distance to each vertex;
 * String getSortedStringResult(): to get the sorted String that matches the output format.
 * LinkedList<Tuple2<Integer, Tuple3<String, Integer, String>>> getSortedTupleResult(): to get the sorted tuple about the shortest distance and shortest path.
 * LinkedList<Tuple2<Integer, Tuple3<String, Integer, String>>> getUnsortedTupleResult(): to get the unsorted tuple about the shortest distance and shortest path.
 *
 * The IntegerComparator class is a custom comparator for two Integer objects.
 *
 * The AssigTwoz5172701 class contains the main method, which is the program's startup class.
 * The execution process of the program is as follows:
 * (0) check whether the number of parameters of the program is three, if not, exit the program directly. Otherwise get the value of the source vertex, the input file path,
 * and the output file path in the parameter, and then create the JavaSparkContext.
 * (1) read the input file and create a JavaRDD<String>.
 * (2) decompose each row into tuples containing three strings, and get an object of JavaRDD<Tuple3<String, String, String>>.
 * (3) create a HashSet object to calculate the number of vertices.
 * (4) calculate the number of vertices. Insert start and target from each tuple of (2) into the set successively, and the maximum value of the final set is the number of vertices.
 * (5) create the directed graph object, iterate through the collection obtained in (2), and add it to the graph in turn.
 * (6) use dijkstra(String startStr) to execute the algorithm and calculate the graph's single source shortest pathes.
 * (7) convert the result of getUnsortedTupleResult() into JavaPairRDD<Integer, Tuple3<String, Integer, String>>.
 * (8) sort by distance and get the formatted tuple list then save to file.
 */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;
import scala.Tuple3;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Consumer;

class Edge implements Serializable {
    private int start;
    private int target;
    private int weight;

    public Edge(int v1, int target, int weight) {
        this.start = v1;
        this.target = target;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean equals(Edge edge) {
        return this.start == edge.getStart() && this.target == edge.getTarget() && this.weight == edge.getWeight();
    }

    public String toString() {
        String str = "[ " + start + " , " + target + " , " + weight + " ]";
        return str;
    }
}

class Graph implements Serializable {
    private LinkedList<Edge>[] adjList;
    private int verNum;    //vertex number
    private int edgeNum;    //edges number
    private int[] dis;    //store the distance between start and i
    private int[] pre;    //store the precursor of i
    private LinkedList<Integer> s;    //vertex set which we have found the shortest path
    private LinkedList<Integer> q;    //vertex set which we haven't found the shortest path
    private int start;
    public static final int INF = Integer.MAX_VALUE;
    public static final int NIL = -1;    //Not existing

    public Graph(int vnum) {
        this.verNum = vnum;
        adjList = new LinkedList[vnum];
        edgeNum = 0;
        dis = new int[vnum];
        pre = new int[vnum];
        for (int i = 0; i < vnum; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void insertEdge(String startStr, String targetStr, String weightStr) {
        int start = Integer.parseInt(startStr.substring(1));
        int target = Integer.parseInt(targetStr.substring(1));
        int weight = Integer.parseInt(weightStr);

//        for (int i = 0; i < adjList[start].size(); i++) {
//            if(adjList[start].get(i).getTarget() == target && adjList[start].get(i).getWeight() > weight){
//                adjList[start].get(i).setWeight(weight);
//                return;
//            }
//        }
        adjList[start].add(new Edge(start, target, weight));
        edgeNum++;
    }

    public void traverse() {
        System.out.println("There are " + verNum + " vertexes, and " + edgeNum + " edges.");
        for (int i = 0; i < verNum; i++) {
            LinkedList<Edge> list = (LinkedList<Edge>) adjList[i].clone();
            while (!list.isEmpty()) {
                Edge edge = list.pop();
                System.out.println(edge.toString());
            }
        }
    }

    /**
     * initialize the shortest path estimation and the precursor node
     *
     * @param start
     */
    private void initialize(int start) {
        this.start = start;
        for (int i = 0; i < verNum; i++) {
            dis[i] = INF;
            pre[i] = NIL;
        }
        dis[start] = 0;
    }

    /**
     * @param edge
     */
    private void relax(Edge edge) {
        int v1 = edge.getStart();
        int v2 = edge.getTarget();
        int w = edge.getWeight();
        if (dis[v2] > dis[v1] + w) {
            dis[v2] = dis[v1] + w;
            pre[v2] = v1;
        }
    }

    /**
     * @param startStr
     */
    public void dijkstra(String startStr) {
        int start = Integer.parseInt(startStr.substring(1));
        initialize(start);

        s = new LinkedList<>();
        q = new LinkedList<>();
        for (int i = 0; i < verNum; i++) {
            q.add(i);
        }

        while (!q.isEmpty()) {
            int u = extractMin(q);
            s.add(u);
            LinkedList<Edge> list = (LinkedList<Edge>) adjList[u].clone();
            while (!list.isEmpty()) {
                Edge edge = list.pop();
                relax(edge);
            }
        }
    }

    /**
     * @param q
     * @return
     */
    private int extractMin(LinkedList<Integer> q) {
        if (q.isEmpty())
            return NIL;

        int min = q.getFirst();
        int minIndex = 0;
        for (int i = 0; i < q.size(); i++) {
            int v = q.get(i);
            if (dis[min] > dis[v]) {
                min = v;
                minIndex = i;
            }
        }
        q.remove(minIndex);
        return min;
    }

    /**
     * get sorted string by distance
     *
     * @return
     */
    public String getSortedStringResult() {
        StringBuffer res = new StringBuffer();
        int visitedDisNum = 0;
        boolean[] visitedDis = new boolean[verNum];
        for (int i = 0; i < visitedDis.length; i++) {
            visitedDis[i] = false;
        }
        visitedDis[start] = true;
        visitedDisNum++;

        while (visitedDisNum < verNum) {
            int minDisIndex = -1;
            for (int i = 0; i < verNum; i++) {
                if (visitedDis[i] == false) {
                    minDisIndex = i;
                    break;
                }
            }
            if (minDisIndex == -1) {
                break;
            }

            for (int i = minDisIndex; i < verNum; i++) {
                if (visitedDis[i] == true) {
                    continue;
                }
                if (dis[minDisIndex] > dis[i]) {
                    minDisIndex = i;
                }
            }

            StringBuffer sb = new StringBuffer();

            if (dis[minDisIndex] > 0) {
                LinkedList<Integer> route = new LinkedList<>();
                int j = minDisIndex;
                while (j != NIL) {
                    route.push(j);
                    j = pre[j];
                }

                sb.append("N" + minDisIndex + "," + dis[minDisIndex] + ",");
                while (route.size() > 1) {
                    sb.append("N" + route.pop() + "-");
                }
                sb.append("N" + route.pop());
            } else {
                sb.append("N" + minDisIndex + ",-1");
            }

            sb.append("\n");
            res.append(sb);

            visitedDis[minDisIndex] = true;
            visitedDisNum++;
        }
        return res.toString();
    }

    /**
     * get sorted tuple2 list by distance
     *
     * @return
     */
    public LinkedList<Tuple2<Integer, Tuple3<String, Integer, String>>> getSortedTupleResult() {
        LinkedList<Tuple2<Integer, Tuple3<String, Integer, String>>> list = new LinkedList<>();
        int visitedDisNum = 0;
        boolean[] visitedDis = new boolean[verNum];
        for (int i = 0; i < visitedDis.length; i++) {
            visitedDis[i] = false;
        }
        visitedDis[start] = true;
        visitedDisNum++;

        while (visitedDisNum < verNum) {
            int minDisIndex = -1;
            for (int i = 0; i < verNum; i++) {
                if (visitedDis[i] == false) {
                    minDisIndex = i;
                    break;
                }
            }
            if (minDisIndex == -1) {
                break;
            }

            for (int i = minDisIndex; i < verNum; i++) {
                if (visitedDis[i] == true) {
                    continue;
                }
                if (dis[minDisIndex] > dis[i]) {
                    minDisIndex = i;
                }
            }

            StringBuffer sb = new StringBuffer();
            if (dis[minDisIndex] > 0) {
                LinkedList<Integer> route = new LinkedList<>();
                int j = minDisIndex;
                while (j != NIL) {
                    route.push(j);
                    j = pre[j];
                }

                while (route.size() > 1) {
                    sb.append("N" + route.pop() + "-");
                }
                sb.append("N" + route.pop());

                list.add(new Tuple2<>(dis[minDisIndex], new Tuple3<>("N" + minDisIndex, dis[minDisIndex], sb.toString())));
            } else {
                list.add(new Tuple2<>(-1, new Tuple3<>("N" + minDisIndex, -1, sb.toString())));
            }

            visitedDis[minDisIndex] = true;
            visitedDisNum++;
        }
        return list;
    }

    /**
     * get sorted tuple2 list
     *
     * @return
     */
    public LinkedList<Tuple2<Integer, Tuple3<String, Integer, String>>> getUnsortedTupleResult() {
        LinkedList<Tuple2<Integer, Tuple3<String, Integer, String>>> list = new LinkedList<>();
        LinkedList<Integer>[] routes = new LinkedList[verNum];

        for (int i = 0; i < verNum; i++) {
            if (i == start) {
                continue;
            }
            StringBuffer sb = new StringBuffer();
            if (dis[i] > 0) {
                routes[i] = new LinkedList<>();
                int j = i;
                while (j != NIL) {
                    routes[i].push(j);
                    j = pre[j];
                }

                while (routes[i].size() > 1) {
                    sb.append("N" + routes[i].pop() + "-");
                }
                sb.append("N" + routes[i].pop());
                list.add(new Tuple2<>(dis[i], new Tuple3<>("N" + i, dis[i], sb.toString())));
            } else {
                list.add(new Tuple2<>(-1, new Tuple3<>("N" + i, -1, sb.toString())));
            }
        }
        return list;
    }

    /**
     * print result
     */
    public void showResult() {
        StringBuffer sb = new StringBuffer();
        LinkedList<Integer>[] routes = new LinkedList[verNum];

        for (int i = 0; i < verNum; i++) {
            if (i == start) {
                continue;
            }
            routes[i] = new LinkedList<>();
            int j = i;
            while (j != NIL) {
                routes[i].push(j);
                j = pre[j];
            }

            sb.append("N" + i + "," + dis[i] + ",");
            while (!routes[i].isEmpty()) {
                int k = routes[i].pop();
                sb.append("N" + k + "-");
            }
            sb.append("\b\n");
        }
        System.out.println(sb);
    }
}

/**
 * custom comparator for integer
 */
class IntegerComparator implements Comparator<Integer>, Serializable {
    public int compare(Integer x, Integer y) {
        return x == y ? 0 : (x > y ? 1 : -1);
    }
}

/**
 *
 */
public class AssigTwoz5172701 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.exit(1);
        }

        //get arguments from command line parameters
        String startStr = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        //set environment for debugging
        //System.setProperty("hadoop.home.dir", "D:\\Program Files\\Apache Software Foundation\\hadoop-2.10.0");

        SparkConf conf = new SparkConf()
                .setAppName("AssigTwoz5172701")
                .setMaster("local");
        JavaSparkContext context = new JavaSparkContext(conf);

        //Create RDD
        //step1: read input.txt
        JavaRDD<String> inputRdd = context.textFile(inputFile);

        // step2: split every line to get a tuple list
        JavaRDD<Tuple3<String, String, String>> splitLineRdd = inputRdd.map(
                new Function<String, Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call(String s) throws Exception {
                        String[] strs = s.split(",");
                        return new Tuple3<>(strs[0], strs[1], strs[2]);
                    }
                }
        );

        //step3: count vertex number
        HashSet<String> s = new HashSet<>();

        JavaRDD<Integer> vertexNumRdd = splitLineRdd.map(
                new Function<Tuple3<String, String, String>, Integer>() {
                    @Override
                    public Integer call(Tuple3<String, String, String> tuple3) throws Exception {
                        s.add(tuple3._1());
                        s.add(tuple3._2());
                        return s.size();
                    }
                }
        );
        // vertexNumRdd's max value is the quantity of vertexes
        Integer vertexNum = vertexNumRdd.max(new IntegerComparator());

        //step5: create graph
//        int vertexNum = 6;
        Graph graph = new Graph(vertexNum);

//        graph.insertEdge("N0", "N1", "4");
//        graph.insertEdge("N0", "N2", "3");
//        graph.insertEdge("N1", "N2", "2");
//        graph.insertEdge("N1", "N3", "2");
//        graph.insertEdge("N2", "N3", "7");
//        graph.insertEdge("N3", "N4", "2");
//        graph.insertEdge("N4", "N0", "4");
//        graph.insertEdge("N4", "N1", "4");
//        graph.insertEdge("N4", "N5", "6");

        //insert edges
        splitLineRdd.collect().forEach(
                new Consumer<Tuple3<String, String, String>>() {
                    @Override
                    public void accept(Tuple3<String, String, String> tuple3) {
                        graph.insertEdge(tuple3._1(), tuple3._2(), tuple3._3());
                    }
                }
        );
//        graph.traverse();

        //step6: execute dijkstra()
        graph.dijkstra(startStr);
//        System.out.println(graph.getUnsortedTupleResult());

        //step7: convert step6's result to RDD
        JavaPairRDD<Integer, Tuple3<String, Integer, String>> unsortedTuple3PairRdd = context.parallelizePairs(graph.getUnsortedTupleResult());

        //step8: Sort by distance and get the formatted tuple list then save to file
        JavaRDD<Tuple3<String, Integer, String>> sortedTuple3Rdd = unsortedTuple3PairRdd.sortByKey().values();
        JavaRDD<String> lastStringRdd = sortedTuple3Rdd.map(new Function<Tuple3<String, Integer, String>, String>() {
            @Override
            public String call(Tuple3<String, Integer, String> tuple3) throws Exception {
                if (tuple3._2() == -1) {
                    return tuple3.toString().substring(1, tuple3.toString().length() - 2);
                } else {
                    return tuple3.toString().substring(1, tuple3.toString().length() - 1);
                }
            }
        });
        lastStringRdd.saveAsTextFile(outputFile);
//        System.out.println(outputRdd.sortByKey().values().collect());

//        JavaPairRDD<Integer, Tuple3<String, Integer, String>> outputRdd2 = context.parallelizePairs(graph.getResult());
//        System.out.println(outputRdd2.values().collect());

//        context.close();
    }
}
