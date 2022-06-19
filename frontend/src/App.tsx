import { ThemeProvider } from '@mui/material/styles';
import React from 'react';
import { Route, Routes } from 'react-router-dom';

import Dashboard from './components/admin/layouts/Dashboard';
import LoginPage from './pages/web/LoginPage';
import CustomTheme from './utils/customizeTheme';

function App() {
	return (
		<ThemeProvider theme={CustomTheme}>
			<Routes>
				{/* user */}
				<Route path="/login" element={<LoginPage />} />

				{/* admin */}
				<Route path="/admin" element={<Dashboard />} />
			</Routes>
		</ThemeProvider>
	);
}

export default App;
