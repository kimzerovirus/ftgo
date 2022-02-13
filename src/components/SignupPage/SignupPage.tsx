import { useNavigate } from 'react-router-dom';

const SignupPage = () => {
	const navigate = useNavigate();

	const submitHandler = (e: React.SyntheticEvent) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			name: { value: string };
			email: { value: string };
			password: { value: string };
		};
		console.log(target.email.value, target.password.value);

		if (target.email.value && target.password.value) navigate('/');
		else alert('빈 칸 존재함');
	};

	return (
		<div className="flex items-center justify-center h-screen bg-gray-100">
			<form
				className="w-72 lg:w-112 lg:p-6 lg:bg-white lg:shadow-md lg:rounded-lg"
				onSubmit={submitHandler}
			>
				<div>
					<h1 className="text-2xl">회원가입</h1>
				</div>

				<div className="mt-4">
					<input
						type="name"
						name="name"
						placeholder="성함"
						className="w-full px-4 py-3 rounded-lg mt-2 border focus:border-blue-600 focus:bg-white focus:outline-none "
						required
					/>
				</div>

				<div className="mt-4">
					<input
						type="email"
						name="email"
						placeholder="이메일 주소"
						className="w-full px-4 py-3 rounded-lg mt-2 border focus:border-blue-600 focus:bg-white focus:outline-none "
						required
					/>
				</div>

				<div className="mt-4">
					<input
						type="password"
						name="password"
						placeholder="비밀번호"
						className="w-full px-4 py-3 rounded-lg mt-2 border focus:border-blue-600
                  focus:bg-white focus:outline-none"
						required
					/>
				</div>

				<div className="mt-4">
					<input
						type="passwordconfirm"
						name="passwordconfirm"
						placeholder="비밀번호 확인"
						className="w-full px-4 py-3 rounded-lg mt-2 border focus:border-blue-600
                  focus:bg-white focus:outline-none"
						required
					/>
				</div>

				<button
					type="submit"
					className="w-full block bg-blue-600 hover:bg-blue-900 focus:bg-blue-900 text-white font-semibold rounded-lg
                px-4 py-3 mt-6"
				>
					회원가입
				</button>
			</form>
		</div>
	);
};

export default SignupPage;
