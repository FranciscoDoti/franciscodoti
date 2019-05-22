# new feature
# Tags: optional
@PARTICIPANT_DELIVER

Feature: Post a ParticipantDeliver
    Post tranfers, cancel transfers, edit transfers.

    Background: This is a background for set the enviroment
        Given Setup PD Page



    Scenario: Edit a Participant Deliver with status CANCELLED with a PARTICIPANT
        Given PD_An ADMINISTRATOR
            And A PD with status Cancelled
            And PD_A PARTICIPANT
        When I edit the ParticipantDeliver with Volume "15"
        Then Verify that the ParticipantDeliver wasnt edited

    Scenario: Cancel a Participant Deliver with a PARTICIPANT
       Given PD_An ADMINISTRATOR
            And A PD with status Registered
            And PD_A PARTICIPANT
       When I cancel the ParticipantDeliver
       Then Verify that the ParticipantDeliver was cancelled

    Scenario: Post a ParticipantDeliver from an affiliate to a POD with a PARTICIPANT
        Given As a POD user "77752293000198" I want to receive a volume ParticipantDeliver from a "77752293004185"
            And PD_A PARTICIPANT
        When I post the ParticipantDeliver with Volume "10"
        Then Verify that the ParticipantDeliver was posted

    Scenario: Post a ParticipantDeliver with negative volume with a PARTICIPANT
        Given As a POD user "77752293000198" I want to receive a volume ParticipantDeliver from a "77752293004185"
            And PD_A PARTICIPANT
        When I post the ParticipantDeliver with Volume "-10"
        Then Verify that the ParticipantDeliver wasnt posted


    Scenario: Post a ParticipantDeliver from a Headoffice to a POD with a PARTICIPANT
        Given As a POD user "77752293000198" I want to receive a volume ParticipantDeliver from a "0001830610"
            And PD_A PARTICIPANT
        When I post the ParticipantDeliver with Volume "10"
        Then Verify that the ParticipantDeliver was posted

    Scenario: Edit a Participant Deliver with with a PARTICIPANT
       Given PD_An ADMINISTRATOR
            And A PD with status Registered
            And PD_A PARTICIPANT
         When I edit the ParticipantDeliver with Volume "20"
         Then Verify that the ParticipantDeliver was edited

    Scenario: Edit a Participant Deliver with negative volume with with a PARTICIPANT
       Given PD_An ADMINISTRATOR
           And A PD with status Registered
           And PD_A PARTICIPANT
       When I edit the ParticipantDeliver with Volume "-15"
       Then Verify that the ParticipantDeliver wasnt edited



    Scenario: Post a ParticipantDeliver from a POD to itself with a PARTICIPANT
        Given As a POD user "77752293004185" I want to receive a volume ParticipantDeliver from a "77752293004185"
            And PD_A PARTICIPANT
        When I post the ParticipantDeliver with Volume "10"
        Then Verify that the ParticipantDeliver wasnt posted







    Scenario: Edit a Participant Deliver with status CANCELLED with an ADMINISTRATOR
         Given PD_An ADMINISTRATOR
            And A PD with status Cancelled
         When I edit the ParticipantDeliver with Volume "15"
         Then Verify that the ParticipantDeliver wasnt edited

    Scenario: Cancel a Participant Deliver with an ADMINISTRATOR
       Given PD_An ADMINISTRATOR
            And A PD with status Registered
       When I cancel the ParticipantDeliver
       Then Verify that the ParticipantDeliver was cancelled

    Scenario: Post a ParticipantDeliver from an affiliate to a POD with an ADMINISTRATOR
        Given As a POD user "77752293000198" I want to receive a volume ParticipantDeliver from a "77752293004185"
            And PD_An ADMINISTRATOR
        When I post the ParticipantDeliver with Volume "10"
        Then Verify that the ParticipantDeliver was posted

    Scenario: Post a ParticipantDeliver with negative volume with an ADMINISTRATOR
        Given As a POD user "77752293000198" I want to receive a volume ParticipantDeliver from a "77752293004185"
            And PD_An ADMINISTRATOR
        When I post the ParticipantDeliver with Volume "-10"
        Then Verify that the ParticipantDeliver wasnt posted


    Scenario: Post a ParticipantDeliver from a Headoffice to a POD with an ADMINISTRATOR
        Given As a POD user "77752293000198" I want to receive a volume ParticipantDeliver from a "0001830610"
            And PD_An ADMINISTRATOR
        When I post the ParticipantDeliver with Volume "10"
        Then Verify that the ParticipantDeliver was posted

    Scenario: Edit a Participant Deliver with an ADMINISTRATOR
       Given PD_An ADMINISTRATOR
            And A PD with status Registered
         When I edit the ParticipantDeliver with Volume "20"
         Then Verify that the ParticipantDeliver was edited

    Scenario: Edit a Participant Deliver with negative volume with an ADMINISTRATOR
       Given PD_An ADMINISTRATOR
            And A PD with status Registered
         When I edit the ParticipantDeliver with Volume "-15"
         Then Verify that the ParticipantDeliver wasnt edited



    Scenario: Post a ParticipantDeliver from a POD to itself with an ADMINISTRATOR
        Given As a POD user "77752293004185" I want to receive a volume ParticipantDeliver from a "77752293004185"
            And PD_An ADMINISTRATOR
        When I post the ParticipantDeliver with Volume "10"
        Then Verify that the ParticipantDeliver wasnt posted










