package com.cyx.gobang.five.constant;

import java.util.HashMap;

public class GobangConstant {
    // ����������εķ���
    /**
     * �������������б�ĳ���
     */
    public static final int CHESS_SIZE = 15;
    /**
     * ���̵ĸ����� ��CHESS_SIZEС1
     */
    public static final int CHESS_GRID = CHESS_SIZE - 1;
    /**
     * ����AI�Ʒ�or�ƹ���false=�Ʒ���true=�ƹ���
     */
    public static boolean ATTACT_OR_DEFENSE = false;
    /**
     * ��������
     */
    public static final int ANALYZE_LEN = 8;
    /**
     * �����볤
     */
    public static final int HALF_LEN = ANALYZE_LEN >> 1;
    /**
     * ��ǰλ�õ��ĸ�����0�ᡢ1����2��б��3��б
     */
    public static final int CHECK_DIR = 4;
    /**
     * ����:��ֻ����һ���Ϳ���ʤ����
     */
    public static final int WU_LIAN = 85;
    /**
     * ���ģ����߶��ɳ���ĵ�
     */
    public static final int HUO_SI = 40;
    /**
     * ����������һ�����Գɻ��ĵĵ�
     */
    public static final int HUO_SAN = 15;
    /**
     * ���ģ�ֻ��һ�˿ɳ���ĵ�
     */
    public static final int CHONG_SI = 6;
    /**
     * ���������һ���ɳɻ����ĵ�
     */
    public static final int HUO_ER = 4;
    /**
     * ����������һ���ɳɳ��ĵĵ�
     */
    public static final int MIAN_SAN = 2;

    /**
     * �߶�������һ���ɳ������ĵ�
     */
    public static final int MIAN_ER = 1;
    // -------------------------------------------------------------
    /**
     * �������������ж�Ӧ�ķ�����
     * 2 ��ʾ�������ӵ㣨�Է����ӻ��߽߱�֮�⣩
     * 1 ��ʾ�������ӵ�
     * 0 ��ʾδ���ӵ�
     */
    public static final HashMap<String, Integer> map = new HashMap<String, Integer>();
    
    static {
	initmap();
    }

    private static void initmap() {
	map.put("011110", 100000); // ����

	map.put("211110", 40000); // ����
	map.put("211101", 40000); // ����
	map.put("211011", 40000); // ����
	map.put("210111", 40000); // ����
	map.put("201111", 40000); // ����

	map.put("011100", 18000);// ����
	map.put("011010", 18000);// ����
	map.put("010110", 18000);// ����
	map.put("001110", 18000);// ����

	map.put("011000", 7000);// ���
	map.put("010100", 7000);// ���
	map.put("010010", 7000);// ���
	map.put("001100", 7000);// ���
	map.put("001010", 7000);// ���
	map.put("000110", 7000);// ���

	map.put("211100", 5000);// ����
	map.put("211010", 5000);// ����
	map.put("211001", 5000);// ����
	map.put("210110", 5000);// ����
	map.put("210101", 5000);// ����
	map.put("210011", 5000);// ����
	map.put("201110", 5000);// ����
	map.put("201101", 5000);// ����
	map.put("201011", 5000);// ����
	map.put("200111", 5000);// ����

	map.put("211000", 2000);// �߶�
	map.put("210100", 2000);// �߶�
	map.put("210010", 2000);// �߶�
	map.put("210001", 2000);// �߶�
	map.put("201100", 2000);// �߶�
	map.put("201010", 2000);// �߶�
	map.put("201001", 2000);// �߶�
	map.put("200110", 2000);// �߶�
	map.put("200101", 2000);// �߶�
	map.put("200011", 2000);// �߶�

	map.put("010000", 1500);// ��һ
	map.put("001000", 1500);// ��һ
	map.put("000100", 1500);// ��һ
	map.put("000010", 1500);// ��һ

	map.put("210000", 700);// ��һ
	map.put("201000", 700);// ��һ
	map.put("200100", 700);// ��һ
	map.put("200010", 700);// ��һ
	map.put("200001", 700);// ��һ

	map.put("200000", 0);//
	map.put("000000", 0);//
    }

    /**
     * ������������
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
     * �������� return �Ƿ���ڻ���
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
     * �������� return �Ƿ���ڻ���
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
     * �������� return �Ƿ���ڳ���
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
     * ������� return �Ƿ���ڻ��
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
     * �������� return �Ƿ��������
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
     * �����߶� return �Ƿ�����߶�
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
