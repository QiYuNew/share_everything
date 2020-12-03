import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import {createActiveRecord, saveClipboard} from "../../actions/record";
import {CopyToClipboard} from "react-copy-to-clipboard";
import {getActiveRecordsBySheetId} from "../../actions/record";
import {cleanRecords} from "../../actions/record";
import record from "../../reducers/record";
import Spinner from "../layout/Spinner";
import Clipboard from "./Clipboard";

const CreateActiveRecord = ({
                                cleanRecords,
                                getActiveRecordsBySheetId,
                                createActiveRecord,
                                saveClipboard,
                                auth:{user},
                                record: {records, loading},
                                match,
                                history}) => {
    useEffect(() => {
        getActiveRecordsBySheetId(match.params.sid)
    }, []);


    const [formData, setFormData] = useState({
        position: '',
        method:'Job Portal',
        submittime: Date.now(),
        apppassword:'',
        note:'',
    });

    const {
        position,
        method,
        submittime,
        apppassword,
        note
    } = formData;

    const onChange = e =>
        setFormData({ ...formData, [e.target.name]: e.target.value });

    const prefix = "/sheet-record/";
    const returnPath = prefix.concat(match.params.sid);

    return (loading && record === null ? <Spinner/> :
            <Fragment>
                <h1 className="large text-primary">
                    Create a Record
                </h1>
                <Link to={returnPath}>
                    <button className="btn btn-primary">
                        <i className="fas fa-chevron-left"></i>{'  '}Go back
                    </button>
                </Link>

                <form className="form" onSubmit={(e) => {
                    e.preventDefault();
                    createActiveRecord(match.params.sid, formData, history)
                }}>
                    <small>*Required Field, Default: Job Portal / Today</small>
                    <div className="form-group">
                        <input
                            type="text"
                            placeholder="* Company Name + Position Title"
                            name="position"
                            value={position}
                            onChange={e => onChange(e)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <select className="form form-my"
                                name="method"
                                value={method}
                                onChange={e => onChange(e)}
                                required>
                            <option value="0">* Select Apply Method</option>
                            <option value="Job Portal">Job Portal</option>
                            <option value="Referral">Referral</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <input
                            type="datetime-local"
                            name="submittime"
                            value={submittime}
                            onChange={e => onChange(e)}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="text"
                            placeholder="Application password"
                            name="apppassword"
                            value={apppassword}
                            onChange={e => onChange(e)}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            type="text"
                            placeholder="Note"
                            name="note"
                            value={note}
                            onChange={e => onChange(e)}
                        />
                    </div>
                    <input type="submit" className="btn btn-primary my-1" value="Submit"/>
                </form>

                <h6 className="medium text-primary">
                    Clipboard
                </h6>

                {records  ?
                    <Clipboard
                        c1={records.c1}
                        c2={records.c2}
                        c3={records.c3}
                        c4={records.c4}
                        c5={records.c5}
                        c6={records.c6}
                        c7={records.c7}
                        c8={records.c8}
                        c9={records.c9}
                        sid = {records._id}
                    />
                    :
                    <Fragment>
                        <Spinner/>
                    </Fragment>}
            </Fragment>
    )

};

CreateActiveRecord.propTypes = {
    createActiveRecord: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired,
    record: PropTypes.object.isRequired,
    getActiveRecordsBySheetId: PropTypes.func.isRequired,
    cleanRecords: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
    auth: state.auth,
    record: state.record
});

export default connect(mapStateToProps, {createActiveRecord, saveClipboard, cleanRecords, getActiveRecordsBySheetId})(withRouter(CreateActiveRecord));
