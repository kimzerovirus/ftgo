import React from 'react';
import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';

import App from './App';
import { store } from './store';

// replace console.* for disable log on production
if (process.env.NODE_ENV === 'production') {
	// eslint-disable-next-line @typescript-eslint/no-empty-function
	console.log = () => {};
	// eslint-disable-next-line @typescript-eslint/no-empty-function
	console.error = () => {};
	// eslint-disable-next-line @typescript-eslint/no-empty-function
	console.debug = () => {};
}

const root = ReactDOM.createRoot(
	document.getElementById('root') as HTMLElement,
);

root.render(
	<Provider store={store}>
		<BrowserRouter>
			<App />
		</BrowserRouter>
	</Provider>,
);
