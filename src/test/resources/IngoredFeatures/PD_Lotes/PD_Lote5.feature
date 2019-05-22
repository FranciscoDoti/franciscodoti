# new feature
# Tags: optional
    
Feature: LOTE 18
Background: Background to get Token
    Given get Token

Scenario: Participant Deliver - Massive Load Test. 27001-36000. LOTE 5
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Participant Deliveries from Affiliate "24" to "29"