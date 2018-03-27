package com.cyx.gobang.five.structs;

import com.cyx.gobang.five.enums.ChessPlayer;

/**
 * 
 * @author 陈宇轩
 * @date 2017年11月14
 * @function 棋盘上每个已落子点的信息
 *
 */
public class ChessPointMsg implements Cloneable{
    /**
     * @expression 该点所在的X轴Y轴的位置
     */
    private ChessPoint point;
    /**
     * @expression 该点所在的BLACK分数
     */
    private ChessPointScore blackScore = new ChessPointScore();
    /**
     * @expression 该点所在的WHITE分数
     */
    private ChessPointScore whiteScore = new ChessPointScore();
    /**
     * @expression 落子的毫秒 时间
     */
    private long timeMsec;
    /**
     * @expression 落子方
     */
    private ChessPlayer player;
    /**
     * @expression 落子步数
     */
    private int count;

    public ChessPointMsg(ChessPoint point, ChessPlayer player) {
	this.point = point;
	this.player = player;
    }

    public ChessPoint getPoint() {
	return point;
    }

    public void setPoint(ChessPoint point) {
	this.point = point;
    }

    public ChessPointScore getBlackScore() {
	return blackScore;
    }

    public void setBlackScore(ChessPointScore blackScore) {
	this.blackScore = blackScore;
    }

    public ChessPointScore getWhiteScore() {
	return whiteScore;
    }

    public void setWhiteScore(ChessPointScore whiteScore) {
	this.whiteScore = whiteScore;
    }

    public long getTimeMsec() {
	return timeMsec;
    }

    public void setTimeMsec(long timeMsec) {
	this.timeMsec = timeMsec;
    }

    public ChessPlayer getPlayer() {
	return player;
    }

    public void setPlayer(ChessPlayer player) {
	this.player = player;
    }

    public int getCount() {
	return count;
    }

    public void setCount(int count) {
	this.count = count;
    }

    public void clearAllScore(){
	blackScore.clear();
	whiteScore.clear();
    }
    
    @Override
    protected ChessPointMsg clone() throws CloneNotSupportedException {
        return (ChessPointMsg) super.clone();
    }
}
