package com.db

import groovy.sql.Sql
 
db = Sql.newInstance(
            'jdbc:oracle:thin:@ldap://va1oid01.corp.agp.ads/FAC2D,cn=OracleContext ldap://va1oid02.corp.agp.ads/FAC2D,cn=OracleContext',
            'APP_CCTR', 'cctr', 'oracle.jdbc.driver.OracleDriver')

def sql = 'select s.SBSB_ID, s.SBSB_HIRE_DT, s.GRGR_CK, m.MEME_CK, m.SBSB_CK, m.MEME_TITLE, m.MEME_LAST_NAME, m.MEME_MID_INIT, m.MEME_FIRST_NAME, m.MEME_SEX, m.MEME_BIRTH_DT, m.MEME_MARITAL_STATUS, m.MEME_SSN, m.MEME_MEDCD_NO, m.MEME_HICN, m.meme_orig_eff_dt, m.SBAD_TYPE_HOME, m.SBAD_TYPE_MAIL '+
		 'from CMC_MEME_MEMBER m inner join CMC_SBSB_SUBSC s on m.SBSB_CK = s.SBSB_CK '+
		 'where s.SBSB_ID = \'720159417\''

def list = db.rows(sql,0,20);

println list