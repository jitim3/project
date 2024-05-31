CREATE TABLE IF NOT EXISTS user_type (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255) NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uq_user_name UNIQUE (name)
);

INSERT INTO user_type (id, name, description) VALUES (1, 'SUPER_ADMIN', 'Super Administrator');
INSERT INTO user_type (id, name, description) VALUES (2, 'ADMIN', 'Administrator');
INSERT INTO user_type (id, name, description) VALUES (3, 'CUSTOMER', 'Customer');

CREATE TABLE IF NOT EXISTS user (
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	user_type_id INT NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uq_user_username UNIQUE (username),
    CONSTRAINT fk_user_user_type_id FOREIGN KEY (user_type_id) REFERENCES user_type (id)
);

INSERT INTO user (id, username, password, user_type_id) VALUES(1, 'sa', 'sa', 1);

CREATE TABLE IF NOT EXISTS customer (
	id BIGINT NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	contact_number VARCHAR(255) NOT NULL,
	email_address VARCHAR(255) NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uq_customer_email_address UNIQUE (email_address),
    CONSTRAINT fk_customer_id FOREIGN KEY (id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS town (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uq_town_name UNIQUE (name)
);

INSERT INTO town (id, name) VALUES(1, 'Carcar');
INSERT INTO town (id, name) VALUES(2, 'Barili');
INSERT INTO town (id, name) VALUES(3, 'Moalboal');
INSERT INTO town (id, name) VALUES(4, 'Alcoy');
INSERT INTO town (id, name) VALUES(5, 'SanTander');
INSERT INTO town (id, name) VALUES(6, 'Oslob');

CREATE TABLE IF NOT EXISTS commission_rate (
	id INT NOT NULL AUTO_INCREMENT,
    rate DECIMAL(19,2) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

INSERT INTO commission_rate (id, rate) VALUES (1, 5);

CREATE TABLE IF NOT EXISTS resort (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	location VARCHAR(255) NULL,
	how_to_get_there TEXT,
	resort_fee DECIMAL(19,2) NULL,
	cottage_fee DECIMAL(19,2) NULL,
	pool_fee DECIMAL(19,2) NULL,
	resort_image VARCHAR (255) NULL,
	pool_image VARCHAR (255) NULL,
	cottage_image VARCHAR (255) NULL,
	user_id BIGINT NOT NULL,
	permit_image VARCHAR(255) NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uq_resort_name UNIQUE (name),
	CONSTRAINT uq_resort_user_id UNIQUE (user_id),
    CONSTRAINT fk_resort_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS town_resort (
    id BIGINT NOT NULL AUTO_INCREMENT,
    town_id INT NOT NULL,
    resort_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uq_town_resort_town_id_resort_id UNIQUE (town_id, resort_id),
    CONSTRAINT fk_town_resort_town_id FOREIGN KEY (town_id) REFERENCES user (id),
    CONSTRAINT fk_town_resort_resort_id FOREIGN KEY (resort_id) REFERENCES resort (id)
);

CREATE TABLE IF NOT EXISTS room_availability_type (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uq_room_availability_type_name UNIQUE (name)
);

INSERT INTO room_availability_type (id, name) VALUES (1, 'Day Use');
INSERT INTO room_availability_type (id, name) VALUES (2, 'Night Use');
INSERT INTO room_availability_type (id, name) VALUES (3, 'Day and Night Use');
	
CREATE TABLE IF NOT EXISTS room (
	id BIGINT NOT NULL AUTO_INCREMENT,
	resort_id BIGINT NOT NULL,
	room_availability_type_id INT NOT NULL,
	room_type ENUM('Normal','Family') NOT NULL,
	num_of_pax INT NOT NULL,
	rate_per_night DECIMAL(19,2) NOT NULL,
	description TEXT,
	room_image1 VARCHAR (255) NULL,
	room_image2 VARCHAR (255) NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_room_resort_id FOREIGN KEY (resort_id) REFERENCES resort (id),
	CONSTRAINT fk_room_room_availability_type_id FOREIGN KEY (room_availability_type_id) REFERENCES room_availability_type (id)
);

CREATE TABLE IF NOT EXISTS review (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	resort_id BIGINT NOT NULL,
	rate INT NOT NULL DEFAULT 0,
	comment TEXT,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_review_user_id FOREIGN KEY (user_id) REFERENCES user (id),
	CONSTRAINT fk_review_resort_id FOREIGN KEY (resort_id) REFERENCES resort (id)
);

CREATE TABLE IF NOT EXISTS reservation (
	id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    resort_id BIGINT NULL,
    room_id BIGINT NULL,
    reservation_date DATE NOT NULL,
    end_date DATE NULL,
    status ENUM('Pending', 'Confirmed', 'Declined', 'Cancelled') NOT NULL DEFAULT 'Pending',
	amount DECIMAL(19,2) NOT NULL DEFAULT 0.00,
	commission_rate_id INT NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_reservation_user_id FOREIGN KEY (user_id) REFERENCES user (id),
	CONSTRAINT fk_reservation_resort_id FOREIGN KEY (resort_id) REFERENCES resort (id),
    CONSTRAINT fk_reservation_room_id FOREIGN KEY (room_id) REFERENCES room (id),
    CONSTRAINT fk_reservation_commission_rate_id FOREIGN KEY (commission_rate_id) REFERENCES commission_rate (id)
);

CREATE TABLE IF NOT EXISTS payment (
	id BIGINT NOT NULL AUTO_INCREMENT,
	reservation_id BIGINT NULL,
	amount DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_payment_reservation_id FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

CREATE VIEW super_admin_total_sales AS
SELECT
	SUM(rsv.amount * (cr.rate / 100)) AS total
FROM reservation rsv
LEFT JOIN resort rst on rst.id = rsv.resort_id
LEFT JOIN room rm on rm.id = rsv.room_id
LEFT JOIN resort rst2 on rst2.id = rm.resort_id
LEFT JOIN user u ON u.id = rst.user_id OR u.id = rst2.user_id
LEFT JOIN commission_rate cr ON cr.id = rsv.commission_rate_id
ORDER BY total;

CREATE VIEW admin_total_sales AS
SELECT
	u.id AS admin_id, SUM((rsv.amount * ((100 - cr.rate) / 100))) AS total
FROM reservation rsv
LEFT JOIN resort rst on rst.id = rsv.resort_id
LEFT JOIN room rm on rm.id = rsv.room_id
LEFT JOIN resort rst2 on rst2.id = rm.resort_id
LEFT JOIN user u ON u.id = rst.user_id OR u.id = rst2.user_id
LEFT JOIN commission_rate cr ON cr.id = rsv.commission_rate_id
GROUP BY admin_id ORDER BY total;

CREATE TABLE IF NOT EXISTS withdrawal (
	id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
	amount DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_withdrawal_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);