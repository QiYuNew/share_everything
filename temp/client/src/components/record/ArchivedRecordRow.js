import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import Moment from "react-moment";
import {restoreArchivedRecord, deleteArchivedRecord} from "../../actions/record";

const initialState = {
    position: '',
    method:'',
    submittime:'',
    apppassword:'',
    note:'',
};

const ArchivedRecordRow = ({restoreArchivedRecord, deleteArchivedRecord, auth, sid, rec:{_id, position, method, submittime, apppassword, note, archivetime, result}, history}) => {

    const [recordData, setrecordData] = useState(initialState);

    useEffect(() => {
        const prev = {...initialState};
        prev.position = position;
        prev.method = method;
        prev.submittime = submittime;
        prev.apppassword = apppassword;
        prev.note = note;
        prev.archivetime = archivetime;
        prev.result = result;
        setrecordData(prev);
    }, [])

    const [displayUpdate, toggleUpdate] = useState(false);

    // const onChange = e =>
    //     setrecordData({...recordData, [e.target.name]: e.target.value });

    const onSubmit = e => {
        e.preventDefault();
        // updateSheetName(_id, formData, history)
    }

    return (
        <tr key={_id}>
            {!displayUpdate &&
                <Fragment>
                    <td>{recordData.position}</td>
                    <td className="hide-sm">{recordData.method}</td>
                    <td className="hide-sm">
                        <Moment format='YYYY/MM/DD'>{recordData.submittime}</Moment>
                    </td>
                    <td className="hide-sm">
                        <Moment format='YYYY/MM/DD'>{recordData.archivetime}</Moment>
                    </td>
                    <td className="hide-sm">{recordData.apppassword}</td>
                    <td className="hide-sm">
                        <div
                            style={{'width':'120px',
                            'height':'40px',
                            'word-break': 'break-all',
                            'overflow':'auto'}}>
                            {recordData.note}
                        </div>

                    </td>
                    <td className="hide-sm">
                        {recordData.result ? <i className="far fa-smile"></i> : <i className="far fa-sad-cry"></i>}
                    </td>
                </Fragment>
            }

            {/*{displayUpdate &&*/}
            {/*    <Fragment>*/}
            {/*        <td>xxx</td>*/}
            {/*        <td className="hide-sm">xxxx</td>*/}
            {/*        <td className="hide-sm">*/}
            {/*            xxxx*/}
            {/*        </td>*/}
            {/*        <td className="hide-sm">xxxx</td>*/}
            {/*        <td className="hide-sm">xxxx</td>*/}
            {/*    </Fragment>*/}
            {/*}*/}

            <td>
                {!displayUpdate &&
                <Fragment>
                    <button className="btn btn-primary" onClick={() => restoreArchivedRecord(sid, _id, history)}>
                        <i className="fas fa-undo"></i></button>
                    <button className="btn btn-danger" onClick={() => deleteArchivedRecord(sid, _id, history)}>
                        <i className="fas fa-trash"></i></button>
                </Fragment>
                }

                {/*{displayUpdate &&*/}
                {/*<Fragment>*/}
                {/*    <button className="btn btn-danger" onClick={e => {onSubmit(e); toggleUpdate(!displayUpdate)}}>*/}
                {/*        <i className="fas fa-check-square"></i>Delete</button>*/}
                {/*</Fragment>*/}
                {/*}*/}
            </td>
        </tr>
    )
}

ArchivedRecordRow.propTypes = {
    auth: PropTypes.object.isRequired,
    restoreArchivedRecord: PropTypes.func.isRequired,
    deleteArchivedRecord: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
    auth: state.auth,
});

export default connect(mapStateToProps, {restoreArchivedRecord,deleteArchivedRecord})(withRouter(ArchivedRecordRow));
