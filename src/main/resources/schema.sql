/* Ingredient table */
create table if not exists Ingredient (
    id varchar(4) not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
);
/* Taco table */
create table Taco (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);
/* Taco_Ingredients table for one to many mapping between Taco and Ingredients */
create table Taco_Ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);
/* Setting up the foreign key for the Taco_Ingredients table */
alter table Taco_Ingredients add foreign key (taco) references Taco(id);
alter table Taco_Ingredients add foreign key (ingredient) references Ingredient(id);

/* Taco_Order table for capturing order details */
create table if not exists Taco_Order(
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(50) not null,
    deliveryZip varchar(50) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

/* Taco_Order_Tacos table for holding all the tacos in a order */
create table if not exists Taco_Order_Tacos (
    tacoOrder bigint not null,
    taco bigint not null
);

/* Setting up the foreign key for the taco order table */
alter table Taco_Order_Tacos add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos add foreign key (taco) references Taco(id);









