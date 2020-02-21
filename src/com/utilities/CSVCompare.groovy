package com.utilities

def allData = new File( 'C:/work/ICR Integration/PreCertUMSGRoute_export.csv' )
def lizData = new File( 'C:/work/ICR Integration/FromLiz.csv' )
def diff = allData.text.tokenize( '\n' ) - lizData.text.tokenize( '\n' )

print diff

diff = lizData.text.tokenize( '\n' ) - allData.text.tokenize( '\n' )
println "-------------------------------------------------------------------------"
print diff