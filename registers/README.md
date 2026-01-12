# UIC Extension Registry

This directory contains the registration tables for national, company proprietary,
private, and other extensions to UIC barcoded ticketing standards.
This serves as a central repository of extension identifiers, and provides
information on where to find more information on specific identifiers.

The keywords "MUST", "MUST NOT", "REQUIRED", "SHALL", "SHALL NOT", "SHOULD", 
"SHOULD NOT", "RECOMMENDED", "NOT RECOMMENDED", "MAY", and "OPTIONAL" in this 
document are to be interpreted as described in 
[BCP 14](https://www.rfc-editor.org/info/bcp14) when, and only when, they appear
in all capitals, as shown here.

## General Format

Extension identifiers can one of 4 possible formats:

- National: `"+" + [2-letter uppercase ISO 3166 country code] + [addon, chosen by the national standards body of that country]`
- Company specific: `"_" + [RICS or ERA company code] + [addon, chosen by this company]`
- Compressed company specific: `"!I" + [addon, chosen by the issuer]`, `"!C" + [addon, chosen by the carrier]`, and `"!P" + [addon, chosen by the product owner]`
- Private: `"*" + [addon]`
- Other: any other value as registered in this repository

### National identifiers

These identifiers MUST be assigned by the ISO recognised standards body of a country.
A national standards body SHOULD register any identifiers in this repository, but is not required to.

### Company specific

These identifiers MUST only be assigned by companies that have their own RICS or ERA company code.
A company SHOULD register any identifiers in this repository, but is not required to.

### Compressed company specific

These identifiers are to be construed as equivalent to the company specific identifiers (see above), but the company code is elided.
The company code is to be read as the relevant issuer, carrier, or product owner for the section of the ticket the extension appears in.
Compressed company specific identifiers SHALL NOT be registered in this repository, as they are merely a different representation of company specific identifiers.
Systems using compressed company specific identifiers SHOULD register a company specific identifier in this repository.

### Private

These identifiers MAY be used by any entity.
These identifiers SHALL NOT be registered in this repository.
Users of private identifiers MUST NOT make any assumptions about the uniqueness of any identifier and MUST be prepared to accept and handle clashes.

### Other

Any other value, not conforming to one of the 3 above formats MUST be registered in this repository *before* use.

## Registration tables

- `challenge_response.csv` - relating to the `dynamicContentResponseToChallenge` field of the `UicDynamicContentData` structure 
- `company_code.csv` - relating to the `otherCode.codeTable` field of the `CompanyCodeType` structure
- `data_type.csv` - relating to the `dataFormat` field of the `DataType` structure
- `extension_data.csv` - relating to the `extensionId` field of the `ExtensionData` structure
- `token_specification.csv` - relating to the `tokenSpecification` field of the `TokenType` structure

## Registration procedure

To register your extension identifier in this repository, do the following:

- Fork this repository
- Edit the table files as required to add your extension ID
- Submit a pull request with your additions

The PR will be reviewed by the UIC for conformance to these rules and, if all is in order, merged.

### Field meanings

The fields in tables in your PR MUST be filled as follows:

- `id` - the extension identifier you wish to register
- `name` - An English language description of your extension
- `reference` - A URL (ideally) or a document identifier where the specification of the extension can be found
- `registrant` - The name of the organisation that is responsible for this extension