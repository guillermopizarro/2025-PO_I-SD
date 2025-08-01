const express = require('express')
const client = require('./index');

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.get('/', (req, res) => {
    client.getUsers({}, (error, data) => {
        if (!error) {
            console.log(data)
            res.send( { msg: 'Users list.', user: data } )
        } else {
            console.error('[error]')
            res.status(500).send( {mensaje: '[error]'} )
        }
    })
})

app.post('/', (req, res) => {
    const user = req.body
    client.addUser(user, (error, data) => {
        if (!error) {
            console.log(data);
            res.send({ msg: 'Data added successfully.', user: data });
        }else {
            console.error('[error]')
            res.status(500).send( {mensaje: '[error]'} )
        }
    })
})

app.listen(5555, () => {
    console.log('Server started on 5555.');
});