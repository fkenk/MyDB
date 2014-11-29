ALTER TABLE `kursschema`.`order_contract` 
ADD CONSTRAINT `fk_order_contract_production`
  FOREIGN KEY (`idProdution`)
  REFERENCES `kursschema`.`products` (`idProducts`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
