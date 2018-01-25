package com.cyx.gobang.five.constant;

import java.util.HashMap;

public class GobangConstant {
    // 定义各种棋形的分数
    /**
     * 待分析的棋型列表的长度
     */
    public static final int CHESS_SIZE = 15;
    /**
     * 棋盘的格子数 比CHESS_SIZE小1
     */
    public static final int CHESS_GRID = CHESS_SIZE - 1;
    /**
     * 电脑AI善防or善攻（false=善防，true=善攻）
     */
    public static boolean ATTACT_OR_DEFENSE = false;
    /**
     * 分析长度
     */
    public static final int ANALYZE_LEN = 8;
    /**
     * 分析半长
     */
    public static final int HALF_LEN = ANALYZE_LEN >> 1;
    /**
     * 当前位置的四个方向，0横、1竖、2左斜、3右斜
     */
    public static final int CHECK_DIR = 4;
    /**
     * 五连:你只需下一步就可以胜利了
     */
    public static final int WU_LIAN = 85;
    /**
     * 活四：两边都可成五的点
     */
    public static final int HUO_SI = 40;
    /**
     * 活三：在走一步可以成活四的点
     */
    public static final int HUO_SAN = 15;
    /**
     * 冲四：只有一端可成五的点
     */
    public static final int CHONG_SI = 6;
    /**
     * 活二：在走一步可成活三的点
     */
    public static final int HUO_ER = 4;
    /**
     * 眠三：在走一步可成冲四的点
     */
    public static final int MIAN_SAN = 2;

    /**
     * 眠二：在走一步可成眠三的点
     */
    public static final int MIAN_ER = 1;
    // -------------------------------------------------------------
    /**
     * 用来存棋子排列对应的分数。
     * 2 表示不可落子点（对方棋子或者边界之外）
     * 1 表示己放落子点
     * 0 表示未落子点
     */
    public static final HashMap<String, Integer> map = new HashMap<String, Integer>();
    
    static {
	initmap();
    }

    private static void initmap() {
	map.put("011110", 100000); // 活四

	map.put("211110", 40000); // 冲四
	map.put("211101", 40000); // 冲四
	map.put("211011", 40000); // 冲四
	map.put("210111", 40000); // 冲四
	map.put("201111", 40000); // 冲四

	map.put("011100", 18000);// 活三
	map.put("011010", 18000);// 活三
	map.put("010110", 18000);// 活三
	map.put("001110", 18000);// 活三

	map.put("011000", 7000);// 活二
	map.put("010100", 7000);// 活二
	map.put("010010", 7000);// 活二
	map.put("001100", 7000);// 活二
	map.put("001010", 7000);// 活二
	map.put("000110", 7000);// 活二

	map.put("211100", 5000);// 眠三
	map.put("211010", 5000);// 眠三
	map.put("211001", 5000);// 眠三
	map.put("210110", 5000);// 眠三
	map.put("210101", 5000);// 眠三
	map.put("210011", 5000);// 眠三
	map.put("201110", 5000);// 眠三
	map.put("201101", 5000);// 眠三
	map.put("201011", 5000);// 眠三
	map.put("200111", 5000);// 眠三

	map.put("211000", 2000);// 眠二
	map.put("210100", 2000);// 眠二
	map.put("210010", 2000);// 眠二
	map.put("210001", 2000);// 眠二
	map.put("201100", 2000);// 眠二
	map.put("201010", 2000);// 眠二
	map.put("201001", 2000);// 眠二
	map.put("200110", 2000);// 眠二
	map.put("200101", 2000);// 眠二
	map.put("200011", 2000);// 眠二

	map.put("010000", 1500);// 活一
	map.put("001000", 1500);// 活一
	map.put("000100", 1500);// 活一
	map.put("000010", 1500);// 活一

	map.put("210000", 700);// 眠一
	map.put("201000", 700);// 眠一
	map.put("200100", 700);// 眠一
	map.put("200010", 700);// 眠一
	map.put("200001", 700);// 眠一

	map.put("200000", 0);//
	map.put("000000", 0);//
    }

    /**
     * 分析存在五连
     * 
     * @param tmpChess
     */
    public boolean analyzeWulian(int[] tmpChess, int isWho) {
	int count = 0;
	for (int i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN - (i + 1)] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	for (int i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN + i] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (count == 4) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * 分析活四 return 是否存在活四
     * 
     * @param tmpChess
     */
    public boolean analyzeHuosi(int[] tmpChess, int isWho) {
	int count = 0;
	int i = 0;
	boolean isSpace = false;
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN - (i + 1)] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN - (i + 1)] == 0) {
	    isSpace = true;
	}
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN + i] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN + i] == 0) {
	    isSpace = true;
	} else {
	    isSpace = false;
	}
	if (count == 3 && isSpace) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * 分析活三 return 是否存在活三
     * 
     * @param tmpChess
     */
    public boolean analyzeHuosan(int[] tmpChess, int isWho) {
	int count = 0;
	int i = 0;
	boolean isSpace = false;
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN - (i + 1)] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN - (i + 1)] == 0) {
	    isSpace = true;
	}
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN + i] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN + i] == 0) {
	    isSpace = true;
	} else {
	    isSpace = false;
	}
	if (count == 2 && isSpace) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * 分析冲四 return 是否存在冲四
     * 
     * @param tmpChess
     */
    public boolean analyzeChongsi(int[] tmpChess, int isWho) {
	int count = 0;
	int i = 0;
	boolean isSpace = false;
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN - (i + 1)] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN - (i + 1)] == 0) {
	    isSpace = true;
	}
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN + i] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN + i] == 0) {
	    isSpace = true;
	}
	if (count == 3 && isSpace) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * 分析活二 return 是否存在活二
     * 
     * @param tmpChess
     */
    public boolean analyzeHuoEr(int[] tmpChess, int isWho) {

	int count = 0;
	int i = 0;
	boolean isSpace = false;
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN - (i + 1)] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN - (i + 1)] == 0) {
	    isSpace = true;
	}
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN + i] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN + i] == 0) {
	    isSpace = true;
	} else {
	    isSpace = false;
	}
	if (count == 1 && isSpace) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * 分析眠三 return 是否存在眠三
     * 
     * @param tmpChess
     */
    public boolean analyzeMianSan(int[] tmpChess, int isWho) {
	int count = 0;
	int i = 0;
	boolean isSpace = false;
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN - (i + 1)] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN - (i + 1)] == 0) {
	    isSpace = true;
	}
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN + i] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN + i] == 0) {
	    isSpace = true;
	}
	if (count == 2 && isSpace) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * 分析眠二 return 是否存在眠二
     * 
     * @param tmpChess
     */
    public boolean analyzeMianEr(int[] tmpChess, int isWho) {
	int count = 0;
	int i = 0;
	boolean isSpace = false;
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN - (i + 1)] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN - (i + 1)] == 0) {
	    isSpace = true;
	}
	for (i = 0; i < HALF_LEN; i++) {
	    if (tmpChess[HALF_LEN + i] == isWho) {
		count++;
	    } else {
		break;
	    }
	}
	if (tmpChess[HALF_LEN + i] == 0) {
	    isSpace = true;
	}
	if (count == 1 && isSpace) {
	    return true;
	}
	return false;
    }
}
