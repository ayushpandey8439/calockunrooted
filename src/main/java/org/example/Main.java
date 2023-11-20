package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.graph.graph;
import org.example.graph.vertex;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        graph g = new graph();
        loadGraph(g);
        System.out.println("Graph loaded");
        System.out.println("Number of vertices: " + g.vertices.size());
        System.out.println("Number of edges: " + g.edges.size());

        thread thread1 = new thread();
        thread1.start();
        thread thread2 = new thread();
        thread2.start();


    }


    static void loadGraph(graph g){
        String csvFile = "./graphs/p2p-Gnutella04.csv";
        String pathLabelsFile = "./graphs/PathLabels.csv";// Replace with your CSV file path
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> rows = reader.readAll();
            // Change this to the column index you want to read (0-based index)
            for (String[] row : rows) {
                if (row.length == 2) {
                    if(!g.vertices.containsKey(Integer.parseInt(row[0]))){
                        g.addVertex(Integer.parseInt(row[0]));
                    }
                    if(!g.vertices.containsKey(Integer.parseInt(row[1]))){
                        g.addVertex(Integer.parseInt(row[1]));
                    }
                    g.addEdge(Integer.parseInt(row[0]), Integer.parseInt(row[1]));

                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathLabelsFile))) {
            // Writing headers
            // Writing data rows
            for(vertex v : g.vertices.values()){
                writer.write(v.vertexId + "," + String.join(",", v.pathLabel.toString()));
                writer.newLine();
            }
            System.out.println("Data has been written to " + csvFile);
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

}