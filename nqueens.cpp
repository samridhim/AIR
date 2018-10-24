#define N 4
#include<iostream>

using namespace std;
bool checkisSafe(int board[N][N], int row, int col){

int i,j;
for(i =0;i<col;i++){
if(board[row][i]) 
return false;
}
for(i =row,j = col;i>=0 && j>=0; i--, j--){
if(board[i][j])
return false;
}
for(i = row,j = col; i<N && j>=0; i++,j--){
if(board[i][j])
return false;
}
return true;
}
void printBoard(int board[N][N]){
for(int i =0;i<N;i++){
 for(int j =0;j<N;j++){
   cout<<board[i][j]<< " ";
}
cout<<endl;
}
}

bool solveNQUtil(int board[N][N], int col)
{

if (col>=N)
{
return true;
}
for(int i =0;i<N;i++){
if(checkisSafe(board, i ,col)){
board[i][col]  = 1;
if(solveNQUtil(board, col+1))
return true;

board[i][col]=0;
}
}
return false;
}
int main(){
int board[N][N] = {{ 0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
if(solveNQUtil(board, 0) == false)
{
cout<<"No solution";
}
else
printBoard(board);
}
