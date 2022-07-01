INSERT INTO `categories` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'natus', '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(2, 'libero', '2022-06-08 19:27:08', '2022-06-08 19:27:08');

INSERT INTO `items` (`id`, `category_id`, `name`, `price`, `quantity_in_stock`, `created_at`, `updated_at`) VALUES
(1, 1, 'aut', 100.00, 5, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(2, 1, 'quia', 20.00, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(3, 1, 'eum', 39.99, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(4, 1, 'dolore', 29.99, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(5, 1, 'optio', 19.99, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(6, 2, 'necessitatibus', 59.99, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(7, 2, 'consequuntur', 59.99, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(8, 2, 'provident', 100.00, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(9, 2, 'tenetur', 5.99, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08'),
(10, 2, 'quis', 6.99, 10, '2022-06-08 19:27:08', '2022-06-08 19:27:08');

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

