import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, Redirect, withRouter} from "react-router-dom";
import {connect} from 'react-redux'
import {updateSheetName, deleteSheet} from "../../actions/sheet";

const initialState = {
    sheetname: ''
}

const SheetRow = ({updateSheetName, deleteSheet, auth, sheet:{_id, sheetname, user, loading}, history}) => {

    const [formData, setFormData] = useState(initialState);

    useEffect(() => {
        const prev = {...initialState};
        prev.sheetname = sheetname;
        setFormData(prev);
    }, [sheetname])

    const [displayUpdate, toggleUpdate] = useState(false);

    const onChange = e =>
        setFormData({ [e.target.name]: e.target.value });

    const onSubmit = e => {
        e.preventDefault();
        updateSheetName(_id, formData, history)
    }

    return (
        <tr>
            <td>
                {!displayUpdate &&
                    <Link to={`/sheet-record/${_id}`}>
                        {formData.sheetname}
                    </Link>
                }
                {displayUpdate &&
                <Fragment>
                    <input className="form form-my" type="text"
                           name="sheetname" value={formData.sheetname} onChange={e => onChange(e)} />
                </Fragment>
                }
            </td>

            <td>
                {!displayUpdate &&
                <Fragment>
                    <button className="btn btn-primary" onClick={() => toggleUpdate(!displayUpdate)}>
                        <i className="fas fa-edit"></i></button>
                    <button className="btn btn-danger" onClick={() => deleteSheet(_id, history)}>
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

SheetRow.propTypes = {
    auth: PropTypes.object.isRequired,
    updateSheetName: PropTypes.func.isRequired,
    deleteSheet: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
    auth: state.auth,
});

export default connect(mapStateToProps, {updateSheetName, deleteSheet})(withRouter(SheetRow));
