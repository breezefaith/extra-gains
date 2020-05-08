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