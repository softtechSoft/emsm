package com.softtech.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DataUtilTest {

	@Test
	public void testFunctionText01() {
		
		// 前提条件確認
		String function = "aaaa;bbb";
		assertThat(function)
			.as("【条件】パラメータfunctionがaaaa;bbb")
			.isEqualTo("aaaa;bbb");
		
		// 実行
		String result = DataUtil.functionText(function);
		
		// 結果確認
		assertThat(result)
			.as("【確認】戻り値がbbbであること。")
			.isEqualTo("bbb");
		
	}
}
