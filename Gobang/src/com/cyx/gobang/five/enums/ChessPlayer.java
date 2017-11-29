package com.cyx.gobang.five.enums;

public enum ChessPlayer {
    /**
     * @expression ¿Õ
     */
    BLANK(-1,"¿Õ×Ó"),
    /**
     * @expression ºÚÆå
     */
    BLACK(1,"ºÚÆå"),
    /**
     * @expression °×Æå
     */
    WHITE(2,"°×Æå"),
    ;
    
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
    
}
