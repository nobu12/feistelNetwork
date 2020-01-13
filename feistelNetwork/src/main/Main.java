package main;

import common.AbstractEncryption;
import encryption.Decryption;
import encryption.Encryption;

public class Main {
	// ラウンド回数
	public static int ROUND_EXEC_TIME = 3;

	// ラウンド関数で使用する秘密鍵
	// ラウンド関数はビットシフト演算をしているため、8よりも小さい数を指定しなければ暗号化ができない
	// 攻撃者に解読されないようにするため、この鍵は秘密にしなければならない
	private static int[] SECRET_KEY = {2, 3, 6};

	public static void main(String[] args) {
		// 秘密データ
		String secretData = "testcase";

		AbstractEncryption encryption = new Encryption();
		AbstractEncryption decryption = new Decryption();

		// 暗号化
		String encryptionData = encryption.execEncryption(secretData, SECRET_KEY);
		// 復号化
		String deryptionData = decryption.execEncryption(encryptionData, SECRET_KEY);

		System.out.println("暗号化データ：" + encryptionData);
		System.out.println("復号化データ：" + deryptionData);

	}
}
