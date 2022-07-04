# CREATE TABLE users(
#     `username` VARCHAR(50) NOT NULL PRIMARY KEY,
#     `password` VARCHAR(100) NOT NULL,
#     `enabled` TINYINT NOT NULL DEFAULT 1
# );
#
# CREATE TABLE authorities (
#      `username` VARCHAR(50) NOT NULL,
#      `authority` VARCHAR(50) NOT NULL,
#      constraint fk_authorities_users foreign key(`username`) references users(username)
# );
# create unique index ix_auth_username on authorities (`username`,`authority`);

# Drops automatically generated foreign key.
# ALTER TABLE authorities
# DROP FOREIGN KEY `FK46xnogm0sbytfo61g6ntvroaa`;
# ALTER TABLE authorities
# DROP COLUMN `account_id`;