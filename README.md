# Installation

Clone git repo: `git clone <url> webshop`.

Add `.env` file to top folder.

Add `application.properties` file to `src/main/resources`.

From the top folder, run `docker compose up -d`.

After the first boot, open `src/main/resources/application.properties` and comment the following lines out to deactivate seeding:

- `spring.jpa.defer-datasource-initialization=true`
- `spring.sql.init.mode=always`
- `spring.sql.init.data-locations=classpath:database/data.sql`

Rebuild the image by running `docker compose build [--no-cache]`.

# Services

| service    | external url                 |
|------------|------------------------------|
| phpmyadmin | http://[docker host ip]:8090 |
| mailhog    | http://[docker host ip]:8025 |
| webshop    | http://[docker host ip]:8080 | 

# Use

A default user is seeded on db creation, the credentials are: `mathieu@webshop.test/password`.

A user must be authenticated before adding items to his cart.

The checkout process takes the form of an order confirmation sent by email.