import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public class Puzzle {
	static int temp;
	static ArrayList<Integer> a = new ArrayList<Integer>();
	public static void print(int initial[][]){
		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				System.out.print(initial[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	public static boolean legal(int x, int y){
		return (x>=0 && x<3) && (y>=0 && y<3);
	}
	public static boolean duplicate(int man, int mis, int initial[][]){
		temp = computeunique(man, mis, initial);
		if(a.contains(temp))
			return false;
		else
			a.add(temp);
		return true;
	}
	public static int computeunique(int man, int mis, int initial[][]){
		int sum =0;
		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				sum += 2*java.lang.Math.pow(i+1,  3) * 2*java.lang.Math.pow(j+1,  2) * initial[i][j];
			}
		}
		return sum * man * mis;
	}
	public static int computeMisplaced(int initial[][], int fin[][]){
		int count =0;
		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				if(initial[i][j]!=0){
					if(initial[i][j]!=fin[i][j])
						count++;
				}
			}
		}
		return count;
	}
	public static int computeManhattan(int initial[][], int fin[][]){
		int count = 0;
		int tileVal  = 0;
		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				tileVal = initial[i][j];
				count+= java.lang.Math.abs(tileVal/3 - i) + java.lang.Math.abs(tileVal%3 -j);
			}
		}
		return count;
	}

	
	public static Node expand(int state[][], int x, int y, int newX, int newY, int cost){
		Node node = new Node();

		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				node.state[i][j] = state[i][j];
			}
		}
		int temp = node.state[newX][newY];
		node.state[newX][newY] = node.state[x][y];
		node.state[x][y] = temp;
		node.x = newX;
		node.y = newY;
		node.cost = cost;
		node.Astar = 10000;
		node.misplaced = 10000;
		node.manhattan=10000;
		return node;
	}
	public static void solve(int initial[][], int fin[][], int x, int y){
		int noofnodesExpanded =0;
		PriorityQueue<Node> queue=new PriorityQueue<Node>(11, new Comparator<Node>() {
		    public int compare(Node node1, Node node2) {
		        Integer Astar1 = node1.Astar;
		        Integer Astar2 = node2.Astar;
		        return Astar1.compareTo(Astar2);
		    }
		});
		Node initnode = expand(initial, x, y, x, y, 0);
		initnode.cost = 0;
		initnode.misplaced = computeMisplaced(initial, fin);
		initnode.Astar = initnode.cost + initnode.misplaced;
		queue.add(initnode);
		int rowop[] = {1, 0, -1, 0 };
		int colop[] = { 0, 1, 0, -1 };
		while(!queue.isEmpty()){
			Node min = queue.poll();
			System.out.println("About to expand" + min.cost);
			print(min.state);
			if(min.misplaced ==0){
				System.out.println("goal state reached");
				System.out.println("Found at level : " + min.cost);
				print(min.state);
				return;
			}
			for(int i =0;i<4;i++){
				if(legal(min.x + rowop[i], min.y + colop[i])){
					//System.out.println("Child  : " + i);
					Node child = expand(min.state, min.x, min.y, min.x+rowop[i], min.y + colop[i], min.cost +1 );
					noofnodesExpanded++;
					child.manhattan = computeManhattan(child.state, fin);
					child.misplaced = computeMisplaced(child.state, fin);
					child.Astar = child.misplaced + child.cost;
					//print(child.state);
					if(duplicate(child.manhattan, child.misplaced,child.state)){
						queue.add(child);
					}
					
				}
			}
		}
		}
	
	public static void main(String args[]){
		int initial[][] =   {{1,2,0}, {3,4,5}, {7,8,6}};
		int fin[][] = {{1,2,3}, {4,5,6}, {7,8,0}};
		int x=0, y=0;
		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				if(initial[i][j]==0)
				{
					x = i;
					y = j;
				}
			}
		}
		solve(initial, fin, x, y);
		
	}
}
