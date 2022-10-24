# courier-service
## This courier service application majorly consist of two features - 
### 1.Delivery Cost Estimation with Offers - 
As part of this feature You will be able to calculate delivery cost and discount basis of applied offers.
#### Offers Codes -
##### Offer Code, Discount(%), Distance (km), Weight (kg)
* OFR001, 10%, < 200, 70-200
* OFR002, 7%, 50-150, 100-250
* OFR003, 5%, 50-250, 10-150

#### Offer Criteria -
* Only one offer code can be applied for any given package. 
* Package should meet the required mentioned offer criterias.
* If offer code is not valid / Not Found, discounted amount will be equal to 0.

#### How to calculate Delivery Cost - Follow the steps Below
* Run the application
* Once the application ran successfully it will ask to enter base cost
* Then it will ask to enter the number of package for you want to calculate delivery cost
* Once you enter the number of package It will ask for package details one by one.
* Please enter package details order of - 
* 1.Package name(String value)
* 2.Package weight (integer value)
* 3.Package Distance (Integer value)
* 4.Coupon code if applicable or input NA (String value)
* You must enter 4 value in above order by space separated for package details
* Example -  Package Request - PKG1 5 5 OFR001
* Once you entered all package details It will ask Do You Want to Calculate delivery Time
* Enter No
* It will print Courier Charge Response of all packages.

### 2.Delivery Time Estimation - 
As part of this feature apart from Courier Charge You will be able to calculate 
Delivery Time

#### How to calculate Delivery Time - Follow the steps Below
* Follow the above calculate Delivery Cost steps till Do You Want to Calculate delivery Time ?
* Enter Yes as Input value for Do You Want to Calculate delivery Time ?
* After this You need to input  " no_of_vehicles max_speed max_carriable_weight" by space separated.
* Please input only 3 value in same above order. Example- 2 70 200
* After this you will get Courier charge response with Delivery time of each package.