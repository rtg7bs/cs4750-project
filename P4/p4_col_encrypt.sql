USE PLAYLIST_PROJECT_CS4750
GO

-- Create master/symmetric keys and certificate
CREATE MASTER KEY ENCRYPTION BY PASSWORD = 'StrongPassword123!';

CREATE CERTIFICATE UserCert
WITH SUBJECT = 'Certificate for User Password Encryption';

CREATE SYMMETRIC KEY UserKey
WITH ALGORITHM = AES_256
ENCRYPTION BY CERTIFICATE UserCert;

-- Create a new col to store og passwords (decrypted) as backups
ALTER TABLE [user]
ADD og_password_backup NVARCHAR(254);

UPDATE [user]
SET og_password_backup = [password];

-- Create a new col to store encrypted passwords
ALTER TABLE [user]
ADD encrypted_password VARBINARY(254);

-- Encrypt all passwords in the password col and store in the encrypted_password col
OPEN SYMMETRIC KEY UserKey
DECRYPTION BY CERTIFICATE UserCert;

UPDATE [user]
SET encrypted_password = EncryptByKey(Key_GUID('UserKey'), password);

CLOSE SYMMETRIC KEY UserKey;

-- Remove the og password col (so that we only have the og_password_backup col and encrypted_password col)
ALTER TABLE [user]
DROP COLUMN [password];

-- EXTRA: Decrypt passwords in the encrypted_password col
-- OPEN SYMMETRIC KEY UserKey
-- DECRYPTION BY CERTIFICATE UserCert;

-- SELECT username, CONVERT(VARCHAR(254), DecryptByKey(encrypted_password)) AS [password]
-- FROM [user];

-- CLOSE SYMMETRIC KEY UserKey;