# Just Some Notes

## SYNOPSIS
* Corporation has contracted the team to create a client-server system for trading of virtual assets. 
	a. Description of project (Appendix A)
	b. Collection of user stories from stakeholders (Appendix B)

## TASK
* Gather requirements from project description and user stories
* Produce detailed design of system that meets above requirements
* Implement the system in java along with comprehensive unit testing. (JUnit)
* Demonstrate system to client, showing that their needs have been met. (Video demo)


## PROJECT DESCRIPTION

* Trade/Share many types of resources across the organisation
	* Computational/hardware/software resources/licenses.

### Virtual Trading Platform
* OrganisationalUnits will be given a budget of "credits" 
* Credits can be used to buy access to resources from other organisational units.

Trade platform must be similar to a "marketplace model"
* Buyers must put in an order for a specific quantity at a particular price
	* e.g.  BUY 100 cpu hours at 10 credits each
	* 	SELL 500 cpu hours at 10 credits each.
* Software will periodically check/reconcile all outstanding trades.
	* bought stock will be subtracted from available selling
	* e.g. SELL 400 cpu hours at 10 credits each
* Trade will not happen both parties do not accept the trade
* Competitive trades will always buy for cheapest price


### Client-Server model. 
* 1 server
* X clients connect to server to list trades.
* No limit to number of commodities in the database
* No limit to number of trades that can be listed
* No limit to number of users in the system.
* Everyone has their own unique login