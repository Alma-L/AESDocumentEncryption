# Document Encryption Java Application

### Institution
- **Name**: University of Prishtina (UP)
- **Faculty**: Faculty of Electrical and Computer Engineering (FIEK)
- **Course**: "Siguri e Informacionit" (Information Security)
- **Program**: First Semester, Master's

## Overview
This project provides a Java-based application for encrypting and decrypting documents. The application leverages **CBC (Cipher Block Chaining)** mode to securely handle sensitive files, ensuring confidentiality and integrity. It demonstrates secure encryption and decryption techniques in line with modern information security standards.

**Note**: This project is developed for academic purposes as part of the Siguri e Informacionit course.

## Features

- **Encryption**: Securely encrypt PDF files using the CBC mode.
- **Decryption**: Restore original PDF content using the decryption process.
- **Customizable Key Management**: Define custom encryption keys for enhanced security.

## Project Structure

### Files Included:
- **`DocumentEncryption.java`**: Core class for encryption and decryption functionality.
- **`PDFDocumentEncryption.java`**: Unit tests for ensuring encryption/decryption reliability.
- **`TextDocumentEncryption.java`**: Demonstrates how to encrypt and decrypt files.
- **`LICENSE`**: License for the project.
- **`README.md`**: Documentation for the project.
- **`BookForEncryption.pdf`**: Sample PDF file for testing encryption.
- **`BookForEncryption_decrypted.pdf`**: Decrypted version of the sample PDF.
 
# Results Sample
## PDF Sample
 ![This is a picture of the PDF we will try to encrypt **BookForEncryption.pdf**](pdf_encrypted.png) <br>
 Here is the sample PDF we will encrypt: **BookForEncryption.pdf**<br><br>
 ![alt text](pdf_for_encryption.png)<br>
 This is a result from **BookForEncryption_encrpyted.pdf**, this is generated when we run file **PDFDocumentEncryption.java**
## Text file Sample
![This is a picture of the text file we create and proceed with encryption **TextForEncryption.pdf**](text_file_for_encryption.png) <br>
This is a picture of the text file we create and proceed with encryption **TextForEncryption.txt**<br><br>
![alt text](text_file_encryption_output.png)<br>
The encrypted version is generated after running **TextDocumentEncryption.java**





[def]: pdf_encrypted.png