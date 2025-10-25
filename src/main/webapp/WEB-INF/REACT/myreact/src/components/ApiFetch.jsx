import React, { useState, useEffect } from "react";

export const ApiFetch = () => {
	const [stones, setStone] = useState([]);

	useEffect(() => {
		// APIをfetchする(呼び出す)
		fetch("https://it-softtech.com/emsm/apiTest", { method: "GET" })
			// レスポンスのデータ形式をjsonに設定
			.then((res) => res.json())
			// APIから渡されるレスポンスデータ(data)をstateにセットする
			.then((data) => {
				setStone(data);
			});
	}, []);

	return (
		<div>
			<ul>
				<li>{stones.month}</li>
				<li>{stones.color}</li>
				<li>{stones.name}</li>
			</ul>
		</div>
	);
};