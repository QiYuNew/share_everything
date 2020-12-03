const express = require('express');
const router = express.Router();
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const config = require('config');
const User = require('../../models/User');
const { check, validationResult } = require('express-validator');

// @route   post api/user
// @desc    register a user
// @access  public
router.post('/', [
        check('name', 'Name is required').not().isEmpty(),
        check('email', 'Please enter a valid email').isEmail(),
        check('password', 'Password must be at least 6 characters in length').isLength({min:6})
    ],
    async (req, res) => {
        const errors = validationResult(req);
        if(!errors.isEmpty()) {
            return res.status(400).json({errors: errors.array()})
        }

        const {name, password, email} = req.body;

        try {
            let user = await User.findOne({email: email});
            if(user) {
                return res.status(400).json({errors: [ {msg: 'This email has already existed'} ]})
            }

            user = new User({
                name,
                email,
                password
            })

            const salt = await bcrypt.genSalt(10);
            user.password = await bcrypt.hash(password, salt);
            await user.save();

            const payload= {
                user: {
                    id: user.id
                }
            };

            jwt.sign(
                payload,
                config.get('jwtSecret'),
                {
                    expiresIn: 360000
                },
                (err, token) => {
                    if(err) throw err;
                    res.json({ token })
                }
            );


        } catch (err) {
            console.error(err.message);
            res.status(500).send('error');
        }


    });


module.exports = router;
