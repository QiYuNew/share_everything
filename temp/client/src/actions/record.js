import axios from 'axios';
import {setAlert} from "./alert";
import {
    GET_RECORD,
    RECORD_ERROR,
    CLEAR_RECORD, SHEET_ERROR
} from "./types";
import {getCurrentUserSheet} from "./sheet";

// get all records by sheet id
export const getActiveRecordsBySheetId = (id) => async dispatch => {
    try {
        const res = await axios.get(`/api/sheet/${id}`);

        dispatch({
            type: GET_RECORD,
            payload: res.data
        })
    } catch (err) {
        dispatch({
            type: RECORD_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
};

// clean records
export const cleanRecords = () => async dispatch => {
    dispatch({
        type: CLEAR_RECORD
    })
};

// update a record
export const updateRecord = (sid, rid, formData, history) => async dispatch => {
    try {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        };
        const res = await axios.put(`/api/sheet/active/${sid}/${rid}`, formData, config);

        dispatch({
            type: GET_RECORD,
            payload: res.data
        })
        dispatch(setAlert('Record Updated', 'success', 1500));
        history.push(`/sheet-record/${sid}`)

    } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
            errors.forEach(error => dispatch(setAlert(error.msg, 'danger')));
        }

        dispatch({
            type: RECORD_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
}

// delete a active record
export const deleteActiveRecord = (sid, rid, history) => async (dispatch) => {
    try {
        const res = await axios.delete(`/api/sheet/active/${sid}/${rid}`);

        dispatch({
            type: GET_RECORD,
            payload: res.data
        })
        dispatch(setAlert('Record Deleted', 'success', 1500));
        history.push(`/sheet-record/${sid}`)

    } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
            errors.forEach(error => dispatch(setAlert(error.msg, 'danger')));
        }

        dispatch({
            type: RECORD_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
};

// create a active record
export const createActiveRecord = (sid, formData, history) => async dispatch => {
    try {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        };
        await axios.post(`/api/sheet/active/${sid}`, formData, config);

        dispatch(setAlert('Record is created', 'success', 1500));
        // history.push(`/sheet-record/${sid}`); // no return

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

// archive a active record
export const archiveActiveRecord = (sid, rid, formData, history) => async dispatch => {
    try {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        };
        const res = await axios.post(`/api/sheet/active-archive/${sid}/${rid}`, formData, config);
        dispatch({
            type: GET_RECORD,
            payload: res.data
        })

        dispatch(setAlert('Record is archived', 'success', 1500));
        history.push(`/sheet-record/${sid}`);

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

// restore a archived record
export const restoreArchivedRecord = (sid, rid, history) => async dispatch => {
    try {

        const res = await axios.get(`/api/sheet/archive-active/${sid}/${rid}`);
        dispatch({
            type: GET_RECORD,
            payload: res.data
        })

        dispatch(setAlert('Record is restored', 'success', 1500));
        history.push(`/sheet-record/${sid}`);

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

// delete a archived record
export const deleteArchivedRecord = (sid, rid, history) => async (dispatch) => {
    try {
        const res = await axios.delete(`/api/sheet/archive/${sid}/${rid}`);

        dispatch({
            type: GET_RECORD,
            payload: res.data
        })
        dispatch(setAlert('Record Deleted', 'success', 1500));
        history.push(`/sheet-record/${sid}`)

    } catch (err) {
        const errors = err.response.data.errors;

        if (errors) {
            errors.forEach(error => dispatch(setAlert(error.msg, 'danger')));
        }

        dispatch({
            type: RECORD_ERROR,
            payload: {msg: err.response.statusText, status: err.response.status}
        })
    }
};

// save Clipboard
export const saveClipboard = (sid, formData, history) => async dispatch => {
    try {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        };
        await axios.post(`/api/sheet/clipboard/${sid}`, formData, config);

        dispatch(setAlert('Clipboard is saved', 'success', 1500));
        // history.push(`/sheet-record/${sid}`); // no return

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
