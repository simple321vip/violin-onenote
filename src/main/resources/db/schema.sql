CREATE TABLE IF NOT EXISTS section(
     section_id INT NOT NULL AUTO_INCREMENT,
     user_id LONG NOT NULL,
     section_name CHAR(20) DEFAULT '未分类' NOT NULL,
     PRIMARY KEY (section_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS page(
     page_id INT NOT NULL AUTO_INCREMENT,
     section_id LONG NOT NULL,
     page_name CHAR(20) DEFAULT '未分类' NOT NULL,
     page_path CHAR(20) DEFAULT '' NOT NULL,
     PRIMARY KEY (page_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;