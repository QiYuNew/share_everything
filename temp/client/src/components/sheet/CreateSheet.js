import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import {createSheet} from "../../actions/sheet";

const CreateSheet = ({auth, createSheet, history}) => {
    const [formData, setFormData] = useState({
        sheetname: ''
    });

    const {sheetname} = formData;

    const onChange = e =>
        setFormData({ ...formData, [e.target.name]: e.target.value });

    return (
        <Fragment>
            <h1 className="large text-primary">
                Create a Sheet
            </h1>
            <Link to="/dashboard">
                <button className="btn btn-primary">
                    <i className="fas fa-chevron-left"></i>{'  '}Go back
                </button>
            </Link>

            <form className="form" onSubmit={(e) => {
                e.preventDefault();
                createSheet(formData, history)
            }}>
                <small>* = required field</small>
                <div className="form-group">
                    <input
                        type="text"
                        placeholder="* Sheet Name"
                        name="sheetname"
                        value={sheetname}
                        onChange={e => onChange(e)}
                        required
                    />
                </div>
                <input type="submit" className="btn btn-primary my-1" value="Submit"/>
            </form>
        </Fragment>
    )

};

CreateSheet.propTypes = {
    createSheet: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    auth: state.auth,
});

export default connect(mapStateToProps, {createSheet})(withRouter(CreateSheet));
