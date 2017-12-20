package com.cyx.gobang.five.structs;

import com.cyx.gobang.five.enums.ChessPlayer;

/**
 * 
 * @author ������
 * @date 2017��11��14
 * @function ������ÿ�������ӵ����Ϣ
 *
 */
public class ChessPointMsg {
    /**
     * @expression �õ����ڵ�X��Y���λ��
     */
    private ChessPoint point;
    /**
     * @expression �õ����ڵ�BLACK����
     */
    private ChessPointScore blackScore = new ChessPointScore();
    /**
     * @expression �õ����ڵ�WHITE����
     */
    private ChessPointScore whiteScore = new ChessPointScore();
    /**
     * @expression ���ӵĺ��� ʱ��
     */
    private long timeMsec;
    /**
     * @expression ���ӷ�
     */
    private ChessPlayer player;
    /**
     * @expression ���Ӳ���
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

}
