const express = require('express')

var app = express()

app.use('/', function(req, res){
    res.send('Hola mundo')
})

app.listen( 3000 )