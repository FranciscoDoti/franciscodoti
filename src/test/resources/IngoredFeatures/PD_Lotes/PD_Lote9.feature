# new feature
# Tags: optional
    
Feature: LOTE 9
Background: Background to get Token
    Given get Token

Scenario: Participant Deliver - Massive Load Test. 36001-45000. LOTE 9
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Participant Deliveries from Affiliate "48" to "53"