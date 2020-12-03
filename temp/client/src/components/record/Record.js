import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import Moment from "react-moment";
import Spinner from "../layout/Spinner";
import record from "../../reducers/record";
import {getActiveRecordsBySheetId} from "../../actions/record";
import sheet from "../../reducers/sheet";
import {cleanRecords} from "../../actions/record";
import ArchivedRecordRow from "./ArchivedRecordRow";
import ActiveRecordRow from "./ActiveRecordRow";

const initialState = {
    _id: '',
    sheetname: '',
    activerecords: [],
    archiverecords: []
}

const Record = ({cleanRecords, getActiveRecordsBySheetId, auth: {user}, match, record: {records, loading}, history}) => {
    const [formData, setFormData] = useState(initialState);

    useEffect(() => {
        cleanRecords();
        getActiveRecordsBySheetId(match.params.sid)
    }, []);

    const [displayArchive, toggleArchive] = useState(false);
    const [sortByDate, toggleSort] = useState(false);
    const [sortArchivedByDate, toggleAchiveSort] = useState(false);

    return (loading && record === null ? <Spinner/> :

        <Fragment>
            <Link to="/dashboard">
                <button className="btn btn-primary">
                    <i className="fas fa-chevron-left"></i>{'  '}Go back
                </button>
            </Link>

            {!displayArchive &&
                <Fragment>
                    <h2 className="my-2">
                        Active Records
                    </h2>
                    <button className="btn btn-primary" onClick={() => toggleArchive(!displayArchive)}>
                        <i className="fas fa-exchange-alt"></i>{'  '}Archived Records</button>
                    {!sortByDate &&
                    <button className="btn btn-light" onClick={() => toggleSort(!sortByDate)}>
                        <i className="fas fa-sort-numeric-down-alt"></i>{'  '}Submitted Date</button>
                    }
                    {sortByDate &&
                    <button className="btn btn-light" onClick={() => toggleSort(!sortByDate)}>
                        <i className="fas fa-sort-alpha-down"></i>{'  '}Position</button>
                    }
                    <Link to={"/create-record/".concat(match.params.sid)}>
                        <button className="btn btn-light-add">
                            <i className="far fa-plus-square"></i>
                        </button>
                    </Link>
                    {records && Array.isArray(records.activerecords) && records.activerecords.length ?
                        <Fragment>
                            <table className="table">
                                <thead>
                                <tr>
                                    <th width='20%'>Position</th>
                                    <th className="hide-sm" width='10%'>Method</th>
                                    <th className="hide-sm" width='15%'>Submitted Date</th>
                                    <th className="hide-sm" width='10%'>Password</th>
                                    <th className="hide-sm" width='20%'>Note</th>
                                    <th width='25%'/>
                                </tr>
                                </thead>
                                <tbody>
                                {!sortByDate && [...records.activerecords].sort(function(it1, it2)
                                    { return ('' + it1.position).localeCompare(it2.position); }
                                    ).map((rec) => (
                                    <ActiveRecordRow key={rec._id} rec={rec} sid={records._id}/>
                                ))}
                                {sortByDate && [...records.activerecords].sort(function(it1, it2)
                                    { return new Date(it2.submittime) - new Date(it1.submittime); }
                                    ).map((rec) => (
                                    <ActiveRecordRow key={rec._id} rec={rec} sid={records._id}/>
                                ))}
                                </tbody>
                            </table>
                        </Fragment>
                        :
                        <Fragment>
                            <div>
                                <h3>No Records</h3>
                            </div>
                        </Fragment>}
                </Fragment>
            }

            {displayArchive &&
            <Fragment>
                <h2 className="my-2">
                    Archived Records
                </h2>
                <button className="btn btn-primary" onClick={() => toggleArchive(!displayArchive)}>
                    <i className="fas fa-exchange-alt"></i>{'  '}Active Records</button>
                {!sortArchivedByDate &&
                <button className="btn btn-light" onClick={() => toggleAchiveSort(!sortArchivedByDate)}>
                    <i className="fas fa-sort-numeric-down-alt"></i>{'  '}Submitted Date</button>
                }
                {sortArchivedByDate &&
                <button className="btn btn-light" onClick={() => toggleAchiveSort(!sortArchivedByDate)}>
                    <i className="fas fa-sort-alpha-down"></i>{'  '}Position</button>
                }

                {records && Array.isArray(records.archiverecords) && records.archiverecords.length ?
                    <Fragment>
                        <table className="table">
                            <thead>
                            <tr>
                                <th width='20%'>Position</th>
                                <th className="hide-sm" width='10%'>Method</th>
                                <th className="hide-sm" width='15%'>Submitted Date</th>
                                <th className="hide-sm" width='15%'>Archived Date</th>
                                <th className="hide-sm" width='10%'>Password</th>
                                <th className="hide-sm" width='10%'>Note</th>
                                <th className="hide-sm" width='5%'>Result</th>
                                <th width='15%'/>
                            </tr>
                            </thead>
                            <tbody>
                            {!sortArchivedByDate && [...records.archiverecords].sort(function(it1, it2)
                                { return ('' + it1.position).localeCompare(it2.position); }
                            ).map((rec) => (
                                <ArchivedRecordRow key={rec._id} rec={rec} sid={records._id}/>
                            ))}
                            {sortArchivedByDate && [...records.archiverecords].sort(function(it1, it2)
                                { return new Date(it2.submittime) - new Date(it1.submittime); }
                            ).map((rec) => (
                                <ArchivedRecordRow key={rec._id} rec={rec} sid={records._id}/>
                            ))}
                            </tbody>
                        </table>
                    </Fragment>
                    :
                    <Fragment>
                        <div>
                            <h3>No Records</h3>
                        </div>
                    </Fragment>}
            </Fragment>
            }
            <div className="myscrolltop" onClick={() => window.scrollTo({top: 0, behavior: 'smooth'})}>
                <i className="fas fa-chevron-circle-up"></i>
            </div>
        </Fragment>

    )
};

Record.propTypes = {
    auth: PropTypes.object.isRequired,
    getActiveRecordsBySheetId: PropTypes.func.isRequired,
    cleanRecords: PropTypes.func.isRequired,
    record: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    auth: state.auth,
    record: state.record
});

export default connect(mapStateToProps, {cleanRecords, getActiveRecordsBySheetId})(withRouter(Record));
