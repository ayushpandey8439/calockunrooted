package org.example.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class vertex {
    public int vertexId;
    public List<Integer> pathLabel = new ArrayList<>();
    Set<vertex> children = new HashSet<>();
    Set<vertex> parents = new HashSet<>();
    Set<vertex> criticalAncestors = new HashSet<>();

    public vertex(int vertexId) {
        this.vertexId = vertexId;
        this.pathLabel.add(vertexId);
    }

    public void recomputeLabel(Set<Integer> visited){
        if(visited.contains(this.vertexId)){
            return;
        }
        visited.add(this.vertexId);
        // calculate the set intersection of parents labels;
        List<Integer> oldPathLabel = new ArrayList<>(this.pathLabel);
        this.pathLabel.clear();
        for(vertex parent : parents){
            if(this.pathLabel.isEmpty()){
                this.pathLabel.addAll(parent.pathLabel);
            } else {
                this.pathLabel.retainAll(parent.pathLabel);
            }
        }
        if(!this.pathLabel.contains(this.vertexId)){
            this.pathLabel.add(this.vertexId);
        }
        if(!this.pathLabel.equals(oldPathLabel)){
            for(vertex child : children){
                child.recomputeLabel(visited);
            }
        }
        visited.remove(this.vertexId);
    }

}
