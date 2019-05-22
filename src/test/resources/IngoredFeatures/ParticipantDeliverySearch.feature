# new feature
# Tags: optional
    
Feature: Participant Deliver Search

Background: Background to set the Participant Deliver Search Page
        Given setup Participant Deliver Search Page

Scenario: Verify that the pagination with limit 5 doesnt bring more than 5 results with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When PD_Search Participant Delivers with limit "5"
    Then Verify that the Participant Deliver search dont return more than "5" results

Scenario: Verify that the pagination with limit 10 doesnt bring more than 10 results with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When PD_Search Participant Delivers with limit "10"
    Then Verify that the Participant Deliver search dont return more than "10" results

Scenario: Verify that the pagination with limit 25 doesnt bring more than 25 results with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"        
    When PD_Search Participant Delivers with limit "25"
    Then Verify that the Participant Deliver search dont return more than "25" results

Scenario: Verify that the pagination with limit 50 doesnt bring more than 50 results with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"        
    When PD_Search Participant Delivers with limit "50"
    Then Verify that the Participant Deliver search dont return more than "50" results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered and Document number with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "77752293004185"       
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered and Name with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Name "COOP AGROINDL LAR"     
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled and Document number with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Number "00296895000153"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled and Name with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Name "COOP AGROP DO CERRADO"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered and Document number with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "00296895000153"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered and Name with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Name "COOP AGROP DO CERRADO"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled and Document number with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Number "00296895000153"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled and Name with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Name "COOP AGROP DO CERRADO"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results



#Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Approved and Document number with an internal user
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        And Filter Sender by document Number "77752293004185"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Approved and Name with an internal user
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        And Filter Sender by document Name "COOP AGROINDL LAR"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results


########################################ACA EMPIEZAN LAS COMBINACIONES SACANDO FILTROS########################################

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"       
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"       
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Approved with an ADMINISTRATOR
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
     When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"      
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Headoffice with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

 ### PASAMOS LAS COMBINACIONES DE HEADOFFICE A AFFILIATE

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate, Date From, Date To with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results





Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results



Scenario: Search ROL filtering by Headoffice, Date From, Date To with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate, Date From, Date To with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results




Scenario: Search ROL filtering by Headoffice,  Date To, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice,  Date To, Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Headoffice, Date To with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice with an ADMINISTRATOR
     Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate,  Date To, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate,  Date To, Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results



Scenario: Search ROL filtering by Affiliate, Date To with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

##### SACANDO DATE TO


Scenario: Search ROL filtering by Headoffice, Date From, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From,  Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice, Date From, Status Approved with an ADMINISTRATOR
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date From "06/10/2018"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results




Scenario: Search ROL filtering by Affiliate, Date From, Status Registered with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From,  Status Canceled with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Affiliate, Date From, Status Approved with an ADMINISTRATOR
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

## SACANDO STATUS


Scenario: Search ROL filtering by Headoffice, Date From, Date To and Document number with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To and Document number with an ADMINISTRATOR
    Given PDS_An ADMINISTRATOR
    Given Filter PD Headoffice by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "00296895000153"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results
    

  ####################################################################################################################################
  ####################################################################################################################################
  
      
      
Scenario: Verify that the pagination with limit 5 doesnt bring more than 5 results with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When PD_Search Participant Delivers with limit "5"
    Then Verify that the Participant Deliver search dont return more than "5" results

Scenario: Verify that the pagination with limit 10 doesnt bring more than 10 results with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When PD_Search Participant Delivers with limit "10"
    Then Verify that the Participant Deliver search dont return more than "10" results

Scenario: Verify that the pagination with limit 25 doesnt bring more than 25 results with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When PD_Search Participant Delivers with limit "25"
    Then Verify that the Participant Deliver search dont return more than "25" results

Scenario: Verify that the pagination with limit 50 doesnt bring more than 50 results with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When PD_Search Participant Delivers with limit "50"
    Then Verify that the Participant Deliver search dont return more than "50" results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered and Document number with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered and Name with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Name "COOP AGROINDL LAR"
        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled and Document number with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Number "00296895000153"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled and Name with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Name "COOP AGROP DO CERRADO"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Approved and Document number with an internal user
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        And Filter Sender by document Number "77752293004185"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Approved and Name with an internal user
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        And Filter Sender by document Name "COOP AGROINDL LAR"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results


########################## aca EMPIEZAN LOS ESCENARIOS DEL AFFILIATE. SON SIMETRICOS A LOS DE ARRIBA. DEBERIAN FALLAR POR EL MOMENTO ####################


Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered and Document number with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "00296895000153"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered and Name with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Name "COOP AGROP DO CERRADO"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled and Document number with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Number "00296895000153"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled and Name with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
        And Filter Sender by document Name "COOP AGROP DO CERRADO"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results



#Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Approved and Document number with an internal user
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        And Filter Sender by document Number "77752293004185"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Approved and Name with an internal user
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        And Filter Sender by document Name "COOP AGROINDL LAR"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results


########################################ACA EMPIEZAN LAS COMBINACIONES SACANDO FILTROS########################################

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Approved with a PARTICIPANT
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Headoffice with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

 ### PASAMOS LAS COMBINACIONES DE HEADOFFICE A AFFILIATE

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Approved with a PARTICIPANT
#    Given PDS_A PARTICIPANT
#     Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


#### SACANDO USER


Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice, Date From, Date To, Status Approved with a PARTICIPANT
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From, Date To with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Affiliate, Date From, Date To, Status Approved with a PARTICIPANT
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


####### SACANDO DATE FROM


Scenario: Search ROL filtering by Headoffice,  Date To, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice,  Date To, Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice,  Date To, Status Approved with a PARTICIPANT
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date To with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice with a PARTICIPANT
     Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results


Scenario: Search ROL filtering by Affiliate,  Date To, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate,  Date To, Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date To "10/31/2019"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Affiliate,  Date To, Status Approved with a PARTICIPANT
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date To "10/31/2019"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date To with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date To "10/31/2019"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

##### SACANDO DATE TO


Scenario: Search ROL filtering by Headoffice, Date From, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From,  Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter PD status "CANCELED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Headoffice, Date From, Status Approved with a PARTICIPANT
#    Given Filter PD Headoffice by document "77752293000198"
#        And Filter Date From "06/10/2018"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Headoffice, Date From with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results




Scenario: Search ROL filtering by Affiliate, Date From, Status Registered with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter PD status "REGISTERED"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From,  Status Canceled with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter PD status "CANCELED"        
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

#Scenario: Search ROL filtering by Affiliate, Date From, Status Approved with a PARTICIPANT
#    Given Filter PD Affiliate by document "77752293004185"
#        And Filter Date From "06/10/2018"
#        And Filter PD status "APPROVED"
#        
#    When Search Participant Delivers
#    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Affiliate by document "77752293004185"
        And Filter Date From "06/10/2018"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

## SACANDO STATUS


Scenario: Search ROL filtering by Headoffice, Date From, Date To and Document number with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293000198"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "77752293004185"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results

Scenario: Search ROL filtering by Affiliate, Date From, Date To and Document number with a PARTICIPANT
    Given PDS_A PARTICIPANT
    Given Filter PD Headoffice by document "77752293004185"
        And Filter Date From "06/10/2018"
        And Filter Date To "10/31/2019"
        And Filter PD status "REGISTERED"
        And Filter Sender by document Number "00296895000153"
    When Search Participant Delivers
    Then Verify that the Participant Deliver search return results
          