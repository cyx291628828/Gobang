package com.cyx.gobang.five.structs;

import java.util.ArrayList;
import java.util.List;

import com.cyx.gobang.five.constant.GobangConstant;

public class ForecastPointMsg {
    /**
     * Ŀǰ�������̵����е�״̬
     */
    private ChessPointMsg[][] chessPointMsgs = new ChessPointMsg[GobangConstant.CHESS_SIZE][GobangConstant.CHESS_SIZE];
    /**
     * Ŀǰ����״̬����õ�12����
     */
    private List<BestPoint> betterPoints = new ArrayList<BestPoint>();
    public ChessPointMsg[][] getChessPointMsgs() {
        return chessPointMsgs;
    }
    public void setChessPointMsgs(ChessPointMsg[][] chessPointMsgs) {
        this.chessPointMsgs = chessPointMsgs;
    }
    public List<BestPoint> getBetterPoints() {
        return betterPoints;
    }
    public void setBetterPoints(List<BestPoint> betterPoints) {
        this.betterPoints = betterPoints;
    }
    
    
}
