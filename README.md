# UIC Barcode

Implementation of UIC Barcode Railway Tickets as specified in the IRS 90918-9.

This repository contains three things:

- The ASN.1 modules defining the format of data used in UIC Ticket Barcodes,    
    in the folder `asn-specs`.
- A Java library implementing Ticket Layout Barcodes, Flexible Content Barcode,
    Small Structured Barcode, and DOSIPAS.
- Registers for national or company specific extensions to this standard, 
    in the folder `registers`.

The Maven repository for the Java library is available [here](https://github.com/orgs/UnionInternationalCheminsdeFer/packages?repo_name=UIC-barcode).

An interactive rendering of the latest Rail Ticket Data v3 ASN.1 may be viewed [here](https://asn1.bt4pt.eu/oid/iso/identified-organization/dod/internet/private/enterprise/uic/fcb/modules/rtd/v3/6/).

OIDs for the ASN.1 modules are documented in `oid.md`, and defined in ASN.1 form
in `asn-specs/oids.asn`.