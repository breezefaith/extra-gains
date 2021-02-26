# Minimum Spanning Tree visualisation

This is a simple graphical user interface to demonstrate the implementation of Prim algorithms to find the MST for a weighted graph. 

In this project, I defined three classes - Graph, Edge and Position - to describe the data structure Graph in model package, and I've also defined a custom comparator EdgeComparator to compare the weight of Edge. In the algorithm package, I defined a MST result class MSTResult and a the executor class PrimExecutor to execute the PRIM algorithm.

The user interface is divided into two parts, the top toolbar and the main drawing area. The toolbar includes a starting point input box, a button to execute the PRIM algorithm, and a reset button, and the main area is used to display the graph.