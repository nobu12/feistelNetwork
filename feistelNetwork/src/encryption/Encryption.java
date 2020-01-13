package encryption;

import common.AbstractEncryption;

/**
 * 暗号化クラス
 * @author nobu
 *
 */
public class Encryption extends AbstractEncryption {
	@Override
	protected byte encryption(byte left, byte right, int[] key, int roundNumber) {
		return (byte) (left ^ roundFunction(right, key[roundNumber]));
	}
}
