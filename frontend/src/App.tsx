import React from 'react';
import { Route, Routes } from 'react-router-dom';

import Dashboard from './layouts/Dashboard';

function App() {
	return (
		<Routes>
			<Route path="/admin" element={<Dashboard />} />
		</Routes>
	);
}

export default App;
