# Installation

From project folder, run `docker compose up [-d]`.

# Services

| service | external url |
|---|---|
| phpmyadmin | http://[docker host ip]:8090 |
| mailhog | http://[docker host ip]:8025 |
| webshop | http://[docker host ip]:8080 |
 
# Use

A default user is seeded on db creation, the credentials are: `mathieu@webshop.test/password`

Default db credentials are: `dba/n7+Cgm9K`

Following ports are published on the docker host:

After the first boot, open `src/main/resources/application.properties` and comment out `spring.sql.init.data-locations=classpath:database/data.sql` to deactivate seeding. Rebuild the image by running `docker compose build [--no-cache]`.
