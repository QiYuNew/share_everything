const express = require('express');
const router = express.Router();
const auth = require('../../middleware/Auth');
const Sheet = require('../../models/Sheet');
const User = require('../../models/User');
const { check, validationResult } = require('express-validator');

// @route   get api/sheet
// @desc    find sheets by user id
// @access  private
router.get('/', auth, async (req, res) => {
    try {
        let sheets = await Sheet.find({user:req.user.id}).select('-activerecords -archiverecords');

        if(!sheets) {
            return res.status(400).json({msg: 'No sheets'})
        }

        await res.json(sheets)

    } catch (err) {
        console.log(err.message);
        res.status(500).send('server error')
    }
});

// @route   post api/sheet
// @desc    create a sheet
// @access  private
router.post('/', [auth, [
    check('sheetname', 'Sheet name is required').not().isEmpty()
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {sheetname} = req.body;

    const sheetFields = {};
    sheetFields.user = req.user.id;
    if(sheetname) sheetFields.sheetname = sheetname;

    try {
        // create
        const sheet = new Sheet(sheetFields);
        await sheet.save();
        return res.json(sheetFields)

    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }

});

// @route   put api/sheet/:sid
// @desc    update a sheet name
// @access  private
router.put('/:sid', [auth, [
    check('sheetname', 'Sheet name is required').not().isEmpty()
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {sheetname} = req.body;

    const sheetFields = {};
    // sheetFields.user = req.user.id;
    if(sheetname) sheetFields.sheetname = sheetname;

    try {

        let sheet = await Sheet.findOne({_id: req.params.sid}).select('-activerecords -archiverecords');

        if(!sheet) {
            return res.status(400).json({msg: 'Sheet is not found'})
        }

        // update
        if(sheet) {
            sheet = await Sheet.findOneAndUpdate(
                {_id: req.params.sid},
                {$set: sheetFields},
                {new: true}
            ).select('-activerecords -archiverecords');
            return res.json(sheet)
        }

    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
});

// @route   delete api/sheet/:sid
// @desc    delete a sheet
// @access  private
router.delete('/:sid', auth, async (req, res) => {
    try {
        const sheet = await Sheet.findById(req.params.sid);
        if(!sheet) {
            return res.status(404).json({msg: "Sheet is not found"})
        }
        // check the user
        if(sheet.user.toString() !== req.user.id) {
            return res.status(401).json({msg: 'User does not auth'})
        }
        await sheet.remove();
        let sheets = await Sheet.find({user:req.user.id}).select('-activerecords -archiverecords');

        if(!sheets) {
            return res.status(400).json({msg: 'No sheets'})
        }

        await res.json(sheets)
    } catch (err) {
        console.error(err.message);
        if(err.kind === 'ObjectId') {
            return res.status(404).json({msg: "Sheet is not found"})
        }
        res.status(500).send('server error')
    }
});

// @route   get api/sheet/:sid
// @desc    find sheets by sheet id
// @access  private
router.get('/:sid', auth, async (req, res) => {
    try {
        let sheets = await Sheet.findOne({_id:req.params.sid});

        if(!sheets) {
            return res.status(400).json({msg: 'No sheets'})
        }

        await res.json(sheets)

    } catch (err) {
        console.log(err.message);
        res.status(500).send('server error')
    }
});

// @route   post api/sheet/active/:sid
// @desc    create a activerecord for a sheet
// @access  private
router.post('/active/:sid', [auth, [
    check('position', 'Position name is required').not().isEmpty(),
    check('method', 'Method is required').not().isEmpty()
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {
        position,
        method,
        apppassword,
        note
    } = req.body;

    const activeRecordFields = {};
    if(position) activeRecordFields.position = position;
    if(method) activeRecordFields.method = method;
    if(apppassword) activeRecordFields.apppassword = apppassword;
    if(note) activeRecordFields.note = note;

    try {
        // find a sheet
        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(400).json({msg: 'Sheet is not found'})
        }

        // add a record
        sheet.activerecords.unshift(activeRecordFields);
        await sheet.save();
        await res.json(sheet.activerecords);

    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }

});

// @route   get api/sheet/active/:sid
// @desc    get all activerecords for a sheet
// @access  private
router.get('/active/:sid', [auth, [
    check('sortby', 'Sort method is required').not().isEmpty(),
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {sortby} = req.body;

    try {
        let sheet = await Sheet.findOne({_id: req.params.sid}).select('-archiverecords');

        if(!sheet) {
            return res.status(400).json({msg: 'Sheet is not found'})
        }

        if(sortby === "position") {
            let ar = sheet.toObject();
            ar.activerecords.sort(function(it1, it2)
                { return ('' + it1.position).localeCompare(it2.position); }
            );

            await res.json(ar);
        } else {
            let ar = sheet.toObject();
            ar.activerecords.sort(function(it1, it2)
                { return it2.submittime - it1.submittime; }
            );

            await res.json(ar);
        }

    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
});

// @route   delete api/sheet/active/:sid/:arid
// @desc    delete a activerecords from a sheet
// @access  private
router.delete('/active/:sid/:arid', auth, async (req, res) => {
    try {
        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(404).json({msg: "Sheet is not found"})
        }

        // check the user
        if(sheet.user.toString() !== req.user.id) {
            return res.status(401).json({msg: 'User does not auth'})
        }
        // get delete index
        const removeIdx = sheet.activerecords.map(it => it.id)
            .indexOf(req.params.arid);

        sheet.activerecords.splice(removeIdx, 1);
        await sheet.save();

        await res.json(sheet)
    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
});

// @route   put api/sheet/active/:sid/:arid
// @desc    update a activerecords from a sheet
// @access  private
router.put('/active/:sid/:arid', [auth, [
    check('position', 'Position name is required').not().isEmpty(),
    check('method', 'Method is required').not().isEmpty(),
    check('submittime', 'Submitted time is required').not().isEmpty()
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {
        position,
        method,
        apppassword,
        submittime,
        note
    } = req.body;

    const activeRecordFields = {};
    if(position) activeRecordFields.position = position;
    if(method) activeRecordFields.method = method;
    if(apppassword) activeRecordFields.apppassword = apppassword;
    if(submittime) activeRecordFields.submittime = submittime;
    if(note) activeRecordFields.note = note;

    try {

        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(400).json({msg: 'Sheet is not found'})
        }

        // delete
        // check the user
        if(sheet.user.toString() !== req.user.id) {
            return res.status(401).json({msg: 'User does not auth'})
        }
        // get delete index
        const removeIdx = sheet.activerecords.map(it => it.id)
            .indexOf(req.params.arid);

        await sheet.activerecords.splice(removeIdx, 1);
        await sheet.save();

        // create
        await sheet.activerecords.unshift(activeRecordFields);
        await sheet.save();
        await res.json(sheet);

    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
});

// @route   post api/sheet/archive/:sid
// @desc    create a archiverecord for a sheet
// @access  private
router.post('/archive/:sid', [auth, [
    check('position', 'Position name is required').not().isEmpty(),
    check('method', 'Method is required').not().isEmpty(),
    check('submittime', 'Submitted time is required').not().isEmpty(),
    check('result', 'Result is required').not().isEmpty()
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {
        position,
        method,
        submittime,
        apppassword,
        note,
        result
    } = req.body;

    const archiveRecordFields = {};
    if(position) archiveRecordFields.position = position;
    if(method) archiveRecordFields.method = method;
    if(submittime) archiveRecordFields.submittime = submittime;
    if(apppassword) archiveRecordFields.apppassword = apppassword;
    if(note) archiveRecordFields.note = note;
    if(result) archiveRecordFields.result = result;

    try {
        // find a sheet
        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(400).json({msg: 'Sheet is not found'})
        }

        // add a record
        sheet.archiverecords.unshift(archiveRecordFields);
        await sheet.save();
        await res.json(sheet.archiverecords);

    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }

});

// @route   get api/sheet/archive/:sid
// @desc    get all archiverecords for a sheet
// @access  private
router.get('/archive/:sid', [auth, [
    check('sortby', 'Sort method is required').not().isEmpty(),
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {sortby} = req.body;

    try {
        let sheet = await Sheet.findOne({_id: req.params.sid}).select('-activerecords');

        if(!sheet) {
            return res.status(400).json({msg: 'Sheet is not found'})
        }

        if(sortby === "position") {
            let ar = sheet.toObject();
            ar.archiverecords.sort(function(it1, it2)
                { return ('' + it1.position).localeCompare(it2.position); }
            );

            await res.json(ar);
        } else {
            let ar = sheet.toObject();
            ar.archiverecords.sort(function(it1, it2)
                { return it2.submittime - it1.submittime; }
            );

            await res.json(ar);
        }

    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
});

// @route   delete api/sheet/archive/:sid/:arid
// @desc    delete a archiverecord from a sheet
// @access  private
router.delete('/archive/:sid/:arid', auth, async (req, res) => {
    try {
        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(404).json({msg: "Sheet is not found"})
        }

        // check the user
        if(sheet.user.toString() !== req.user.id) {
            return res.status(401).json({msg: 'User does not auth'})
        }
        // get delete index
        const removeIdx = sheet.archiverecords.map(it => it.id)
            .indexOf(req.params.arid);

        sheet.archiverecords.splice(removeIdx, 1);
        await sheet.save();

        await res.json(sheet)
    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
});

// @route   archive api/sheet/archive/:sid/:arid
// @desc    archive a activerecord from a sheet
// @access  private
router.post('/active-archive/:sid/:arid', [auth, [
    check('result', 'Result is required').not().isEmpty(),
]], async (req, res) => {
    const errors = validationResult(req);
    if(!errors.isEmpty()) {
        return res.status(400).json({errors: errors.array()})
    }

    const {
        result
    } = req.body;

    try {
        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(404).json({msg: "Sheet is not found"})
        }

        // check the user
        if(sheet.user.toString() !== req.user.id) {
            return res.status(401).json({msg: 'User does not auth'})
        }
        // get delete index
        const removeIdx = sheet.activerecords.map(it => it.id)
            .indexOf(req.params.arid);

        const {
            position,
            method,
            submittime,
            apppassword,
            note,
        } = sheet.activerecords[removeIdx];

        const archiveRecordFields = {};
        if(position) archiveRecordFields.position = position;
        if(method) archiveRecordFields.method = method;
        if(submittime) archiveRecordFields.submittime = submittime;
        if(apppassword) archiveRecordFields.apppassword = apppassword;
        if(note) archiveRecordFields.note = note;
        archiveRecordFields.result = result;

        // delete from active
        sheet.activerecords.splice(removeIdx, 1);
        // add to archive
        sheet.archiverecords.unshift(archiveRecordFields);

        await sheet.save();
        await res.json(sheet)
    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
})

// @route   archive api/sheet/archive/:sid/:arid
// @desc    archive a activerecord from a sheet
// @access  private
router.get('/archive-active/:sid/:arid', auth, async (req, res) => {
    try {
        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(404).json({msg: "Sheet is not found"})
        }

        // check the user
        if(sheet.user.toString() !== req.user.id) {
            return res.status(401).json({msg: 'User does not auth'})
        }
        // get delete index
        const removeIdx = sheet.archiverecords.map(it => it.id)
            .indexOf(req.params.arid);

        const {
            position,
            method,
            submittime,
            apppassword,
            note,
        } = sheet.archiverecords[removeIdx];

        const activeRecordFields = {};
        if(position) activeRecordFields.position = position;
        if(method) activeRecordFields.method = method;
        if(submittime) activeRecordFields.submittime = submittime;
        if(apppassword) activeRecordFields.apppassword = apppassword;
        if(note) activeRecordFields.note = note;

        // delete from archive
        sheet.archiverecords.splice(removeIdx, 1);
        // add to active
        sheet.activerecords.unshift(activeRecordFields);

        await sheet.save();
        await res.json(sheet)
    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
})

// @route   post api/clip
// @desc    manage clipboard for a user
// @access  private
router.post('/clipboard/:sid', auth, async (req, res) => {

    const {
        c1,
        c2,
        c3,
        c4,
        c5,
        c6,
        c7,
        c8,
        c9,
    } = req.body;

    try {
        let sheet = await Sheet.findOne({_id: req.params.sid});

        if(!sheet) {
            return res.status(404).json({msg: "Sheet is not found"})
        }

        // check the user
        if(sheet.user.toString() !== req.user.id) {
            return res.status(401).json({msg: 'User does not auth'})
        }

        sheet.c1 = c1;
        sheet.c2 = c2;
        sheet.c3 = c3;
        sheet.c4 = c4;
        sheet.c5 = c5;
        sheet.c6 = c6;
        sheet.c7 = c7;
        sheet.c8 = c8;
        sheet.c9 = c9;

        await sheet.save();
        await res.json(sheet)
    } catch (err) {
        console.error(err.message);
        res.status(500).send('server error')
    }
})

module.exports = router;
