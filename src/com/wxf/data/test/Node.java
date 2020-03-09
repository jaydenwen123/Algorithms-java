package com.wxf.data.test;

import java.util.ArrayList;

/* 表示一个节点以及和这个节点相连的所有节点 */
public class Node {
	public String name = null;
	public ArrayList<Node> relationNodes = new ArrayList<Node>();
	public int weight;
	public int cost;
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Node(String name, int weight, int cost) {
		super();
		this.name = name;
		this.weight = weight;
		this.cost = cost;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Node> getRelationNodes() {
		return relationNodes;
	}

	public void setRelationNodes(ArrayList<Node> relationNodes) {
		this.relationNodes = relationNodes;
	}

	class Edge {
		public String start;
		public String end;
		public int weight;
		public int cost;

		public Edge() {
			// TODO Auto-generated constructor stub
		}

		public Edge(String start, String end, int weight, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
			this.cost = cost;
		}

	}
}