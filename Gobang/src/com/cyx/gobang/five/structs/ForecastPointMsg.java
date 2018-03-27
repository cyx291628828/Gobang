package com.cyx.gobang.five.structs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cyx.gobang.five.constant.GobangConstant;
import com.cyx.gobang.five.enums.ChessPlayer;

public class ForecastPointMsg implements Cloneable{
    /**
     * 目前整个棋盘的所有点状态
     */
    private ChessPointMsg[][] chessPointMsgs = new ChessPointMsg[GobangConstant.CHESS_SIZE][GobangConstant.CHESS_SIZE];
    /**
     * 目前棋盘状态的最好的12个点
     */
    private List<BestPoint> betterPoints = new ArrayList<BestPoint>();
    /**
     * 最好点的个数
     */
    private int pointSize = 16;
    /**
     * 下子的步数
     */
    private int chessCount = 0;

    public int getChessCount() {
	return chessCount;
    }

    public void setChessCount(int chessCount) { 
	this.chessCount = chessCount;
    }

    public int getPointSize() {
	return pointSize;
    }

    public void setPointSize(int pointSize) {
	this.pointSize = pointSize;
    }

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

    public void init(int cHESS_SIZE, int pointSize) {
	chessPointMsgs = new ChessPointMsg[cHESS_SIZE][cHESS_SIZE];
	for (int i = 0; i < cHESS_SIZE; i++) {
	    for (int j = 0; j < cHESS_SIZE; j++) {
		chessPointMsgs[i][j] = new ChessPointMsg(new ChessPoint(i, j), ChessPlayer.BLANK);
		chessPointMsgs[i][j].getBlackScore().setFourScore(30);
		chessPointMsgs[i][j].getWhiteScore().setFourScore(30);
	    }
	}
	setPointSize(pointSize);
    }

    public void addDropChess(ChessPoint cPoint, ChessPlayer cPlayer) {
	ChessPointMsg chessPointMsg = chessPointMsgs[cPoint.getPoint_x()][cPoint.getPoint_y()];
	chessPointMsg.setPoint(cPoint);
	chessPointMsg.setPlayer(cPlayer);
	chessPointMsg.setTimeMsec(new Date().getTime());
	chessPointMsg.setCount(++chessCount);
//	chessPointMsg.clearAllScore();
    }
    
    public ChessPointMsg getDropChessMsg(ChessPoint cPoint){
	ChessPointMsg chessPointMsg = chessPointMsgs[cPoint.getPoint_x()][cPoint.getPoint_y()];
	return chessPointMsg;
    }
    
    @Override
    protected ForecastPointMsg clone() throws CloneNotSupportedException {
        return (ForecastPointMsg) super.clone();
    }
}
