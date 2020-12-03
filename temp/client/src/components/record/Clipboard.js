import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import Moment from "react-moment";
import {CopyToClipboard} from "react-copy-to-clipboard";
import {saveClipboard} from "../../actions/record";

const initialState = {
    c1: '',
    c2:'',
    c3:'',
    c4:'',
    c5:'',
    c6: '',
    c7:'',
    c8:'',
    c9:'',
};

const Clipboard = ({saveClipboard, auth, c1, c2, c3, c4, c5, c6, c7, c8, c9, sid, history}) => {

    const [clipboardData, setClipboardData] = useState(initialState);

    useEffect(() => {
        const prev = {...initialState};
        prev.c1 = c1;
        prev.c2 = c2;
        prev.c3 = c3;
        prev.c4 = c4;
        prev.c5 = c5;
        prev.c6 = c6;
        prev.c7 = c7;
        prev.c8 = c8;
        prev.c9 = c9;
        setClipboardData(prev);
    }, [])

    const onChangeClip = e =>
        setClipboardData({ ...clipboardData, [e.target.name]: e.target.value });

    return (
        <form className="form" onSubmit={(e) => {
            e.preventDefault();
            saveClipboard(sid, clipboardData, history);
        }}>
            <div className="row">
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c1}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c1"
                            value={clipboardData.c1} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c2}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c2"
                            value={clipboardData.c2} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c3}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c3"
                            value={clipboardData.c3} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c4}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c4"
                            value={clipboardData.c4} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c5}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c5"
                            value={clipboardData.c5} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c6}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c6"
                            value={clipboardData.c6} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c7}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c7"
                            value={clipboardData.c7} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c8}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c8"
                            value={clipboardData.c8} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
                <div className="column">
                    <div className="form-group">
                        <CopyToClipboard text={clipboardData.c9}>
                            <button className="btn btn-light"><i className="far fa-copy"></i></button>
                        </CopyToClipboard>
                        <textarea
                            className="mytextarea"
                            name="c9"
                            value={clipboardData.c9} onChange={e => onChangeClip(e)}
                        />
                    </div>
                </div>
            </div>

            {/*<input type="submit" className="btn btn-primary my-1" value="Save"/>*/}
        </form>
    )
};

Clipboard.propTypes = {
    auth: PropTypes.object.isRequired,
    saveClipboard: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
    auth: state.auth,
});

export default connect(mapStateToProps, {saveClipboard})(withRouter(Clipboard));
