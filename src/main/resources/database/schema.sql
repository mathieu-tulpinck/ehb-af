CREATE TABLE IF NOT EXISTS categories
(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT NULL,
    `updated_at` TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table IF NOT EXISTS items
(
    `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `category_id` BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (`category_id`)
        REFERENCES `categories` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    `name` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `price` DECIMAL(19,2) UNSIGNED NOT NULL,
    `vat_rate` INT UNSIGNED NOT NULL DEFAULT '21' COMMENT 'in percent',
    `quantity_in_stock` INT UNSIGNED NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT NULL,
    `updated_at` TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



