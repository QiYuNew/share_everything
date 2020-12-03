import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import {getCurrentUserSheet} from "../../actions/sheet";
import Spinner from "../layout/Spinner";
import sheet from "../../reducers/sheet";
import SheetRow from "../sheet/SheetRow";
import {cleanRecords} from "../../actions/record";

const Dashboard = ({cleanRecords, getCurrentUserSheet, auth: {user}, sheet:{sheets, loading}, history}) => {
    useEffect(() => {
        getCurrentUserSheet();
        cleanRecords();
    }, []);


    return ( loading && sheet === null ? <Spinner/> :
            <Fragment>
                <h1 className="large text-primary">Dashboard</h1>
                <p className="lead">
                    <i className="fas fa-user"></i>
                    {' '}Welcome {user&&user.name}
                    <Link to="/create-sheet">
                        <button className="btn btn-light-add">
                            <i className="far fa-plus-square"></i>
                        </button>
                    </Link>
                </p>

                {sheet !== null ?
                    <Fragment>
                        {/*<DashboardAction/>*/}
                        <table className="table">
                            <thead>
                            <tr>
                                <th width='80%'>Sheet</th>
                                <th width='30%'></th>
                            </tr>
                            </thead>
                            <tbody>
                                {sheets.map((sheet) => (
                                    <SheetRow key={sheet._id} sheet={sheet} />
                                ))}
                            </tbody>
                        </table>
                    </Fragment>
                    :
                    <Fragment>
                        {/*<p>No Sheet</p>*/}
                        {/*<Link to='/create-profile' className='btn btn-primary my-1'>*/}
                        {/*    Create Profile*/}
                        {/*</Link>*/}
                    </Fragment>}
            </Fragment>
    )

};

Dashboard.propTypes = {
    getCurrentUserSheet: PropTypes.func.isRequired,
    cleanRecords: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired,
    sheet: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    auth: state.auth,
    sheet: state.sheet
});

export default connect(mapStateToProps, {cleanRecords, getCurrentUserSheet})(withRouter(Dashboard));
