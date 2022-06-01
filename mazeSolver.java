package Assign4;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;

public class mazeSolver {

    private ASCIIDataFile input;
    private ASCIIOutputFile output;
    private char [][] maze;
    private int [] nums = new int[] { -1,0,3,5,9,12 };
    private int i, j, f, s;

    public mazeSolver(){
        input = new ASCIIDataFile();
        output = new ASCIIOutputFile();
        i = input.readInt();
        j = input.readInt();
        f = i; //i will change, therefore using f as f will not be altered
        s = j; //j will also be changed in the program, using s to be the original value of j
        maze = new char[f][s];
        readFile();
        solveMaze();
        outputMaze();
    }

    public int search(int[] se, int target) {
        int i = 0;
        if (se[i] == target){
            return i;
        }
        else {
            i += 1;
            search(se, target);
        }
        return 0;
    }


    /**Output Maze:
     * Writes each line of the solved maze to the output file which will be generated.
     *
     */

    private void outputMaze() {
        for (int x = 0; x<f; x++) {
            output.writeLine(String.valueOf(maze[x]));
        }
    }

    /**Solve Maze:
     *Recursively generates a random int that is no longer than the array length of the columns or rows.
     * It will continue to return false, and therefore generate a new random int, aka starting position
     * until it finds an empty space. Once an empty cell is found, this spot will be marked by 'S' to
     * mean Start, and then it will run the findPath method from the starting position.
     *
     * @return true when maze[i][j] is a empty cell; ' '.
     */

    private boolean solveMaze() {
        i = (int) (f*Math.random());
        j = (int) (s*Math.random());
        if(maze[i][j] == ' '){
            findPath(i,j);
            maze[i][j] = 'S';
            return true;
        }
        if(maze[i][j] != ' '){
            solveMaze();
            return false;
        }
        return false;
    }

    /**Reads file:
     * Imports each line from a user selected file and converts it to a character array to put into our maze array.
     *
     */

    private void readFile() {
        for (int x = 0; x<f; x++) {
            maze[x] = input.readLine().toCharArray();
        }
    }

    /**Path Finding Algorithm:
     * Takes in int x, y and uses these as a starting point for the path finding algorithm.
     * Recursively goes through each direction in the maze and replaces this point with a '.' if it is a deadend.
     * Once it reaches the end of the maze, marked by 'E' or if it is in a deadend where the end is not reachable,
     * it will return true and break the recursive loop.
     *
     * @param x Starting value: row
     * @param y Starting value: for col
     * @return returns true when maze[x][y] is 'E'
     */

    private boolean findPath(int x, int y) {
        if(maze[x][y] == 'E') { //base case
            return true;
        }
        if((maze[x][y] != ' ') && maze[x][y] != 'S' || maze[x][y] == '.'){
            return false;
        }
        maze[x][y] = '.';
        if(findPath(x,y+1)){ //Right
            maze[x][y] = '>';
            return true;
        }
        if(findPath(x,y-1)){ //Left
            maze[x][y] = '<';
            return true;
        }
        if(findPath(x+1,y)){ //Downward
            maze[x][y] = 'v';
            return true;
        }
        if(findPath(x-1,y)){ //Upward
            maze[x][y] = '^';
            return true;
        }
        return false;
    }

    public static void main ( String[] args ) { mazeSolver s = new mazeSolver(); };
}
