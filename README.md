# Installation

Under `docker` directory, create an `.env` file and set following variables: 

- `DB_DATABASE={db name}`
- `DB_USERNAME={db user}`
- `DB_PASSWORD={db pwd}`

They will be picked by `docker compose` on build.

Under `src/main/resources` directory, create an `application.properties` file and set following variables:

- `spring.datasource.url=jdbc:mysql://{db host}:3306/{db name}`
- `spring.datasource.username={db user}`
- `spring.datasource.password={db pwd}`
- `spring.sql.init.mode=always`
- `spring.sql.init.schema-locations=classpath:database/schema.sql`
- `spring.sql.init.data-locations=classpath:database/data.sql`
- `spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect`
- `spring.jpa.hibernate.ddl-auto=none`

Run `gradle wrapper --gradle-version 7.4.1` to generate the necessary wrapper files in the project directory.