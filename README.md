# Installation

From project folder, run `docker compose up [-d]`.

A default user is seeded on db creation, the credentials are: `mathieu@webshop.test/password`

Default db credentials are: `dba/n7+Cgm9K`

Following ports are published on the docker host:
 
- `db`: `3306`
- `phpmyadmin`: `8090`
- `mailhog`: `8025`
- `webshop`: `8080`

After the first boot, open `src/main/resources/application.properties` and comment out `spring.sql.init.data-locations=classpath:database/data.sql` to deactivate seeding. Rebuild the image by running `docker compose build [--no-cache]`.