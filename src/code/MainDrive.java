package code;

import java.util.Scanner;

public class MainDrive {

	public static void main(String[] args) {

		/*
		 * 1. 문제 설명 - 내 1년 수익을 (만원단위로) 입력하면, 세금을 제외한 실 수령액은 얼마?
		 * 
		 * 2. 과세표준 - 1000만원 이하 : 6% 떼감. 
		 * - 1000 ~ 4000이하 : 15% 
		 * - 4000 ~ 8000이하 : 24% 
		 * - 8000 초과 : 35%
		 * 
		 * Ex. A-1000 (940), B-1000 (940) + 1 (0.85) => 940.85 10000 => 1000(940) +
		 * 3000(2550) + 4000(3040) + 2000(1300) 940+2550+3040+1300=7830
		 */

		Scanner scan = new Scanner(System.in);

		System.out.print("세전 연 수익금을 만원단위로 입력해주세요 : ");
		int userInput = scan.nextInt(); // 연수익 사용자 입력값

		long calculRemainMoney = userInput;
		int[] taxSection = { 0, 1000, 4000, 8000 }; // 과세 변동 구간
		double[] taxRate = { 0.06, 0.15, 0.24, 0.35 }; // 과세 구간별 세율
		double[] afterTaxMoney = new double[3]; // 구간별 금액 초과시 세후금액

		double resultMoney = 0; // 세후 실수령액

//		afterTaxMony(구간별 세후금액) 배열 값 초기화 
		for (int i = 0; i < 3; i++) {
			afterTaxMoney[i] = (taxSection[i + 1] - taxSection[i]) * (1 - taxRate[i]);
//			System.out.println(afterTaxMoney[i]);
		}

//		풀이 시작
		int i = 0;
		while (true) {
			if (calculRemainMoney <= (taxSection[i + 1] - taxSection[i])) {
				resultMoney += calculRemainMoney * (1 - taxRate[i]);
				break;
			} else {
				resultMoney += afterTaxMoney[i];
				calculRemainMoney -= (taxSection[i + 1] - taxSection[i]);
				i++;
				if (i >= taxSection.length - 1) {
					break;
				}
			}
		}
		if (userInput > taxSection[i]) {
			resultMoney += calculRemainMoney * (1 - taxRate[3]);
		}
		System.out.println(String.format("세후 실수령액은 %d만원 입니다.", (long) resultMoney));
	}
}
