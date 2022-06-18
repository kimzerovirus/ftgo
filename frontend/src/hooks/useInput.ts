import { useCallback, useState } from 'react';

export const useInputs = (initialValue: any) => {
	const [inputValue, setInputValue] = useState(initialValue);

	const handler = useCallback(
		(e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
			const { name, value } = e.target;
			setInputValue({
				...inputValue,
				[name]: value,
			});
		},
		[inputValue],
	);
	return [inputValue, handler, setInputValue];
};
