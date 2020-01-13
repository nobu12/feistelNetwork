package encryption;

import common.AbstractEncryption;
import main.Main;

/**
 * 復号化クラス
 * @author nobu
 *
 */
public class Decryption extends AbstractEncryption {
	@Override
	protected byte encryption(byte left, byte right, int[] key, int roundNumber) {
		return (byte) (left ^ roundFunction(right, key[Main.ROUND_EXEC_TIME - roundNumber - 1]));
	}
}
