import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import Moment from "react-moment";
import {
    updateRecord,
    deleteActiveRecord,
    archiveActiveRecord,
} from "../../actions/record";

const initialState = {
    position: '',
    method:'',
    submittime:'',
    apppassword:'',
    note:'',
};

const ActiveRecordRow = ({
                             updateRecord,
                             deleteActiveRecord,
                             archiveActiveRecord,
                             auth, sid,
                             rec:{_id, position, method, submittime, apppassword, note}, history}) => {

    const [recordData, setrecordData] = useState(initialState);

    useEffect(() => {
        const prev = {...initialState};
        prev.position = position;
        prev.method = method;
        prev.submittime = submittime;
        prev.apppassword = apppassword;
        prev.note = note;
        setrecordData(prev);
    }, [])

    const [displayUpdate, toggleUpdate] = useState(false);

    const onChange = e =>
        setrecordData({...recordData, [e.target.name]: e.target.value });

    const onSubmit = e => {
        e.preventDefault();
        updateRecord(sid, _id, recordData, history)
    }

    const offer = {
        result: true
    };

    const rej = {
        result: false
    };

    return (
        <tr key={_id}>
            {!displayUpdate &&
                <Fragment>
                    <td>{recordData.position}</td>
                    <td className="hide-sm">{recordData.method}</td>
                    <td className="hide-sm">
                        <Moment format='YYYY/MM/DD'>{recordData.submittime}</Moment>
                    </td>
                    <td className="hide-sm">{recordData.apppassword}</td>
                    <td className="hide-sm">
                        <div
                            style={{'width':'200px',
                                'height':'40px',
                                'word-break': 'break-all',
                                'overflow':'auto'}}>
                            {recordData.note}
                        </div>
                    </td>
                </Fragment>
            }

            {displayUpdate &&
                <Fragment>
                    <td>
                        <input className="form form-my" type="text"
                               name="position"
                               value={recordData.position}
                               onChange={e => onChange(e)}
                               required
                        />
                    </td>
                    <td className="hide-sm">
                        <select className="form form-my"
                                name="method"
                                value={recordData.method}
                                onChange={e => onChange(e)}
                                required>
                            <option value="0">* Select Apply Method</option>
                            <option value="Job Portal">Job Portal</option>
                            <option value="Referral">Referral</option>
                            <option value="Other">Other</option>
                        </select>
                    </td>
                    <td className="hide-sm">
                        <input
                            style={{'width': '130px'}}
                            type="datetime-local"
                            name="submittime"
                            value={recordData.submittime}
                            onChange={e => onChange(e)}
                        />
                    </td>
                    <td className="hide-sm">
                        <input className="form form-my" type="text"
                               name="apppassword"
                               value={recordData.apppassword}
                               onChange={e => onChange(e)}
                        />
                    </td>
                    <td className="hide-sm">
                        <input className="form form-my" type="text"
                               name="note"
                               value={recordData.note}
                               onChange={e => onChange(e)}
                        />
                    </td>
                </Fragment>
            }

            <td>
                {!displayUpdate &&
                <Fragment>
                    <button className="btn btn-light" onClick={() => archiveActiveRecord(sid, _id, offer, history)}>
                        <i className="far fa-smile"></i></button>
                    <button className="btn btn-light" onClick={() => archiveActiveRecord(sid, _id, rej, history)}>
                        <i className="far fa-sad-cry"></i></button>
                    <button className="btn btn-primary" onClick={() => toggleUpdate(!displayUpdate)}>
                        <i className="fas fa-edit"></i></button>
                    <button className="btn btn-danger" onClick={() => deleteActiveRecord(sid, _id, history)}>
                        <i className="fas fa-trash"></i></button>
                </Fragment>
                }

                {displayUpdate &&
                <Fragment>
                    <button className="btn btn-success" onClick={e => {onSubmit(e); toggleUpdate(!displayUpdate)}}>
                        <i className="fas fa-check-square"></i></button>
                </Fragment>
                }
            </td>
        </tr>
    )
}

ActiveRecordRow.propTypes = {
    auth: PropTypes.object.isRequired,
    updateRecord: PropTypes.func.isRequired,
    deleteActiveRecord: PropTypes.func.isRequired,
    archiveActiveRecord: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
    auth: state.auth,
});

export default connect(mapStateToProps, {updateRecord, deleteActiveRecord, archiveActiveRecord})(withRouter(ActiveRecordRow));
