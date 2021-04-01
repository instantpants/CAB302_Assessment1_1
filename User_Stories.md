# User Stories

## Common Abbreviations
Original | Abbreviated
------------ | -------------
Organisational Unit | OU
Database | DB
IT Admin Team | ITAT
System Administrator | SysAdmin
IT Security Team | ITSec


### Friendly GUI
As a user, I think it's important to have a nice, friendly GUI interface, 
not a dusty old command line interface.

### USERNAME/PASSWORD LOGIN
As the leader of an OU, I need all of the members of my team to have access to this system, 
through their own individual usernames and passwords. I need them all to be trading using 
the credit balance and assets of the OU they are part of.

### MODIFY ORGANISATIONAL UNIT LISTINGS
As a user, sometimes after adding an offer I might decide that I want to remove that offer, 
perhaps to relist it again at a different price. I should be able to see all the currently 
standing offers from my OU in the database and be able to selectively remove them.

### BUY/SELL WITHIN DEPARTMENTS MEANS
As the leader of an OU, I do not want to accidentally put in a BUY offer for more credits than
my OU has, or SELL offer for more of an asset than my OU has. We do not want our OUs to risk going
into debt. The system should check for this and stop us from listing more than we have.

e.g. 
If I have 50 widgets and I have a SELL offer for 30 of them, I can only create a SELL offer for 20.
I will have to cancel the first offer if I want to create another for more.

### IT ADMINISTRATION TEAM PERMISSIONS
As a member of the ITAT, I require that only we (the ITAT) have the ability to:
* Create new OU's. 
* Edit the number of credits they have
* Number of each asset they have
* Add new asset types to the DB
* Add new users and:
	* Assign them passwords 
	* Assign them to OU's. 
* We need to have special types of user accounts that can do this from the GUI client.

Members of the ITAT should also be able to add new users with the same level of access,
so we can give it to anyone new that joins the ITAT.

### PERSONAL USER ACCOUNT MANAGEMENT
As a user, it would be nice if I could change my own password without having to ask the ITAT.

### SALES HISTOGRAM (non essential)
As a user, when I go to buy or sell an asset, I want to be able to:
* See the price history of that asset (what it has sold for in the past.)
	* Would be nice to show this on some kind of graph
		* Date on X axis, price on Y axis
* Similar to the stock markets so that I can see the trend


### OU NOTIFICATIONS
As a user, when I have the software open and a trade involving my OU is reconciled, 
I would like it to show a message somewhere. Nothing too obnoxious, but it's nice to know
when a trade is fulfilled.

### PORTABLE SERVER CONFIGURATION
As a SysAdmin, I would like the client to read from some kind of config file to get the server IP
and port to connect to. The server should do the same thing to get its port number. 
Reason is, I may have to move the server to run on a different machine at some point, 
and I think this is the easiest way to get the new config information out to everyone.

### DATABASE REQUIREMENTS
As a SysAdmin, I require the following things to be stored in a MariaDB or PostgreSQL or SQLite3 DB
on the server, so they are all kept in one place and they can be backed up easily:
* User Information (username, pass, account type, OU)
* OU information (OU name, credits, assets, quantity of each asset)
* Asset Types (names)
* Current Trades (BUY/SELL, OU, asset name, quantity, price, date)
* Trade history (as above)

### PASSWORD ENCRYPTION/HASHING
As the head of ITSec, I do not want plaintext passwords being sent over our network. 
At least hash the password before sending it over. In the same way, I don't want to see plaintext
passwords in the database either.