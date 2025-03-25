CREATE TABLE IF NOT EXISTS `company` (
    `id_company` int NOT NULL AUTO_INCREMENT,
    `codigo_company` varchar(100) NOT NULL UNIQUE,
    `name_company` varchar(100) NOT NULL,
    `description_company` varchar(100) NOT NULL,
    PRIMARY KEY (`id_company`)
    );