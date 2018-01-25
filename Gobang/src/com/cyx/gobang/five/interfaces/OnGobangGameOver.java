package com.cyx.gobang.five.interfaces;

import com.cyx.gobang.five.chessboard.ChessBoard;

public interface OnGobangGameOver {
    
    /**
     * ����Ϸ������ʱ����
     * ��Ϸ����ʤ��֮�����
     * ���ʤ��
     * @param cBoard
     */
    public void onGameWin(ChessBoard cBoard);
    
    /**
     * ����Ϸ������ʱ����
     * ��Ϸ����ʤ��֮�����
     * ���ʧ��
     * @param cBoard
     */
    public void onGameLose(ChessBoard cBoard);
}
