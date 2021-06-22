drop
database IF EXISTS hotel;

create
DATABASE hotel DEFAULT CHARACTER SET UTF8;

USE
hotel;

create TABLE roles
(
    id        INT         NOT NULL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL UNIQUE
);

insert into roles
values (0, 'Admin');
insert into roles
values (1, 'User');

create TABLE users
(
    id       INT                NOT NULL auto_increment,
    name     VARCHAR(20)        NOT NULL,
    login    VARCHAR(25) UNIQUE NOT NULL,
    password VARCHAR(20)        NOT NULL,
    email    VARCHAR(50) UNIQUE NOT NULL,
    role_id  INT                NOT NULL DEFAULT 1,
    PRIMARY KEY (id, role_id),
    FOREIGN KEY (role_id) REFERENCES roles (id) ON delete CASCADE ON update RESTRICT
)ENGINE=InnoDB;

insert into users
values (default, 'Olga', 'olga1234', 'password12345', 'olga.sidorova.v@gmail.com', 0);
insert into users
values (default, 'Ivan', 'ivan543', 'password54321', 'ivan@gmail.com', 1);
insert into users
values (default, 'Alex', 'alex098', 'password', 'alex@gmail.com', 1);

create TABLE room
(
    room_id       INT PRIMARY KEY NOT NULL auto_increment,
    capacity      ENUM('1', '2', '3', '4') NOT NULL default 1,
    room_category ENUM('ECONOMY', 'STANDARD', 'SUITE') default 'ECONOMY' NOT NULL,
    price         INT             NOT NULL

)ENGINE=InnoDB;

insert into room
values (default, 1, 1, 400);
insert into room
values (default, 2, 1, 500);
insert into room
values (default, 3, 1, 550);

insert into room
values (default, 1, 2, 650);
insert into room
values (default, 2, 2, 750);
insert into room
values (default, 3, 2, 850);

insert into room
values (default, 1, 3, 950);
insert into room
values (default, 2, 3, 1100);
insert into room
values (default, 3, 3, 1300);
insert into room
values (default, 4, 3, 1400);

create TABLE bid
(
    id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    room_number INT,
    email       VARCHAR(50)     NOT NULL,
    capacity    ENUM('1', '2', '3', '4') NOT NULL,
    category    ENUM('ECONOMY', 'STANDARD', 'SUITE') NOT NULL,
    arrival     DATE            NOT NULL,
    departure   DATE            NOT NULL,
    FOREIGN KEY (email) REFERENCES users (email) ON delete CASCADE ON update RESTRICT,
    FOREIGN KEY (room_number) REFERENCES room (room_id) ON delete CASCADE ON update RESTRICT

)ENGINE=InnoDB;
create TABLE bill
(
    id_bill   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    status    ENUM('UNPAID', 'PAID') NOT NULL,
    bid_bill  INT             NOT NULL UNIQUE,
    bill_date DATE            NOT NULL,
    FOREIGN KEY (bid_bill) REFERENCES bid (id)
)ENGINE=InnoDB;

create TABLE concatenated_table
(
    room_id     INT  NOT NULL,
    date_from   DATE NOT NULL,
    date_to     DATE NOT NULL,
    room_status ENUM('FREE', 'BOOKED', 'OCCUPIED', 'UNAVAILABLE'),
    FOREIGN KEY (room_id) REFERENCES room (room_id)

)ENGINE=InnoDB;

insert into concatenated_table values (8,'2021-06-25', '2021-07-5','UNAVAILABLE');
insert into concatenated_table values (4,'2021-06-25', '2021-07-25','UNAVAILABLE');