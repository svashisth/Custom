package com.db

import groovy.sql.Sql

def qaDB = Sql.newInstance('jdbc:oracle:thin:@ldap://va1oid01.corp.agp.ads/FAC7Q,cn=OracleContext ldap://va1oid02.corp.agp.ads/FAC7Q,cn=OracleContext',
		'APP_CCTR', 'cctr2p9q', 'oracle.jdbc.driver.OracleDriver')
println '7Q connected'
def devDB = Sql.newInstance('jdbc:oracle:thin:@ldap://va1oid01.corp.agp.ads/FAC2D,cn=OracleContext ldap://va1oid02.corp.agp.ads/FAC2D,cn=OracleContext',
		'APP_CCTR', 'cctr', 'oracle.jdbc.driver.OracleDriver')
println '2D connected'
def table1 = qaDB.dataSet('AGP.MER_CMS_TAT')
println 'Got table1 ' + table1
def table2 = devDB.dataSet('AGP.MER_CMS_TAT')
println 'Got table2 ' + table2

//table1.rows("SELECT * FROM AGP.MER_CMS_TAT tat WHERE tat.SRC_SYSTEM_CD = 'ACMP' and tat.MEME_ORAL_DT IS NOT NULL ORDER bY tat.CREATED_DT desc").findAll().each { println it }

table1.rows("SELECT * FROM AGP.MER_CMS_TAT tat WHERE tat.SRC_SYSTEM_CD = 'ACMP' and tat.MEME_ORAL_DT IS NOT NULL ORDER bY tat.CREATED_DT desc").findAll().each{
	it.CMS_TAT_ID = it.CMS_TAT_ID + '000'
	println 'Inserting ' + it
	table2.add(it)
}

println 'Completed'
qaDB.close()
devDB.close()