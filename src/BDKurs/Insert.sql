USE `kursschema`;


INSERT INTO `kursschema`.`customer`
(`idCustomer`,
`Name`,
`Adress`,
`Phone`,
`Banking_Account`)
VALUES
(2,
('Tim Burton'),
('New York'),
('23234234'),
('324234234'));

USE `kursschema`;





select * from customer;

select * from products;

select * from order_contract;

INSERT INTO `kursschema`.`order_contract`
(`idOrder`,
`date`,
`idCustomer`,
`idProdution`,
`count`,
`monthDeliver`)
VALUES
(1,
('1991-01-01'),
1,
1,
2,
3);

USE `kursschema`;
INSERT INTO `kursschema`.`products`
(`idProducts`,
`Name`,
`Price`)
VALUES
(1,
('Cocaine'),
100);
