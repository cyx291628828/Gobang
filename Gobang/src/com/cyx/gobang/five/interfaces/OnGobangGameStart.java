package com.cyx.gobang.five.interfaces;

import com.cyx.gobang.five.chessboard.ChessBoard;

public interface OnGobangGameStart {

    /**
     * 在游戏开始时调用
     * @param cBoard
     */
    public void onGameStart(ChessBoard cBoard);
}
