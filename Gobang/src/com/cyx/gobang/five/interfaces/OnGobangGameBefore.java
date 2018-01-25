package com.cyx.gobang.five.interfaces;

import com.cyx.gobang.five.chessboard.ChessBoard;

public interface OnGobangGameBefore {

    /**
     * 游戏开始前调用
     * @param cBoard
     */
    public void onGameBefore(ChessBoard cBoard);
    
}
