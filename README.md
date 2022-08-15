# Installation

Clone git repo: `git clone <url> webshop`.

Add `.env` file to root folder.

Add `application.properties` file to `src/main/resources`.

The app runs locally against a containerized db service.

After the first boot, open `src/main/resources/application.properties` and comment out `spring.sql.init.data-locations=classpath:database/data.sql` to deactivate seeding.

# Services

| service | external url |
|---|---|
| phpmyadmin | http://[docker host ip]:8090 |
| mailhog | http://[docker host ip]:8025 |

# Use

A default user is seeded on db creation, the credentials are: `mathieu@webshop.test/password`.

A user must be authenticated before adding items to his cart.

The checkout process takes the form of an order confirmation sent by email.