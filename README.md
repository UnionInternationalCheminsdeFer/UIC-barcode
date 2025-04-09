# UIC-barcode
Implementation of FCB barcode for rail tickets as specified in the IRS 90918-9.

The implementation provides a java API for the ticket an encoding / decoding functions to convert 
the ticket to and from the ASN.1/UPER encoded byte array specified in IRS 90918-9 for the FCB (flexible content barcode).

Covered barcode types:

  - Static barcode (Fixed length structure)
     - TLB (Ticket Layout Barcode content)
     - FCB (Flexible Content Barcode) version 1
     - FCB (Flexible Content Barcode) version 2 (not used by railways)
     - FBC (Flexible Content Barcode) version 3
  - Dynamic barcode (DOSIPAS)
     - FCB (Flexible Content Barcode) version 1
     - FCB (Flexible Content Barcode) version 2 (not used by railways)
     - FBC (Flexible Content Barcode) version 3
  - SSB (Small Structured Barcode)
    

Documentation is available in the wiki: https://github.com/UnionInternationalCheminsdeFer/UIC-barcode/wiki

The maven repo is available at: https://github.com/orgs/UnionInternationalCheminsdeFer/packages?repo_name=UIC-barcode
