create table line_item
(
    id           bigint not null auto_increment,
    quantity     integer,
    selled_price float(53),
    sub_total    float(53),
    order_id     bigint not null,
    product_id   bigint not null,
    primary key (id)
);