#define N 4
#include<iostream>
using namespace std;

bool checkisSafe(int board[N][N], int row, int col, int backslashcode[N][N], int slashcode[N][N], bool backslashcodelookup[], bool slashcodelookup[], bool rowlookup[N]){
if(backslashcodelookup[backslashcode[row][col]] || slashcodelookup[slashcode[row][col]] || rowlookup[row])
	return false;
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

bool NQueensUtil(int board[N][N], int col, int backslashcode[N][N], int slashcode[N][N], bool backslashcodelookup[], bool slashcodelookup[], bool rowlookup[N])
{
if(col>=N){
return true;
}
for(int i =0;i<N;i++){
if(checkisSafe(board, i,col, backslashcode, slashcode, backslashcodelookup, slashcodelookup, rowlookup))
{
board[i][col] = 1;
backslashcodelookup[backslashcode[i][col]] = true;
slashcodelookup[slashcode[i][col]] = true;
rowlookup[i] = true; 

if(NQueensUtil(board, col+1, backslashcode, slashcode, backslashcodelookup, slashcodelookup, rowlookup))
return true;


board[i][col] = 0;
backslashcodelookup[backslashcode[i][col]] = false;
slashcodelookup[slashcode[i][col]] = false;
rowlookup[i] = false;
}
}
return false;
}
int main(){
int board[N][N] = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
int slashcode[N][N], backslashcode[N][N];
for(int i =0;i<N;i++){
for(int j =0;j<N;j++){
slashcode[i][j] = i+j;
backslashcode[i][j] = i-j+3;
}
}
bool backslashcodelookup[2*N-1] = {false};
bool slashcodelookup[2*N-1] = {false};
bool rowlookup[N] = {false};
if(NQueensUtil(board, 0, backslashcode, slashcode, backslashcodelookup, slashcodelookup, rowlookup) == false)
cout<<"NO solution";
else
printBoard(board);
}
