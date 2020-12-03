import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {Route, Redirect, Component} from "react-router-dom";

const PrivateRoute = ({component: Component, auth: {isAuthenticated, loading}, ...rest}) => (
    <Route {...rest} render={
        props => !isAuthenticated && !loading ? (<Redirect to='/login'/>) : (<Component {...props}/>)
    }/>
);

PrivateRoute.protoTypes = {
    auth: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    auth: state.auth
});

export default connect(mapStateToProps)(PrivateRoute);
