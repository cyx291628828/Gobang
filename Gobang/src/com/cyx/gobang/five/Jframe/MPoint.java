package com.cyx.gobang.five.Jframe;

import com.cyx.gobang.five.enums.ChessPlayer;

public class MPoint {
    public int X = 0;
    
    public int Y = 0;
    
    public ChessPlayer type = ChessPlayer.BLACK;

    public MPoint(int a, int b, ChessPlayer c){
	this.X = a;
	this.Y = b;
	this.type = c;
    }
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
    
    public ChessPlayer getType() {
        return type;
    }
    
    public void setType(ChessPlayer type) {
        this.type = type;
    }

    
}
