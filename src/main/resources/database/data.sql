INSERT INTO `categories` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'natus', '2022-06-08 19:27:08', '2022-06-08 19:27:08');

INSERT INTO `items` (`id`, `category_id`, `name`, `price`, `quantity_in_stock`, `created_at`, `updated_at`) VALUES
(1, 1, 'aut', 99.99, 5, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(2, 1, 'quia', 49.99, 21, '2022-06-08 19:27:08', '2022-06-08 19:27:08');

# INSERT INTO `users` (`id`, `created_at`, `updated_at`) VALUES
# (1, '2022-06-08 19:27:08', '2022-06-08 19:27:08');
#
# INSERT INTO `shopping_cart_items` (`id`, `shopping_cart_id`, `quantity`, `created_at`, `updated_at`) VALUES
# (1, '8b7ce7bb-071f-402d-b3ea-ebe8b6b88885', 5, '2022-06-08 19:27:08', '2022-06-08 19:27:08');
#
# INSERT INTO `orders` (`id`, `user_id`, `subtotal`, `total`, `created_at`, `updated_at`) VALUES
# (1, 1, NULL, NULL, '2022-06-08 19:27:08', '2022-06-08 19:27:08');
#
# INSERT INTO `orders_details` (`id`, `order_id`, `item_id`, `quantity`, `price`, `created_at`, `updated_at`) VALUES
# (1, 1, 1, 5, 99.99, '2022-06-08 19:27:08', '2022-06-08 19:27:08');

