const mongoose = require('mongoose');

const sheetSchema = new mongoose.Schema({
    user: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'user'
    },
    sheetname: {
        type: String,
        required: true
    },
    activerecords: [
        {
            position: {
                type: String,
                required: true
            },
            method: {
                type: String,
                required: true
            },
            submittime: {
                type: Date,
                default: Date.now
            },
            apppassword: {
                type: String
            },
            note: {
                type: String,
            }
        }
    ],
    archiverecords: [
        {
            position: {
                type: String,
                required: true
            },
            method: {
                type: String,
                required: true
            },
            submittime: {
                type: Date,
                required: true
            },
            apppassword: {
                type: String
            },
            archivetime: {
                type: Date,
                default: Date.now
            },
            note: {
                type: String,
            },
            result: {
                type: Boolean,
                required: true
            }
        }
    ],
    c1: {
        type: String
    },
    c2: {
        type: String
    },
    c3: {
        type: String
    },
    c4: {
        type: String
    },
    c5: {
        type: String
    },
    c6: {
        type: String
    },
    c7: {
        type: String
    },
    c8: {
        type: String
    },
    c9: {
        type: String
    }

});

module.exports = Sheet = mongoose.model('sheet', sheetSchema)
