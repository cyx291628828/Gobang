package src.com.cyx.gobang.five.interfaces;

import src.com.cyx.gobang.five.chessboard.ChessBoard;

public interface OnGobangGameBefore {

    /**
     * ��Ϸ��ʼǰ����
     * @param cBoard
     */
    public void onGameBefore(ChessBoard cBoard);
    
}
