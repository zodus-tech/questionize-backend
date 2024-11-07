INSERT IGNORE INTO `department` (`id`, `name`)
    VALUES (UUID(), "Admin");

-- senha admin123
INSERT IGNORE INTO `administrator` (`id`, `name`, `password`, `username`, `department_id`)
    VALUES(UUID(), "Administrator", "$2a$12$4F5OCej4ao6lb4mba3jIV.4d.VdiT7OSqSM6u/dWKLtrNWEC.2jT2", "admin", (SELECT `id` FROM `department` WHERE `name` = "Admin" LIMIT 1));