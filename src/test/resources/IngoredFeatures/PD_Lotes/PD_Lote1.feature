# new feature
# Tags: optional
    
Feature: LOTE 1
Background: Background to get Token
    Given get Token

Scenario: Participant Deliver - Massive Load Test. 1-9000 . LOTE 1
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Participant Deliveries from Affiliate "0" to "5"