CREATE TABLE `lal` (
  `idProducts` int(11) NOT NULL,
  `Productscol_Name` varchar(45) DEFAULT NULL,
  `Productscol_Price` double DEFAULT NULL,
  PRIMARY KEY (`idProducts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP  TABLE 
    lal;
    

CREATE TABLE `customer` (
  `idCustomer` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Adress` varchar(100) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  `Banking_Account` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_contract` (
  `idOrder` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `idCustomer` int(11) DEFAULT NULL,
  `idProdution` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `monthDeliver` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `fk_order_contract_customer_idx` (`idCustomer`),
  KEY `fk_order_contract_production_idx` (`idProdution`),
  CONSTRAINT `fk_order_contract_customer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`idcustomer`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_contract_production` FOREIGN KEY (`idProdution`) REFERENCES `products` (`idProducts`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table
 order_contract;