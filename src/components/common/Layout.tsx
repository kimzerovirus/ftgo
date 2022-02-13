import React from 'react';

function Layout({ children }: any) {
	return (
		<div className="w-full bg-gray-100 h-screen">
			<div className="w-full lg:container lg:mx-auto">{children}</div>
		</div>
	);
}

export default Layout;
