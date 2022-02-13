import React from 'react';
import { Link } from 'react-router-dom';

function NavBar() {
	return (
		<div className="w-full flex justify-between py-3">
			<h1>
				<Link
					to="/"
					className="bg-white block w-48 py-2 text-center shadow-md rounded-lg"
				>
					Community
				</Link>
			</h1>
			<nav className="flex">
				<Link
					to="/login"
					className="bg-blue-300 w-24 py-2 shadow-md rounded-lg text-center mr-2"
				>
					로그인
				</Link>
				<Link
					to="/signup"
					className="bg-blue-300 w-24 py-2 shadow-md rounded-lg text-center"
				>
					회원가입
				</Link>
			</nav>
		</div>
	);
}

export default NavBar;
