package chess;

import chess.Player.Color;
import chess.piece.*;

import java.util.Scanner;

public class Chess {
    private Player[] players;
    private Cell[][] board;
    private Player currentPlayer;

    public void play() {
        // Game loop
        while (true) {
            createPlayers();
            initializeBoard();
            currentPlayer = players[0];
            // print the board
            while (!checkmate()) {
                printBoard();
                System.out.println("Player " + currentPlayer.toSimpleString() + "'s turn");
                // get input
                String move;
                do {
                    move = askMove();
                }
                while (!isValidMove(move));
                // check if move is valid
                // move piece
                switchPlayer();

            }
        }

    }

    private String askMove() {
        // Ask the user for a move and return it
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your move (Exemple : A2 A4): ");
        return scanner.nextLine();
    }

    private boolean isValidMove(String move) {
        // Check if the move is valid
        Cell[] cells = parseMove(move);
        if(cells == null) {
            System.out.println("Invalid move, please enter a valid move");
            return false;
        }

        // Start cell must container player's piece and end cell must not contain player's piece
        if(!cells[0].getPiece().getOwner().equals(currentPlayer) || cells[1].getPiece().getOwner().equals(currentPlayer)) {
            System.out.println("Invalid move, you cannot move this piece to this cell");
            return false;
        }

        // Piece in start cell must be able to move to end cell
        if(!cells[0].getPiece().canMove(board, cells[0], cells[1])) {
            System.out.println("Invalid move, piece cannot move to this cell");
            return false;
        }


        return true;
    }
    private Cell[] parseMove(String move) {
        // Parse the move and return the two cells
        String[] parts = move.split(" ");
        if (parts.length != 2 || parts[0].length() != 2 || parts[1].length() != 2) {
            return null;
        }
        int x1 = parts[0].charAt(0) - 'A';
        int y1 = parts[0].charAt(1) - '1';
        int x2 = parts[1].charAt(0) - 'A';
        int y2 = parts[1].charAt(1) - '1';
        if (x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7 || x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) {
            return null;
        }
        System.out.println("x1 = " + x1 + " y1 = " + y1 + " x2 = " + x2 + " y2 = " + y2);
        board[x1][y1].print();
        System.out.print("-->");
        board[x2][y2].print();
        System.out.println();

        return new Cell[] {board[x1][y1], board[x2][y2]};
    }
    private void switchPlayer() {
        currentPlayer = currentPlayer == players[0] ? players[1] : players[0];
    }
    private boolean checkmate() {
        // Analyse the board to see if the current player is in checkmate

        return false;
    }

    private void printBoard() {
        System.out.println("  A B C D E F G H");
        for (int y = 0; y < 8; y++) {
            System.out.print(y + 1);
            for (int x = 0; x < 8; x++) {
                board[x][y].print();
            }
            System.out.println();
        }
    }
    private void createPlayers() {
        players = new Player[2];
        players[0] = new Player(Color.WHITE);
        players[1] = new Player(Color.BLACK);
    }

    private void initializeBoard() {
        board = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
        // Initialize player 0's pieces
        for (int i = 0; i < 8; i++) {
            board[i][1].setPiece(new Pawn(players[0]));
        }
        board[0][0].setPiece(new Rook(players[0]));
        board[1][0].setPiece(new Knight(players[0]));
        board[2][0].setPiece(new Bishop(players[0]));
        board[3][0].setPiece(new Queen(players[0]));
        board[4][0].setPiece(new King(players[0]));
        board[5][0].setPiece(new Bishop(players[0]));
        board[6][0].setPiece(new Knight(players[0]));
        board[7][0].setPiece(new Rook(players[0]));
        // Initialize player 1's pieces
        for (int i = 0; i < 8; i++) {
            board[i][6].setPiece(new Pawn(players[1]));
        }
        board[0][7].setPiece(new Rook(players[1]));
        board[1][7].setPiece(new Knight(players[1]));
        board[2][7].setPiece(new Bishop(players[1]));
        board[3][7].setPiece(new Queen(players[1]));
        board[4][7].setPiece(new King(players[1]));
        board[5][7].setPiece(new Bishop(players[1]));
        board[6][7].setPiece(new Knight(players[1]));
        board[7][7].setPiece(new Rook(players[1]));



    }


}
