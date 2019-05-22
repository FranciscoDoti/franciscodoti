# new feature
# Tags: optional
    
Feature: LOTE 2
Background: Background to get Token
    Given get Token

Scenario: Participant Deliver - Massive Load Test. 9001-18000. LOTE 2
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Participant Deliveries from Affiliate "6" to "11"