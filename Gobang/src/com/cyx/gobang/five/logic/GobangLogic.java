package com.cyx.gobang.five.logic;

public class GobangLogic {
    private static volatile GobangLogic gobangLogic;
    private static final Object S_OBJECT = new Object();
    
    public static GobangLogic getMe(){
	if(gobangLogic == null){
	    synchronized(S_OBJECT){
		if(gobangLogic == null){
		    gobangLogic = new GobangLogic();
		}
	    }
	}
	return gobangLogic;
    }
    
    private GobangLogic() {
	
    }
    
}
