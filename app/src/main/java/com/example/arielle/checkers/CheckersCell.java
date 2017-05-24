package com.example.arielle.checkers;

/**
 * Created by arielle on 13/04/2017.
 */

public class CheckersCell {
    private int id, player, type;
    private int[] moves;

    CheckersCell(int id, int player, int type, int[] moves) {
        this.id = id;
        this.player = player;
        this.type = type;
        moves = new int[4];
        System.arraycopy(moves, 0, this.moves, 0, 4);
    }

    CheckersCell(CheckersCell a) {
        moves = new int[4];
        id = a.getId();
        player = a.getPlayer();
        type = a.getType();
        for (int i = 0; i < 4; i++) {
            moves[i] = a.getMove(i);
        }
    }

    CheckersCell(int ind) {
        id = ind;
        moves = new int[4];
        if ((ind / 4) % 2 == 0) {
            moves[0] = ind - 5;
            moves[1] = ind - 4;
            moves[2] = ind + 3;
            moves[3] = ind + 4;
            if (ind % 4 == 0) {
                moves[0] = -1;
                moves[2] = -1;
            }
        } else {
            moves[0] = ind - 4;
            moves[1] = ind - 3;
            moves[2] = ind + 4;
            moves[3] = ind + 5;
            if (ind % 4 == 3) {
                moves[1] = -1;
                moves[3] = -1;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (moves[i] < 0 || moves[i] > 31) {
                moves[i] = -1;
            }
        }
        if (ind / 4 < 3) {
            player = 1;
            type = 1;
        } else if (ind / 4 > 4) {
            player = 2;
            type = 1;
        } else {
            player = 0;
            type = 0;
        }
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCell(CheckersCell a) {
        player = a.getPlayer();
        type = a.getType();
    }

    public void clear() {
        player = 0;
        type = 0;
    }

    public int getPlayer() {
        return player;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public boolean isNeighbor(int vl) {
        for (int i : moves) {
            if (i == vl)
                return true;
        }
        return false;
    }

    public int getMove(int ind) {
        return moves[ind];
    }
}

