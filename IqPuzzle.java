import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class IqPuzzle {
    public static void moveBlock(int[][][] visited, ArrayList<Integer> piece, int x, int y, int piece_length, int isLineVert, int isMirror, int isReverse, int blockNumber, int isPut){
        int num = blockNumber;
        if(isPut == 0){
            num = 0;
        }
        int offsetX = 0, offsetY = 0, lay;
        for(int i = 0; i < piece_length; i++){
            if(isReverse == 1){
                lay = piece.get(piece_length-i-1);
            }else{
                lay = piece.get(i);
            }

            //condition is valid
            while(lay > 0){
                visited[0][x+offsetX][y+offsetY] = num;
                if(isLineVert == 1){
                    // offsetX++;
                    if(isMirror == 1){
                        offsetY--;
                    }else{
                        offsetY++;
                    }
                }else{
                    // offsetY++;
                    if(isMirror == 1){
                        offsetX--;
                    }else{
                        offsetX++;
                    }
                }
                lay--;
            }

            if(isLineVert == 1){
                offsetX++;
                offsetY = 0;
            }else{
                offsetY++;
                offsetX = 0;
            }

        }
        
    }
    public static boolean isValid(int[][][] board, int[][][] visited, ArrayList<Integer> piece, int N, int M, int x, int y, int piece_length, int isLineVert, int isMirror, int isReverse){    //default is not mirror, line vert (ex: L, E) | line horz (ex: M)
        int lay;
        int offsetX = 0, offsetY = 0;
        for(int i = 0; i < piece_length; i++){
            if(isReverse == 1){
                lay = piece.get(piece_length-i-1);
            }else{
                lay = piece.get(i);
            }

            while(lay > 0){
                if((x+offsetX >= N || x+offsetX < 0) || (y+offsetY >= M || y+offsetY < 0)){
                    return false;
                }else if(visited[0][x+offsetX][y+offsetY] > 0 || board[0][x+offsetX][y+offsetY] == 0){
                    return false;
                }else{
                    if(isLineVert == 1){
                        // offsetX++;
                        if(isMirror == 1){
                            offsetY--;
                        }else{
                            offsetY++;
                        }
                    }else{
                        // offsetY++;
                        if(isMirror == 1){
                            offsetX--;
                        }else{
                            offsetX++;
                        }
                    }
                }
                lay--;
            }

            if(isLineVert == 1){
                offsetX++;
                offsetY = 0;
            }else{
                offsetY++;
                offsetX = 0;
            }

        }


        
        return true;
    }
    public static int recSolver2D(int[][][] board, int[][][] visited, ArrayList<Integer>[] pieces, int[] used_piece, int N, int M, int B, int idx, int x, int y, int tries){
        int this_run_try = 0;
        if(idx <= B){
            System.out.println("Block placed: " + idx);
            for (int i = 0; i < B; i++){
                if(used_piece[i] == 1){
                    continue;
                }
                for (int j = 0; j < 2 ; j++){ //mirror
                    for (int k = 0; k < 2; k++){ //line Vert
                        for(int l = 0; l < 2; l++){ //reverse

                            if(isValid(board, visited, pieces[i], N, M, x, y, pieces[i].size(), j, k, l)){

                                //put block
                                used_piece[i] = 1;
                                moveBlock(visited, pieces[i], x, y, pieces[i].size(), j, k, l, i + 1, 1);
                                
                                //next empty pos
                                int nextX = x, nextY = y;
                                while(visited[0][nextX][nextY] > 0|| board[0][nextX][nextY] == 0){
                                    if(nextY == M-1){
                                        nextX++;
                                        nextY = 0;
                                    }else{
                                        nextY++;
                                    }
                                    if(nextX == N){
                                        break;
                                    }
                                }

                                //solution found
                                if(nextX == N){
                                    if(idx == B-1){
                                        System.out.println("Solution found.\nTries: " + tries + "\nSolution:\n");
                                        //print board
                                        for (int a = 0; a < N; a++){
                                            for (int b = 0; b < M; b++){
                                                if(visited[0][a][b] == 0){
                                                    System.out.print(" ");
                                                }else{
                                                    System.out.print(visited[0][a][b] + " ");
                                                }
                                            }
                                            System.out.println();
                                        }
                                        System.exit(0);
                                    }else{ //kalo kelebihan piece(s)
                                        System.out.println("\nTest Case invalid.\nTries: " + tries);
                                        System.out.println("Reason: board full with spare piece(s).");
                                        System.exit(0);
                                    }
                                }

                                //recursive
                                this_run_try += recSolver2D(board, visited, pieces, used_piece, N, M, B, idx+1, nextX, nextY, tries + this_run_try);
                                //take block
                                used_piece[i] = 0;
                                moveBlock(visited, pieces[i], x, y, pieces[i].size(), j, k, l, i + 1, 0);
                            }else{
                                this_run_try++;
                            }
                            
                        }

                    }

                }
            }
        }
        return this_run_try;

    }

    public static void main(String[] args){
        Scanner input = null;
        try {
            input = new Scanner(new File("input668.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(1);
        }
        int N = input.nextInt();
        int M = input.nextInt();
        int B = input.nextInt();
        input.nextLine(); // consume the newline character
        String board_type = input.nextLine();
        int[][][] board;
        //board set-up
        if(board_type.equals("DEFAULT")){
            board = new int[1][N][M];
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    board[0][i][j] = 1;
                }
            }
        }else if(board_type.equals("CUSTOM")){
            board = new int[1][N][M];
            String c;
            for (int i = 0; i < N; i++){
                c = input.nextLine();
                for (int j = 0; j < M; j++){
                    if(c.charAt(j) == 'X'){
                        board[0][i][j] = 1;
                    }else{
                        board[0][i][j] = 0;
                    }
                }
            }
        }else if(board_type.equals("PYRAMID")){
            board = new int[N][N][M]; //top left always valid
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    for (int k = 0; k < M; k++){
                        if(j<=i && k<=i){
                            board[i][j][k] = 1;
                        }else{
                            board[i][j][k] = 0;
                        }
                    }
                }
            }
        }else{//error, invalid\
            board = new int[0][0][0];
            System.out.println("Invalid board type");
            System.exit(0);
        }
        
        //pieces set-up
        int[] used_piece = new int[B];
        for (int i = 0; i < B; i++){
            used_piece[i] = 0;
        }

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] piece = new ArrayList[B];
        for (int i = 0; i < B; i++) {
            piece[i] = new ArrayList<>();
        }
        String piece_layer;
        char pos = 'A';
        int pos_idx = 0;
        while(input.hasNextLine() && pos_idx < B-1){  //read pieces
            piece_layer = input.nextLine();
            if(piece_layer.charAt(0) != pos){
                pos = piece_layer.charAt(0);
                pos_idx++;
            }
            System.out.println("OK"+pos_idx);
            piece[pos_idx].add(piece_layer.length());
        }

        System.out.println("???");
        input.close();
        
        //visited set-up
        int[][][] visited = new int[N][N][M];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                for (int k = 0; k < M; k++){
                    visited[i][j][k] = 0;
                }
            }
        }

        //solve
        if(board_type.equals("DEFAULT") || board_type.equals("CUSTOM")){
            int tries = recSolver2D(board, visited, piece, used_piece, N, M, B, 0, 0, 0, 0);
            System.out.println("No solution found.\nTries: " + tries);
            System.exit(0);
        }else{  //PYRAMID

        }
    
//----------------------
    }
}