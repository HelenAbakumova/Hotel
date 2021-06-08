drop database IF EXISTS hotel;

create DATABASE hotel DEFAULT CHARACTER SET UTF8;

USE hotel;

create TABLE roles(
                      id INT NOT NULL PRIMARY KEY,
                      role_name VARCHAR(20) NOT NULL UNIQUE
);

insert into roles values(0, 'Admin');
insert into roles values(1, 'User');

create TABLE users(
                      id INT NOT NULL auto_increment,
                      name VARCHAR(20) NOT NULL,
                      login VARCHAR(25) UNIQUE NOT NULL,
                      password VARCHAR(20) NOT NULL,
                      email VARCHAR(50) UNIQUE NOT NULL,
                      role_id INT NOT NULL DEFAULT 1,
                      PRIMARY KEY (id, role_id),
                      FOREIGN KEY(role_id) REFERENCES roles(id) ON delete CASCADE	ON update RESTRICT
)ENGINE=InnoDB;

insert into users values(default, 'Olga', 'olga1234', 'password12345', 'olga.sidorova.v@gmail.com', 0);
insert into users values(default, 'Ivan','ivan543', 'password54321', 'ivan@gmail.com', 1);
insert into users values(default, 'Alex','alex098', 'password', 'alex@gmail.com', 1);

create TABLE room(
                     room_id INT PRIMARY KEY NOT NULL auto_increment,
                     capacity ENUM('1', '2', '3', '4') NOT NULL default 1,
                     room_categories ENUM('Economy', 'Standard', 'Suite') default 'Economy' NOT NULL,
                     price INT NOT NULL,
                     room_status ENUM('FREE', 'BOOKED', 'OCCUPIED', 'UNAVALIABLE') default 'FREE'

)ENGINE=InnoDB;

insert into room values(default, 1, 1, 400, default);
insert into room values(default, 2, 1, 500, default);
insert into room values(default, 3, 1, 550, default);

insert into room values(default, 1, 2, 650, default);
insert into room values(default, 2, 2, 750, default);
insert into room values(default, 3, 2, 850, default);

insert into room values(default, 1, 3, 950, default);
insert into room values(default, 4, 3, 1100, default);
insert into room values(default, 3, 3, 1300, default);

create TABLE order_status(
                                status_id INT PRIMARY KEY NOT NULL,
                                status VARCHAR(20) NOT NULL UNIQUE
)ENGINE=InnoDB;

insert into order_status values(1, 'SEARCH');
insert into order_status values(2, 'WAITING_OF_ACCEPT');
insert into order_status values(3, 'WAITING_OF_PAYMENT');
insert into order_status values(4, 'BOOKED');

create TABLE orders_selection (
                    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    user_id INT NOT NULL,
                    capacity_id INT NOT NULL,
                    category_id INT NOT NULL,
                    arrival DATE NOT NULL,
                    departure DATE NOT NULL,
                    room_id INT NOT NULL,
                    status INT NOT NULL,
                    FOREIGN KEY(user_id) REFERENCES users(id) ON delete CASCADE ON update RESTRICT,
                    FOREIGN KEY(room_id) REFERENCES room(room_id) ON delete CASCADE ON update RESTRICT

)ENGINE=InnoDB;

create TABLE bill_status(
                                status_id INT PRIMARY KEY NOT NULL,
                                status_bill VARCHAR(20) NOT NULL UNIQUE
)ENGINE=InnoDB;

insert into bill_status values(1, 'Paid');
insert into bill_status values(2, 'Unpaid');

create TABLE bill(
                                id_bill INT PRIMARY KEY NOT NULL,
                                status INT NOT NULL ,
                                order_bill VARCHAR(20) NOT NULL UNIQUE,
                                totalPrice INT NOT NULL UNIQUE,
                                billDate DATE NOT NULL UNIQUE,
                                FOREIGN KEY(status) REFERENCES bill_status(status_id) ON delete CASCADE ON update RESTRICT
)ENGINE=InnoDB;
