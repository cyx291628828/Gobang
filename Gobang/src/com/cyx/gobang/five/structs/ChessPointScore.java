package com.cyx.gobang.five.structs;

/**
 * 
 * @author ������
 * @date 2017��11��14
 * @function ������ÿ�������ӵ�ĸ�������ķ���
 *
 */
public class ChessPointScore {
    /**
     * @expression ���ϵ����µĳɼ�
     */
    private int zuoScore;
    /**
     * @expression ���ϵ����µĳɼ�
     */
    private int youScore;
    /**
     * @expression ��ֱ�����ϵĳɼ�
     */
    private int verticalScore;
    /**
     * @expression ˮƽ�����ϵĳɼ�
     */
    private int horizontalScore;
    /**
     * @expression �ܳɼ�
     */
    private int scoreAll;
    
    public int getZuoScore() {
        return zuoScore;
    }
    public void setZuoScore(int zuoScore) {
        this.zuoScore = zuoScore;
    }
    public int getYouScore() {
        return youScore;
    }
    public void setYouScore(int youScore) {
        this.youScore = youScore;
    }
    public int getVerticalScore() {
        return verticalScore;
    }
    public void setVerticalScore(int verticalScore) {
        this.verticalScore = verticalScore;
    }
    public int getHorizontalScore() {
        return horizontalScore;
    }
    public void setHorizontalScore(int horizontalScore) {
        this.horizontalScore = horizontalScore;
    }
    public int getScoreAll() {
        return scoreAll;
    }
    public void setScoreAll(int scoreAll) {
        this.scoreAll = scoreAll;
    }
    
}
