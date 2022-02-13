import React from 'react';
import Layout from '../common/Layout';
import NavBar from '../NavBar/NavBar';
import Board from './Board';

const MainPage = () => {
	return (
		<Layout>
			<div className="w-full bg-gray-100 h-screen">
				<div className="w-full lg:container lg:mx-auto">
					<NavBar />
					<Board />
				</div>
			</div>
		</Layout>
	);
};

export default MainPage;
