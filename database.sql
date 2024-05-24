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
	town_id INT NOT NULL,
	user_id BIGINT NOT NULL,
	permit_image VARCHAR(255) NULL,
	approved BOOLEAN NOT NULL DEFAULT FALSE,
	approved_by VARCHAR(255) NULL,
	approved_at DATETIME NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uq_resort_name UNIQUE (name),
	CONSTRAINT uq_resort_user_id UNIQUE (user_id),
    CONSTRAINT fk_resort_town_id FOREIGN KEY (town_id) REFERENCES town (id),
    CONSTRAINT fk_resort_user_id FOREIGN KEY (user_id) REFERENCES user (id)
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

CREATE TABLE IF NOT EXISTS room_reservation (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	room_id BIGINT NULL,
	start_date DATE NOT NULL,
	end_date DATE NULL,
	status ENUM('Pending', 'Confirmed', 'Declined', 'Cancelled') NOT NULL DEFAULT 'Pending',
	amount DECIMAL(19,2) NOT NULL DEFAULT 0.00,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_reservation_cottage_user_id FOREIGN KEY (user_id) REFERENCES user (id),
	CONSTRAINT fk_reservation_cottage_room_id FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE IF NOT EXISTS cottage_reservation (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	resort_id BIGINT NOT NULL,
	reservation_date DATE NULL,
	status ENUM('Pending', 'Confirmed', 'Declined', 'Cancelled') NOT NULL DEFAULT 'Pending',
	amount DECIMAL(19,2) NOT NULL DEFAULT 0.00,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_reservation_cottage_user_id FOREIGN KEY (user_id) REFERENCES user (id),
	CONSTRAINT fk_reservation_cottage_resort_id FOREIGN KEY (resort_id) REFERENCES resort (id)
);

CREATE TABLE IF NOT EXISTS payment (
	id BIGINT NOT NULL AUTO_INCREMENT,
	room_reservation_id BIGINT NULL,
	cottage_reservation_id BIGINT NULL,
	amount DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_payment_room_reservation_id FOREIGN KEY (room_reservation_id) REFERENCES room_reservation (id),
	CONSTRAINT fk_payment_cottage_reservation_id FOREIGN KEY (cottage_reservation_id) REFERENCES cottage_reservation (id)
);