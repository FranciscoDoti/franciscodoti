# new feature
# Tags: optional
    
Feature: LOTE 7
Background: Background to get Token
    Given get Token

Scenario: Participant Deliver - Massive Load Test. 45001-54000. LOTE 7
    Given a Headoffice with id "77863223000107"
        And 100 affiliates from headoffice "77863223000107"
    When Bulk Participant Deliveries from Affiliate "36" to "41"