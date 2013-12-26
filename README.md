assi3test
=========

we have many empty constructors that can be delete- to check again
the ingredients vector in Dish can be change to arrylist
to check how we receive the address
to check the cook & delivery time
how to deliver an order to deliveryPerson after it finish? can a thread say that he finish?
how is Statistics connect to the program?
maybe it is faster to send orders to management one by one so that the chef could start work on it


observer, observable - to implements from.... a well as runnable
i remove synchronized in acquireIngredients- might be a problem
there might be a priblem with how many ingredients we use
some of the vector can be change to array list
how to decide how many threads in chefs? and to change it to blocking qeue-threadsPool. example: http://tutorials.jenkov.com/java-concurrency/thread-pools.html
why do we have an object in synchronized?
put the chef,deliveryPerson and order in arrayList

arnon:
 if shutDown call function ShutDown in management
 to start the management run one thread after the warehouse, the chef and delivery person are load
 when you finish to load al the orders call to function: setReceiveAllOrders(Boolean receiveAllOrders); when receiveAllOrders=true
 do the logger
 do the ant
 