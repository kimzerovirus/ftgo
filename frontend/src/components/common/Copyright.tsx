import { Typography } from '@mui/material';
import Link from '@mui/material/Link';

export default function Copyright(props: any) {
	return (
		<Typography
			variant="body2"
			color="text.secondary"
			align="center"
			{...props}
		>
			{'Copyright © '}
			{new Date().getFullYear()}{' '}
			<Link
				color="inherit"
				href="https://github.com/kimzerovirus"
				target="_blank"
			>
				KIMZEROVIRUS
			</Link>
			{'. All Rights Reserved.'}
		</Typography>
	);
}
