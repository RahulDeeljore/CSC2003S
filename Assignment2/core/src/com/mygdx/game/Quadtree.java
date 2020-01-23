package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Quadtree {

    private int x;
    private int y;
    private int width;
    private int height;
    private int level;
    public static final int  maxlevels = 5;
    private List<Entity> entities;
    private Quadtree[] nodes;


    // constructor
    public Quadtree(int pLevel, int x, int y, int width, int height) {
        level = pLevel;
        entities = new ArrayList<Entity>();
        nodes = new Quadtree[4];
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private int getIndex(Entity entity) {
        int index = -1; // initialize index of quadrants, explained below

        // Get midpoints of this node of the Quadtree
        int verticalMidpoint = this.y + (this.height / 2);
        int horizontalMidpoint = this.x + (this.width / 2);

        // Check where entity fits in the 4 quadrands.
        boolean top = (entity.y > verticalMidpoint);
        boolean bottom = ((entity.y + entity.height) < verticalMidpoint);
        boolean right = (entity.x > horizontalMidpoint);
        boolean left = ((entity.x + entity.width) < horizontalMidpoint);

       // index = index of quadrant
        if(left && bottom)
            index = 0;
        if(right && bottom)
            index = 1;
        if(left && top)
            index = 2;
        if(right && top)
            index = 3;

        return index;// returns index
    }

    private void split() {
       // calculating midpoint to create subnodes
        int subWidth = this.width / 2;
        int subHeight = this.height / 2;

        // create quadtree in subnodes
        nodes[0] = new Quadtree(level+1, x, y, subWidth, subHeight);
        nodes[1] = new Quadtree(level+1, x + subWidth, y, subWidth, subHeight);
        nodes[2] = new Quadtree(level+1, x, y + subHeight, subWidth, subHeight);
        nodes[3] = new Quadtree(level+1, x + subWidth, y + subHeight, subWidth, subHeight);
    }

    private void insert(Entity entity) {

        int index = getIndex(entity);

        // can hold at most 4 levels
        if (index != -1 && level < maxlevels) {

            if (nodes[index] == null) split();

            nodes[index].insert(entity);

            return;
        }
        System.out.println("Entity added at level"+this.level);
        entities.add(entity);
    }

    // inserting entities in quandtree
    public void insert(List<Entity> entities) {
        for(Entity e: entities){
            insert(e);
        }
    }

    public List<Entity> retrieve(List<Entity> returnEntities, Entity entity) {
        int index = getIndex(entity);

        if(nodes[0] != null) {

            if (index != -1) {
                nodes[index].retrieve(returnEntities, entity);
            } else {
                retrieveAll(returnEntities);
            }

        }

        returnEntities.addAll(entities);

        return returnEntities;
    }

    private void retrieveAll(List<Entity> returnEntities) {
        if(nodes[0] != null) {

            for(Quadtree node: nodes){
                returnEntities.addAll(node.entities);
                node.retrieveAll(returnEntities);
            }

        }
    }

    public void clear(){
        entities.clear();

        for (int i = 0; i < nodes.length; i++) {
            if(nodes[i] != null){
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

}

