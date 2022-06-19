import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import React from 'react';
import { Link as RouterLink } from 'react-router-dom';

interface Titleprops {
	title: Array<string>;
}

function FooterNavigation({ title }: Titleprops) {
	return (
		<Typography variant="body2" color="text.secondary" align="center">
			{title.map((item: string) => (
				<Link component={RouterLink} to="#" color="inherit">
					{item}
				</Link>
			))}
		</Typography>
	);
}

export default FooterNavigation;
