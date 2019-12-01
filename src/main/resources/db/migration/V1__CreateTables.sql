create table products (
	id int not null auto_increment,
    name varchar(60) not null,
    description varchar(200),
    price numeric(10,2) default 0,
    quantity int default 0,
    primary key (id)
);

drop table if exists reviews;
create table reviews (
	id int not null auto_increment,
    username varchar(60) default 'anonymous',
    rating int not null,
    review_text varchar(200),
    product_id int not null,
    primary key (id),
    constraint `fk_product` foreign key (product_id) references products (id)
);

drop table if exists comments;
create table comments (
	id int not null auto_increment,
    username varchar(60) not null,
    comment_text varchar(1000) not null,
    review_id int not null,
    primary key (id),
    constraint `fk_review` foreign key (review_id) references reviews (id)
);