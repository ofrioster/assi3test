assi3test
=========
need to check how to transfer pointer (like warehouse)
we have many empty constructors that can be delete- to check again
the ingredients vector in Dish can be change to arrylist
to check how we receive the address
to check the cook & delivery time
how to deliver an order to deliveryPerson after it finish? can a thread say that he finish?
does notifayAll tell all the threads?
how is Statistics connect to the program?
Management is not finish
maybe it is faster to send orders to management one by one so that the chef could start work on it
there might be a problem in the constructor in create new vectors
if one of the vector is empty some function return empty objects
does we need to implement full blocking queue?
add Address object

observer, observable - to implements from.... a well as runnable


arnon:
 if shutDown call function ShutDown in management
 to start the management run one thread after the warehouse, the chef and delivery person are load
 when you finish to load al the orders call to function: setReceiveAllOrders(Boolean receiveAllOrders); when receiveAllOrders=true
 do the logger
 