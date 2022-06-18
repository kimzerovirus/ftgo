import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';

export const addUserListAsync = createAsyncThunk(
	'user/addUserListAsync',
	async (cpage: number, { rejectWithValue }) => {
		try {
		} catch (e) {
			return rejectWithValue(e);
		}
	},
);
