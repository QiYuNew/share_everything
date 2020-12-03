const express = require('express');
const connectDB = require('./config/db');
const path = require('path');
// npm run server
const app = express();
connectDB();

app.use(express.json({extended:false}));

// app.get('/', (req, res) => res.send('api is running'));

app.use('/api/user', require('./routes/api/user'));
app.use('/api/auth', require('./routes/api/auth'));
app.use('/api/sheet', require('./routes/api/sheet'));

// Serve static assets in production
if (process.env.NODE_ENV === 'production') {
    // Set static folder
    app.use(express.static('client/build'));

    app.get('*', (req, res) => {
        res.sendFile(path.resolve(__dirname, 'client', 'build', 'index.html'));
    });
}

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => console.log(`server on ${PORT}`));
