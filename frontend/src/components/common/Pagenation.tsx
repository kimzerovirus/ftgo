import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { useEffect, useState } from 'react';

function Pagenation() {
	const [cpage, setCpage] = useState(1);

	useEffect(() => {
		console.log(cpage);
	}, [cpage]);

	const handlePage = (e: any) => {
		const currentPage = parseInt(e.target.outerText);
		setCpage(currentPage);
	};

	return (
		<Stack spacing={2}>
			<Pagination
				defaultPage={1}
				count={5}
				shape="rounded"
				variant="outlined"
				onChange={e => handlePage(e)}
			/>
		</Stack>
	);
}

export default Pagenation;
