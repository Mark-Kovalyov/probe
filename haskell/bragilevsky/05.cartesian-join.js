# Modern way

const f = (a,b) => 
 [].concat(...a.map(d => b.map( e=> [].concat(d,e))));

const cartesianProduct = (a, b, ...c) => 
 (b ? cartesianProduct(f(a,b), ...c) : a);

let a = cartesianProduct([ 
 [1,2,3],
 [4,5,6],
 [7,8],
 [9,10]
]);

