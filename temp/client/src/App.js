import React, {Fragment, useEffect} from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Navbar from './components/layout/Navbar';
import Landing from './components/layout/Landing';
import Register from "./components/auth/Register";
import Login from "./components/auth/Login";
import Alert from "./components/layout/Alert";
import Dashboard from "./components/dashboard/Dashboard";
import CreateSheet from "./components/sheet/CreateSheet";
import Record from "./components/record/Record";
import PrivateRoute from "./routing/PrivateRoute";
import CreateActiveRecord from "./components/record/CreateActiveRecord";
import NotFound from "./components/layout/NotFound";
// redux
import {Provider} from 'react-redux';
import store from "./store";
import setAuthToken from "./utils/setAuthToken";
import {loadUser} from "./actions/auth";



if(localStorage.token) {
  setAuthToken(localStorage.token);
}

const App = () => {
  useEffect(() => {
    store.dispatch(loadUser())
  }, [])

  return (
      <Provider store={store}>
        <Router>
          <Fragment>
            <Navbar/>
            <Route exact path="/" component={Landing}/>
            <section className="container">
              <Alert/>
              <Switch>
                <Route exact path="/register" component={Register}/>
                <Route exact path="/login" component={Login}/>
                <PrivateRoute exact path="/dashboard" component={Dashboard}/>
                <PrivateRoute exact path="/create-sheet" component={CreateSheet}/>
                <PrivateRoute path="/sheet-record/:sid" component={Record}/>
                <PrivateRoute path="/create-record/:sid" component={CreateActiveRecord}/>
              </Switch>
            </section>
          </Fragment>
        </Router>
      </Provider>
  );
}

export default App;
