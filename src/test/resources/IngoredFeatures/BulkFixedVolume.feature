# new feature
# Tags: optional
    
Feature: Bulk Fixed Volume tests

    Background: to set bulk feature env
        Given BFV_Set new UUID
            And A Bulk with fixed volumes


 Scenario: Fixed Volume Bulk to an affiliate with an non-existent Document Number with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Document Number "000000212123123"
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume


 Scenario: Fixed Volume Bulk to an affiliate with an non-existent Document Type with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Document Type "WRONG_TYPE"
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

Scenario: Fixed Volume Bulk to an affiliate with a Document Number with letters with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Document Number with letters
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with a Document Number with null value with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Document Number null
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with an non-existent Document Number with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Document Number "000000212123123"
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with an non-existent Document Type with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Document Type "WRONG_TYPE"
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with Technology with number value with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Technology with number "232"
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with Technology different to INTACTA and XTend with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Technology different to INTACTA and XTend
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with volume empty with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Volume empty
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with volume null with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Volume null
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with volume with simbols with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Volume with simbols
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with volume with letters with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Volume with letters
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with negative volume with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
        And Negative Volume
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status Bulk_Error

Scenario: Fixed Volume Bulk to an affiliate with no errors with usertype ADMINISTRATOR
    Given An user with usertype "ADMINISTRATOR"
        And AffiliateDocument "08707604000184"
    When I register the Fixed Volume Bulk
    Then Verify that the Bulk has status registered


  ####################################################################################################################################
  ####################################################################################################################################

  Scenario: Fixed Volume Bulk to an affiliate with a Document Number with letters with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Document Number with letters
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with a Document Number with null value with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Document Number null
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume




  Scenario: Fixed Volume Bulk to an affiliate with Technology with number value with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Technology with number "232"
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with Technology different to INTACTA and XTend with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Technology different to INTACTA and XTend
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with volume empty with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Volume empty
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with volume null with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Volume null
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with volume with simbols with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Volume with simbols
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with volume with letters with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Volume with letters
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with negative volume with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
          And Negative Volume
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume

  Scenario: Fixed Volume Bulk to an affiliate with no errors with usertype PARTICIPANT
      Given An user with usertype "PARTICIPANT"
          And AffiliateDocument "08707604000184"
      When I register the Fixed Volume Bulk
      Then Verify that the user is not able to bulk a Fixed Volume
