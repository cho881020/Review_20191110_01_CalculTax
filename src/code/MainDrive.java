package code;

import java.util.Scanner;

public class MainDrive {

	public static void main(String[] args) {

		/*
		 * 1. 문제 설명 - 내 1년 수익을 (만원단위로) 입력하면, 세금을 제외한 실 수령액은 얼마?
		 * 
		 * 2. 과세표준 
		 * - 1000만원 이하 : 6% 떼감. 
		 * - 1000 ~ 4000이하 : 15% 
		 * - 4000 ~ 8000이하 : 24% 
		 * - 8000 초과 : 35%
		 * 
		 * Ex. A-1000 (940), B-1000 (940) + 1 (0.85) => 940.85
		 * 10000 => 1000(940)+3000(2550)+4000(3040)+2000(1300) 940+2550+3040+1300=7830
		 */

		Scanner scan = new Scanner(System.in);

		System.out.print("세전 연 수익금을 만원단위로 입력해주세요 : ");
		int userInput = scan.nextInt(); // 사용자 입력값: 세전 연 수익금
		long calculRemainMoney = userInput;  //잔여정산액 변수 선언, 초기값을 사용자 최초 입력값으로 초기화
		int[] taxSection = { 0, 1000, 4000, 8000 }; // 과세 변동 구간
		double[] taxRate = { 0.06, 0.15, 0.24, 0.35 }; // 과세 구간별 세율
		double[] afterTaxMoney = new double[taxSection.length-1]; // 구간별 금액 초과시 세후금액
		double resultMoney = 0; // 세후 실수령액(최종결과값)

//		afterTaxMony(구간별 세후금액) 배열 값 초기화 
		for (int i = 0; i < afterTaxMoney.length; i++) {
			afterTaxMoney[i] = (taxSection[i + 1] - taxSection[i]) * (1 - taxRate[i]);
//			{940, 2550, 3040}
//			System.out.println(afterTaxMoney[i]);
		}

//		풀이 시작
		int i = 0;	//	while문 break되기 전까지 index 증가시킬 것임
		
//		break 조건1: 잔여정산액이 과세구간 이내에 포함될 때 마지막 계산 후 break 됨
//		break 조건2: i(index) 값이 과세변동구간 마지막까지 도달했을 경우 마지막 잔여정산액 계산여부 판단 후 break
		while (true) {
			//	잔여정산액이 과세구간 이내에 포함되는 지 판단
			if (calculRemainMoney <= (taxSection[i + 1] - taxSection[i])) {
				resultMoney += calculRemainMoney * (1 - taxRate[i]);
				break; // 잔여정산액이 과세구간 이내에 포함되면 마지막 계산을 완료하고 break
			} else {	
				// 여기서 else는 잔여정산액이 과세구간을 초과
				// 해당 과세구간에 대해 미리 계산해놓은 afterTaxMoney 값을 resultMoney에 저장 후
				// 해당 과세구간만큼 잔여정산액을 차감
				resultMoney += afterTaxMoney[i];
				calculRemainMoney -= (taxSection[i + 1] - taxSection[i]);
				i++;	// 다음 과세구간에 대해 판단해야 하므로 index 증가
				
				//	index가 마지막 과세구간에 도달했는지 판단
				//	index가 마지막 과세구간에 도달했을 경우 다음 과세구간으로 넘기면 안됨
				//	(다음 과세구간이 존재하지 않으므로)
				if (i >= taxSection.length - 1) {
					
					//	(다음 과세구간이 존재하지 않으므로)
					//	마지막 과세구간은 첫번째 if문으로 넘기지 않고
					//	여기서 마지막 계산을 처리해줌
					if (userInput > taxSection[i]) {	//	최초 입력금액이 구간 최대금액 이하일 경우 이전 단계에서 계산이 완료됨
						resultMoney += calculRemainMoney * (1 - taxRate[i]);
					}
					break;
				}
			}
		}

		System.out.println(String.format("세후 실수령액은 %d만원 입니다.", (long) resultMoney));
	}
}
