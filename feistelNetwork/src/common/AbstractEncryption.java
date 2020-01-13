package common;

import main.Main;

/**
 * 暗号化と復号化の両方を受け持つクラス
 * @author nobu
 *
 */
public abstract class AbstractEncryption {
	/**
	 * 暗号化と復号化を実行する
	 * @param secretData 秘密データ
	 * @param key 秘密鍵
	 */
	public String execEncryption(String secretData, int key[]) {
		String left = secretData.substring(0, secretData.length() / 2);
		String right = secretData.substring(secretData.length() / 2, secretData.length());

		// 左側の文字列データ、暗号化されたデータを入れるための変数
		byte leftArray[][];
		// 左側の文字は秘密データの文字数が偶数、奇数の場合を考慮している
		if (secretData.length() % 2 == 0) {
			leftArray = new byte[Main.ROUND_EXEC_TIME+1][left.length()];
		} else {
			leftArray = new byte[Main.ROUND_EXEC_TIME+1][left.length() + 1];
		}
		// 右側の文字列データ、暗号化されたデータを入れるための変数
		byte rightArray[][] = new byte[Main.ROUND_EXEC_TIME+1][right.length()];

		// 入力値の初期化
		for (int i = 0; i < left.length(); i++) {
			leftArray[0][i] = (byte) left.charAt(i);
		}
		for (int i = 0; i < right.length(); i++) {
			rightArray[0][i] = (byte) right.charAt(i);
		}

		// 暗号化, 復号化
		for (int i = 0; i < Main.ROUND_EXEC_TIME; i++) {
			for (int j = 0; j < right.length(); j++) {
				rightArray[i + 1][j] = encryption(leftArray[i][j], rightArray[i][j], key, i);
				leftArray[i + 1][j] = rightArray[i][j];
			}
			// 最終ラウンドでは、左右を交換しないため、既に交換済みのデータを元に戻す
			if (i == Main.ROUND_EXEC_TIME - 1) {
				byte tmp[];
				tmp = leftArray[i + 1];
				leftArray[i + 1] = rightArray[i + 1];
				rightArray[i + 1] = tmp;
			}
		}

		String returnData = "";
		for (int i = 0; i < right.length(); i++) {
			returnData = returnData + String.valueOf((char)leftArray[Main.ROUND_EXEC_TIME][i]);
		}
		for (int i = 0; i < right.length(); i++) {
			returnData = returnData + String.valueOf((char)rightArray[Main.ROUND_EXEC_TIME][i]);
		}
		return returnData;
	}

	/**
	 * 暗号化
	 * @param left 左側のデータ
	 * @param right 右側のデータ
	 * @param key 秘密鍵
	 * @param roundNumber
	 * @return 暗号化の結果
	 */
	abstract protected byte encryption(byte left, byte right, int[] key, int roundNumber);

	/**
	 * ラウンド関数、秘密鍵で指定された値だけ右シフトする
	 * @param right 右側データ
	 * @param key 秘密鍵
	 * @return ラウンド関数の結果
	 */
	protected byte roundFunction(byte right, int key) {
		return (byte) (right << key);
	}

}