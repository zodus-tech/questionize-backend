INSERT IGNORE INTO `department` (`id`, `name`)
    VALUES (UUID_TO_BIN(UUID()), "Admin");

INSERT IGNORE INTO `administrator` (`id`, `name`, `password`, `username`, `department_id`)
    VALUES(UUID_TO_BIN(UUID()), "Administrator", "admin123", "admin", (SELECT `id` FROM `department` WHERE `name` = "Admin" LIMIT 1));