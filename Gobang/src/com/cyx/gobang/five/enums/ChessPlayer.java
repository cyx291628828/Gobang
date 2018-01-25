package src.com.cyx.gobang.five.enums;

public enum ChessPlayer {
    /**
     * @expression ��
     */
    BLANK(-1, "����"),
    /**
     * @expression ����
     */
    BLACK(1, "����"),
    /**
     * @expression ����
     */
    WHITE(2, "����"),;

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
