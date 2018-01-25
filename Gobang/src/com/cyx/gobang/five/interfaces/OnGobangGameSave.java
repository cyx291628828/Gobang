package com.cyx.gobang.five.interfaces;

import com.cyx.gobang.five.chessboard.ChessBoard;

public interface OnGobangGameSave {
    
    /**
     * 在游戏存盘的时候调用
     * @param cBoard
     */
    public void onGameSave(ChessBoard cBoard);
}
