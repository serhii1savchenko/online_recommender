-- Roles
INSERT INTO `roles` (`role`) VALUES
('ADMIN'),
('USER');

-- Administrator
INSERT INTO `users` (`idUser`, `name`, `password`, roleId) VALUES
(0, 'Serhii', '202cb962ac59075b964b07152d234b70', 1);

-- Users
INSERT INTO `users` (`name`, `password`, roleId) VALUES
('user1', '202cb962ac59075b964b07152d234b70', 2),
('user2', '202cb962ac59075b964b07152d234b70', 2),
('user3', '202cb962ac59075b964b07152d234b70', 2),
('user4', '202cb962ac59075b964b07152d234b70', 2),
('user5', '202cb962ac59075b964b07152d234b70', 2),
('user6', '202cb962ac59075b964b07152d234b70', 2),
('user7', '202cb962ac59075b964b07152d234b70', 2),
('user8', '202cb962ac59075b964b07152d234b70', 2),
('user9', '202cb962ac59075b964b07152d234b70', 2),
('user10', '202cb962ac59075b964b07152d234b70', 2),
('user11', '202cb962ac59075b964b07152d234b70', 2),
('user12', '202cb962ac59075b964b07152d234b70', 2),
('user13', '202cb962ac59075b964b07152d234b70', 2),
('user14', '202cb962ac59075b964b07152d234b70', 2),
('user15', '202cb962ac59075b964b07152d234b70', 2);
