# UIC Barcode

Implementation of UIC Barcode Railway Tickets as specified in the IRS 90918-9.

This repository contains three things:

- The ASN.1 modules defining the format of data used in UIC Ticket Barcodes,    
    in the folder `asn-specs`.
- A Java library implementing Ticket Layout Barcodes, Flexible Content Barcode,
    Small Structured Barcode, and DOSIPAS.
- Registers for national or company specific extensions to this standard, 
    in the folder `registers`.

The Maven repository for the Java library is available at https://github.com/orgs/UnionInternationalCheminsdeFer/packages?repo_name=UIC-barcode

OIDs for the ASN.1 modules are documented in `oid.md`, and defined in ASN.1 form
in `asn-specs/oids.asn`.

## Draft Multi Modal Ticket Data v1 (MMTDv1)

The `mmtdv1-draft` branch contains a draft version of the future Multi Modal 
Ticket Data v1 ASN.1 modules. These ASN.1 modules are *not* to be used in 
production yet, they are provided to facilitate experimentation and public comment.
