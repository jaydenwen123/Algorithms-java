package com.wxf.data.huffman;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

public class TestHuffman {

	public TestHuffman() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
				+ "depending on the characteristics of the data being compressed. 中华崛起";
		Map<Character, Integer> statistics = HuffmanTree
				.processStatistic(oriStr);
		// for(Entry<Character, Integer> entry:statistics.entrySet()){
		// System.out.println(entry.getKey()+","+entry.getValue());
		// }
		String encodedBinariStr = HuffmanTree.encode(oriStr, statistics);
		String decodedStr = HuffmanTree.decode(encodedBinariStr, statistics);
		System.out.println("Original sstring: " + oriStr);
		System.out.println("Huffman encoed binary string: " + encodedBinariStr);
		System.out.println("decoded string from binariy string: " + decodedStr);
		System.out.println("binary string of UTF-8: "
				+ getStringOfByte(oriStr, Charset.forName("UTF-8")));
		System.out.println("binary string of UTF-16: "
				+ getStringOfByte(oriStr, Charset.forName("UTF-16")));
		System.out.println("binary string of US-ASCII: "
				+ getStringOfByte(oriStr, Charset.forName("US-ASCII")));
		System.out.println("binary string of GB2312: "
				+ getStringOfByte(oriStr, Charset.forName("GB2312")));
	}

	/**
	 * 取得字节的字符串
	 * 
	 * @param str
	 *            二进制字符串
	 * @param charset
	 *            字符集
	 * @return 返回的是编码后的字符集
	 */
	public static String getStringOfByte(String str, Charset charset) {
		if (str == null || str.equals("")) {
			return "";
		}

		byte[] byteArray = str.getBytes(charset);
		int size = byteArray.length;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			byte temp = byteArray[i];
			buffer.append(getStringOfByte(temp));
		}

		return buffer.toString();
	}

	/**
	 * 获取单个字节的二进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String getStringOfByte(byte b) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 7; i >= 0; i--) {
			byte temp = (byte) ((b >> i) & 0x1);
			buffer.append(String.valueOf(temp));
		}

		return buffer.toString();
	}
}
