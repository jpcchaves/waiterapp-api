create table orders
(
    order_id        bigint not null auto_increment,
    conclusion_date datetime(6),
    created_at      datetime(6),
    is_done         bit,
    is_paid         bit,
    order_code      binary(16),
    order_details   varchar(255),
    order_total     float(53),
    status          int,
    primary key (order_id)
);