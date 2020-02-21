package com.stacko.groovy.xml

import groovy.json.JsonSlurper;

/**
 * JSON to XML script using JsonSlurper
 * 
 * 				{
				  "test" : { 
				    "a" : "A", 
				    "b" : "B"
				  }
				}
				
				To
				
				<test>
				  <message>
				    <a>A</a>
				    <b>B</b>
				  </message>
				  <booleanMessage>
				    <a>true</a>
				    <b>true</b>
				  </booleanMessage>
				</test>
 *				
 */


	def jsonSlurper = new JsonSlurper()
	
	def json = jsonSlurper.parseText('''
				    {
				      "test" : { 
				        "a" : "A", 
				        "b" : "B"
				       }
				    }
				''')

	def sw = new StringWriter()
	def xml = new groovy.xml.MarkupBuilder(sw)
	
	json.each {
		prop ->
		xml."$prop.key" {
			message {
				prop.value.each {
					nestedProp ->
					"$nestedProp.key"(nestedProp.value)
				}
			}
			booleanMessage {
				prop.value.each  {
					p ->
					"$p.key"('true')
				}
			}
		}
	}
	println sw.toString()
