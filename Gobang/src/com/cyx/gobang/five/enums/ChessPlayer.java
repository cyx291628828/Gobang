package com.cyx.gobang.five.enums;

public enum ChessPlayer {
    /**
     * @expression 空白
     */
    BLANK(-1, "空白"),
    /**
     * @expression 黑棋
     */
    BLACK(1, "黑棋"),
    /**
     * @expression 白棋
     */
    WHITE(2, "白棋"),;

    private int mark;
    
    private String des;

    private ChessPlayer(int mark, String des) {
	this.mark = mark;
	this.des = des;
    }

    public int getMark() {
	return mark;
    }

    public void setMark(int mark) {
	this.mark = mark;
    }

    public String getDes() {
	return des;
    }

    public void setDes(String des) {
	this.des = des;
    }

    public boolean compare(ChessPlayer player) {
	return player.getMark() == this.mark;
    }

    public static ChessPlayer formMark(int mark){
	for (ChessPlayer cPlayer : values()) {
	    if(cPlayer.getMark() == mark){
		return cPlayer;
	    }
        }
	return BLANK;
    }
}
