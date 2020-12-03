import {GET_RECORD, RECORD_ERROR, CLEAR_RECORD} from "../actions/types";

const initialState = {
    records: null,
    loading: true,
    error: {}
};

export default function (state = initialState, action) {
    const {type, payload} = action;

    switch(type) {
        case GET_RECORD:
            return {
                ...state,
                records: payload,
                loading: false
            };
        case RECORD_ERROR:
            localStorage.removeItem('records')
            return {
                ...state,
                records: null,
                error: payload,
                loading: false
            };
        case CLEAR_RECORD:
            localStorage.removeItem('records')
            return {
                ...state,
                records: null,
                loading: false
            };
        default:
            return state;
    }

}
