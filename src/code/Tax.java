package code;

public class Tax {

	int taxIndex = 3;
	int[] taxSection = new int[taxIndex]; // 과세 변동 구간
	double[] taxRate = new double[taxIndex]; // 과세 구간별 세율
	double[] afterTaxMoney = new double[taxSection.length-1] ; // 구간별 금액 초과시 세후금액

//	afterTaxMony(구간별 세후금액) 배열 값 초기화 
	for (int i = 0; i < afterTaxMoney.length; i++) {
		afterTaxMoney[taxIndex] = (taxSection[taxIndex + 1] - taxSection[taxIndex]) * (1 - taxRate[taxIndex]);
	}

}
