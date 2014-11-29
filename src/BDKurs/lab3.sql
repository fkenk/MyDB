select * from customer
  inner join products
  on customer.idCustomer = products.idProducts;

select * from customer
  left join products
  on customer.idCustomer = products.idProducts;

select * from customer
  cross join products;

select order_contract.date, order_contract.idCustomer, customer.name, AVG(order_contract.count)
  from order_contract
  inner join customer
  on customer.idCustomer = order_contract.idCustomer
  group by customer.idCustomer;

select order_contract.idCustomer, AVG(order_contract.count)
  from order_contract
  group by order_contract.idCustomer;

