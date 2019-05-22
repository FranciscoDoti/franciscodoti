# new feature
# Tags: optional
    
Feature: ROL BULK

    Background: to set bulk feature env
        Given RB_set ROL Bulk Page
            And RB_Set new UUID



# A REUBICAR






Scenario: Bulk with all data ok and 3 records with ADMINISTRATOR
    Given Bulk file with 3 records and all data ok
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "REGISTERED"

Scenario: Bulk with rol previously loaded
        Given Bulk file with 3 records and all data ok
            And RB_Replace ID with new UUID
            And RB_An ADMINISTRATOR
        When RB_I post the Bulk
        Then RB_Verify that Bulk status code was 200
            And RB_Verify the status of the Bulk load is "BULK_ERROR"



#OTRAS VALIDACIONES








#GENERAL VALIDATIONS

Scenario: Bulk with some errors and 3 records with ADMINISTRATOR
    Given Bulk file with 3 records and several errors
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"





Scenario: Verify that the system allows to download a report with status REGISTERED with ADMINISTRATOR
    Given RB_An ADMINISTRATOR
    When RB_View uploaded files
        And Select a file with status "REGISTERED"
        And RB_Download File
    Then RB_Verify that the download response status code was 200


Scenario: Verify that the system allows to download a report with status BULK_ERROR with ADMINISTRATOR
    Given RB_An ADMINISTRATOR
    When RB_View uploaded files
        And Select a file with status "BULK_ERROR"
        And RB_Download File
    Then RB_Verify that the download response status code was 200



#VALIDACIONES CUANDO ES INTACTA

Scenario: Bulk with Received Declared Volume with big number for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with Received Declared Volume with big number and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with Received Declared Volume with negative number for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with Received Declared Volume with negative number and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with Received Declared Volume  not number for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with Received Declared Volume not number and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with Received Declared Volume  null for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with Received Declared Volume null and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with receivedPositiveTestedVolume  null for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with ReceivedPositiveTestedVolume NULL and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with receivedPositiveTestedVolume  negative number for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with ReceivedPositiveTestedVolume negative number and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with receivedPositiveTestedVolume  not number for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with ReceivedPositiveTestedVolume not number and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with receivedPositiveTestedVolume  big number for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with ReceivedPositiveTestedVolume big number and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"



Scenario: Bulk with fixedVolume null for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with fixedVolume null and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"



Scenario: Bulk with fixedVolume not number for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with fixedVolume not number and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with fixedVolume negative for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with fixedVolume negative and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with fixedVolume with big value for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with fixedVolume with big value and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"









Scenario: Bulk with fixedPrice null for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with fixedPrice null and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with fixedPrice negative for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with fixedPrice negative and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with fixedPrice with big value for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with fixedPrice big value and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with receivedNegativeTestedVolume null for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with receivedNegativeTestedVolume null and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"



Scenario: Bulk with receivedNegativeTestedVolume with big value for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with receivedNegativeTestedVolume with big value and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"



Scenario: Bulk with period with wrong format for INTACTA Technology with ADMINISTRATOR
    Given Bulk file with period with wrong format and "INTACTA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"



# FIN VALIDACIONES CUANDO ES INTACTA







#ESCENARIOS DE VALIDACIONES CUANDO LA TECHNOLOGY ES N/A#

Scenario: Bulk with Received Declared Volume  null for NA Technology with ADMINISTRATOR
    Given Bulk file with Received Declared Volume null and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with receivedPositiveTestedVolume  null for NA Technology with ADMINISTRATOR
    Given Bulk file with ReceivedPositiveTestedVolume NULL and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"



Scenario: Bulk with fixedVolume null for NA Technology with ADMINISTRATOR
    Given Bulk file with fixedVolume null and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with fixedPrice null for NA Technology with ADMINISTRATOR
    Given Bulk file with fixedPrice null and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with receivedNegativeTestedVolume null for NA Technology with ADMINISTRATOR
    Given Bulk file with receivedNegativeTestedVolume null and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"



Scenario: Bulk with receivedNegativeTestedVolume with big value for NA Technology with ADMINISTRATOR
    Given Bulk file with receivedNegativeTestedVolume with big value and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with receivedNegativeTestedVolume with negative value for NA Technology with ADMINISTRATOR
    Given Bulk file with receivedNegativeTestedVolume with negative value and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with period with wrong format for NA Technology with ADMINISTRATOR
    Given Bulk file with period with wrong format and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with receivedNegativeTestedVolume with letters for NA Technology with ADMINISTRATOR
    Given Bulk file with receivedNegativeTestedVolume with letters and "NA"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"


#FIN VALIDACIONES DE CUANDO TECHNOLOGY ES NA








Scenario: Bulk with technology empty with ADMINISTRATOR
            Given Bulk file with technology empty
                And RB_Replace ID with new UUID
                And RB_An ADMINISTRATOR
            When RB_I post the Bulk
            Then RB_Verify that Bulk status code was 200
                And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with technology not founded with ADMINISTRATOR
                Given Bulk file with wrong technology
                    And RB_Replace ID with new UUID
                    And RB_An ADMINISTRATOR
                When RB_I post the Bulk
                Then RB_Verify that Bulk status code was 200
                    And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with affiliate with report rol disabled with ADMINISTRATOR
        Given Bulk file with affiliate with report rol disabled
            And RB_Replace ID with new UUID
            And RB_An ADMINISTRATOR
        When RB_I post the Bulk
        Then RB_Verify that Bulk status code was 200
            And RB_Verify the status of the Bulk load is "BULK_ERROR"

Scenario: Bulk with affiliate with report rol not valid with ADMINISTRATOR
        Given Bulk file with affiliate with report rol not valid
            And RB_Replace ID with new UUID
            And RB_An ADMINISTRATOR
        When RB_I post the Bulk
        Then RB_Verify that Bulk status code was 200
            And RB_Verify the status of the Bulk load is "BULK_ERROR"


Scenario: Bulk with affiliate with affiliate not found for a Headoffice with ADMINISTRATOR
        Given Bulk file with affiliate not found for a Headoffice
            And RB_Replace ID with new UUID
            And RB_An ADMINISTRATOR
        When RB_I post the Bulk
        Then RB_Verify that Bulk status code was 200
            And RB_Verify the status of the Bulk load is "BULK_ERROR"





 #  //pod-rol.get.model.found.more.than.one.result

Scenario: Bulk with headoffice with rol model not consolidated with ADMINISTRATOR
    Given Bulk file with headoffice with rol model not consolidated
        And Setup ROL Models Page
        And RMod_An ADMINISTRATOR
    Then Delete ROL Model with id "08707604000184"
    When Create "DETAILED" ROL Model for "08707604000184"
        And RB_Replace ID with new UUID
        And RB_An ADMINISTRATOR
    When RB_I post the Bulk
    Then RB_Verify that Bulk status code was 200
        And RB_Verify the status of the Bulk load is "BULK_ERROR"
        Given A headoffice "08707604000184"
              And A User "STARK_A"
        When Edit to a "CONSOLIDATED" one
            Then Verify that it was edited