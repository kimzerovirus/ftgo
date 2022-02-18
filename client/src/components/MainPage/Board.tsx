import React from 'react';
import { Link } from 'react-router-dom';

function Board() {
	return (
		<>
			<table className="w-full p-5 bg-white shadow-md rounded-lg">
				<thead>
					<tr className="border-b">
						<th>글번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<td className="text-center">123</td>
					<td className="text-center">hello world</td>
					<td className="text-center">hero123</td>
					<td className="text-center">2021-12-10</td>
				</tbody>
			</table>
			<div className="flex flex-row-reverse w-full">
				<Link
					to="register"
					className="bg-gray-300 block w-24 py-2 shadow-md rounded-lg text-center mt-2"
				>
					글쓰기
				</Link>
			</div>
		</>
	);
}

export default Board;
