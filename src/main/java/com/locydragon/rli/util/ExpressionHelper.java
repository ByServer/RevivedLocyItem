package com.locydragon.rli.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class ExpressionHelper {
	public static double run(Player who, String param) {
		return new Calculator().calculate(who, PlaceholderAPI.setPlaceholders(who, param));
	}

	public static double levenshtein(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int[][] dif = new int[len1 + 1][len2 + 1];
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}
				dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
						dif[i - 1][j] + 1);
			}
		}
		float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
		return similarity;
	}
	public static String getPercentValue( float similarity){
		NumberFormat fmt = NumberFormat.getPercentInstance();
		fmt.setMaximumFractionDigits(2);
		return fmt.format(similarity);
	}

	private static int min(int... is) {
		int min = Integer.MAX_VALUE;
		for (int i : is) {
			if (min > i) {
				min = i;
			}
		}
		return min;
	}
}
