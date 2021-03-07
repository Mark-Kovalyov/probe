def productByCat(cat : String) = 
  orders.flatMap(o => o.products)
   .filter(p => p.category == cat)