# SMI Parser

## Java based SMI parser

### Overview
The Structure of Management Information (SMI), an adapted subset of ASN.1, operates in Simple Network Management Protocol (SNMP) to define sets ("modules") of related managed objects in a Management information base (MIB).

SMI subdivides into three parts: module definitions, object definitions, and notification definitions.

* Module definitions are used when describing information modules. An ASN .1 macro, MODULE-IDENTITY, is used to concisely convey the semantics of an information module.
* Object definitions describe managed objects. An ASN.1 macro, OBJECT-TYPE, is used to concisely convey the syntax and semantics of a managed object.
* Notification definitions (aka "traps") are used when describing unsolicited transmissions of management information. An ASN.1 macro, NOTIFICATION-TYPE, concisely conveys the syntax and semantics of a notification.
 
### Project scope
The core of the distribution is a library that allows management applications to access SMI MIB module definitions a toolkit for parsing, validating and handling MIB files. The library uses [ANTLR](http://www.antlr.org/) to generate java pojos from a grammar file. For futher information please refer to its [original project] (https://code.google.com/p/smiparser/) and to its referral project [libsmi](https://www.ibr.cs.tu-bs.de/projects/libsmi/).

### Technologies
Current project uses following core technologies:
* Java
* Maven
* AntLR

### Usage
Clone project and compile.
