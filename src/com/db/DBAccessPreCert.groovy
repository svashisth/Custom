package com.db

import groovy.sql.Sql

db = Sql.newInstance(
		'jdbc:jtds:sqlserver://DBD_SOA.corp.agp.ads:40021/WEBAUTH;sendStringParametersAsUnicode=false;ssl=request',
		'APP_WEBAUTH', '9!$B&VJuJ@rQ8', 'net.sourceforge.jtds.jdbc.Driver')

def sql = 'SELECT distinct grgr_id, cspi_id, cscs_id FROM PreCertGPLink (nolock)'

def list = db.rows(sql);

println list.size()