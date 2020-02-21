package com.utilities

import javax.mail.*
import javax.mail.search.*
import java.util.Properties
 
def host = "imap.gmail.com"
def username = "sunilvashisth@gmail.com"
def password = "Jul123!@#"
Properties props = new Properties()
props.setProperty("mail.store.protocol", "imap")
props.setProperty("mail.imap.host", host)
props.setProperty("mail.imap.port", "993")
props.setProperty("username", username)
props.setProperty("password", password)

def session = Session.getDefaultInstance(props, null)
def store = session.getStore("imap")
def inbox
 
try {
  store.connect(host, username, password)
  inbox = openFolder(store, "INBOX")
  def messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.RECENT), false))
  messages.each { msg ->
    println("${msg.subject} ${msg.sender}")
    msg.setFlag(Flags.Flag.SEEN, true)
  }
} finally {
  if(inbox) {
    inbox.close(true)
  }
  store.close()
}