# UIC Flexible Barcode Working Group Object Identifier Registry

This documents contains the register of Object Identifiers (OIDs) assigned by
the UIC's Flexible Barcode Working Group for its work and its standards.
Its counterpart in ASN.1 format is `asn-specs/oids.asn`.

All assignments are made under the `1.3.6.1.4.1.17218.1` node, that is:

```asn1
{ iso(1) identified-organization(3) dod(6) internet(1) private(4) enterprise(1) uic(17218) fcb(1) }
```

An identifier, once added to this registry, can never be removed.
It may only be marked as deprecated.

- `0`: ASN.1 Modules
  - `0`: UIC FCB Object Identifiers
  - `1`: Header (DOSIPAS)
    - `1`: Header v1.0:
      - `0`: : UIC Barcode Header - version 1.0.0
    - `2`: Header v2.0:
      - `0`: : UIC Barcode Header - version 2.0.0
      - `1`: : UIC Barcode Header - version 2.0.1
  - `2`: Rail Ticket Data
    - `1`: RTDv1.3:
      - `0`: UIC Rail Ticket Data - version 1.3.0
      - `1`: UIC Rail Ticket Data - version 1.3.1
      - `3`: UIC Rail Ticket Data - version 1.3.3
      - `4`: UIC Rail Ticket Data - version 1.3.4
      - `5`: UIC Rail Ticket Data - version 1.3.5
    - `2`: RTDv2.0:
      - `0`: UIC Rail Ticket Data - version 2.0.0
      - `1`: UIC Rail Ticket Data - version 2.0.1
      - `2`: UIC Rail Ticket Data - version 2.0.2
      - `3`: UIC Rail Ticket Data - version 2.0.3
    - `3`: RTDv3.0:
      - `0`: UIC Rail Ticket Data - version 3.0.0
      - `1`: UIC Rail Ticket Data - version 3.0.1
      - `2`: UIC Rail Ticket Data - version 3.0.2
      - `3`: UIC Rail Ticket Data - version 3.0.3
      - `4`: UIC Rail Ticket Data - version 3.0.4
      - `5`: UIC Rail Ticket Data - version 3.0.5
    - `4`: RTDv4.0:
      - `0`: Draft RTDv4, will be replaced by initial release
  - `3`: Dynamic Content Data
    - `1`: DCDv1.0:
        - `0`: FCB Dynamic Content Data - version 1.0.0
        - `1`: FCB Dynamic Content Data - version 1.0.1
        - `2`: FCB Dynamic Content Data - version 1.0.2
        - `3`: FCB Dynamic Content Data - version 1.0.3
        - `4`: FCB Dynamic Content Data - version 1.0.4
        - `5`: FCB Dynamic Content Data - version 1.0.5
- `1`: PKI Related OIDs, primarily X.509 extensions
  - Nodes under this tree to be defined