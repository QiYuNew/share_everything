import {combineReducers} from "redux";
import alert from './alert'
import auth from "./auth";
import sheet from "./sheet";
import record from "./record";

export default combineReducers({
    alert,
    auth,
    sheet,
    record
});
