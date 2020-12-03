import axios from 'axios';
import {setAlert} from "./alert";
import {
    CLEAR_SHEET,
    GET_SHEET,
    SHEET_ERROR
} from "./types";

// get current users sheet
export const getCurrentUserSheet = () => async dispatch => {
    try {
        const res = await axios.get('/api/sheet');

        dispatch({
            type: GET_SHEET,
            payload: res.data
        })
    } catch (err) {
        dispatch({
            type: SHEET_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
};

// update a sheet name
export const updateSheetName = (sid, formData, history) => async dispatch => {
    try {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        };
        await axios.put(`/api/sheet/${sid}`, formData, config);

        await getCurrentUserSheet();
        dispatch(setAlert('Sheet Name Updated', 'success', 1500));
        history.push('/dashboard');

    } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
            errors.forEach(error => dispatch(setAlert(error.msg, 'danger')));
        }

        dispatch({
            type: SHEET_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
}

// create a sheet
export const createSheet = (formData, history) => async dispatch => {
    try {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        };
        await axios.post(`/api/sheet`, formData, config);

        dispatch(setAlert('Sheet is created', 'success', 1500));
        history.push('/dashboard');

    } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
            errors.forEach(error => dispatch(setAlert(error.msg, 'danger')));
        }

        dispatch({
            type: SHEET_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
}

// delete a sheet
export const deleteSheet = (id, history) => async (dispatch) => {
    try {
        const res = await axios.delete(`/api/sheet/${id}`);

        dispatch({
            type: GET_SHEET,
            payload: res.data
        })
        dispatch(setAlert('Sheet Removed', 'success', 1500));
        await history.push('/dashboard');

    } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
            errors.forEach(error => dispatch(setAlert(error.msg, 'danger')));
        }

        dispatch({
            type: SHEET_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
};
