import {GET_SHEET, SHEET_ERROR, CLEAR_SHEET} from "../actions/types";

const initialState = {
    sheets: [],
    loading: true,
    error: {}
};

export default function (state = initialState, action) {
    const {type, payload} = action;

    switch(type) {
        case GET_SHEET:
            return {
                ...state,
                sheets: payload,
                loading: false
            };
        case SHEET_ERROR:
            localStorage.removeItem('sheets')
            return {
                ...state,
                sheets: [],
                error: payload,
                loading: false
            };
        case CLEAR_SHEET:
            localStorage.removeItem('sheets')
            return {
                ...state,
                sheets: [],
                loading: false
            };
        default:
            return state;
    }


}
