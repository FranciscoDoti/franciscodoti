# new feature
# Tags: optional
    
Feature: LOTE 6
Background: Background to get Token
    Given get Token

Scenario: Participant Deliver - Massive Load Test. 18001-27000. LOTE 4
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Participant Deliveries from Affiliate "18" to "23"