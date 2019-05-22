# new feature
# Tags: optional
    
Feature: LOTE 3
Background: Background to get Token
    Given get Token

Scenario: Participant Deliver - Massive Load Test. 18001-27000. LOTE 3
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Participant Deliveries from Affiliate "12" to "17"