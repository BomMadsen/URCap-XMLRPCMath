#!/usr/bin/env python

import xmlrpclib
from SimpleXMLRPCServer import SimpleXMLRPCServer
import math
import string

def add(a,b):
	print "Received A: " , a
	print "Received B: " , b
	return a+b

def sub(a,b):
	print "Received A: " , a
	print "Received B: " , b
	return a-b
	
def strIndex(str,n):
	print "Received string: ", str
	return str[n]
	
def strCon(stringA,stringB):
	print "String 1: ", stringA
	print "String 2: ", stringB
	return "%s%s" %(stringA,stringB)

def negBool(boo):
	print "Received the boolean: ", boo 
	return not (boo)

#server = SimpleXMLRPCServer(("", 33000), allow_none=True)
server = SimpleXMLRPCServer(("", 33000), allow_none=True)
print "Listening on port 33000..."
server.register_function(add, "add")
server.register_function(sub, "sub")
server.register_function(strIndex, "strIndex")
server.register_function(strCon, "strCon")
server.register_function(negBool, "negBool")
server.serve_forever()