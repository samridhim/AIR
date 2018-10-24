
// Java program to demonstrate 
// working of Alpha-Beta Pruning 
import java.io.*; 
  
class pruning { 
  
static int min = +10000;
static int max = -10000;
static int pruning  =0;
public static int minimax(int depth, int nodeIndex, int alpha, int beta, boolean maximiser, int values[]){

if(depth==4)
return values[nodeIndex];

if(maximiser)
{
int best = max;
for(int i =0;i<2;i++){
int val = minimax(depth+1, nodeIndex*2+i, alpha, beta, false, values);
best = Math.max(best, val);
alpha = Math.max(best, alpha);
if(alpha>=beta){
pruning++;
break;
}
}
return best;
}
else
{
int best = min;
for(int i =0;i<2;i++){
int val = minimax(depth+1, nodeIndex*2+i, alpha, beta, true, values);
best = Math.min(best, val);
beta = Math.min(best, alpha);
if(alpha>=beta){
pruning++;
break;
}
}
return best;
}
}

public static void main(String args[]){

int values [] = {8,7,3,9,9,8,2,4,1,8,8,9,9,9,3,4};
System.out.println("Best value for maximiser is " + minimax(0, 0, max, min, true, values));
System.out.println("Pruned " + pruning);
}
}
