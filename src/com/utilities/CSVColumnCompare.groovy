package com.utilities

def allData = new File( 'C:/work/AIM-MSK.px.csv' )


//def diff = allData.text.tokenize( '\n' ) - lizData.text.tokenize( '\n' )

allData.text.tokenize("\n").each {  println it }

