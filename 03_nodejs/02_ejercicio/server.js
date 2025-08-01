const express = require('express')
const body_parser = require('body-parser')

const router = express.Router()

var app = express()

app.use( body_parser.json() )
app.use( body_parser.urlencoded({extended:false}) )
app.use( router )

router.get('/', function(req, res) {
    console.log( req.query )
    console.log( req.body )
    res.send( 'GET - Hola mundo.' )
})

router.post('/', function(req, res) {
    console.log( req.body )
    res.send( {mensaje: 'POST - Hola mundo.'} )
})


app.listen( 3000 )