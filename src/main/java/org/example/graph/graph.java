package org.example.graph;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class graph {
    public Map<Integer, vertex> vertices = new HashMap<>();
    public Set<Pair<Integer, Integer>> edges = new HashSet<>();

    public vertex addVertex(int vertexId) {
        vertex v = new vertex(vertexId);
        vertices.put(vertexId, v);
        return v;
    }

    public boolean addEdge(int from, int to) {
        if (vertices.containsKey(from) && vertices.containsKey(to)) {
            edges.add(Pair.of(from, to));
            vertices.get(from).children.add(vertices.get(to));
            vertices.get(to).parents.add(vertices.get(from));
            vertices.get(to).recomputeLabel(new HashSet<>());
            return true;
        }
        return false;
    }
}
