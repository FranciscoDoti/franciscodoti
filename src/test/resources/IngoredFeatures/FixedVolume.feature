# new feature
# Tags: optional

Feature: Fixed Volume tests

    Background: Set Fixed Volume Page
        Given set Fixed Volume Page

### FALTA AGREGAR LOS FILTROS POR FECHAS


####################################################################################################################################
  #####################################################################################################################################################################

    Scenario: Add a new Fixed Volume with Xtend with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given A headoffice with doc "77863223000107"
            And A Grower "77863223000107"
            And Xtend volume "10"
            And Intacta volume "0"
        When Create Fixed Volume
        Then Verify that fixed volume wasnt created



Scenario: Delete a Fixed Volume with an Administrator
        Given FV_An ADMINISTRATOR
                And A headoffice with doc "77863223000107"
                And A Grower "77863223000107"
                And Xtend volume "10"
                And Intacta volume "0"
            When Create Fixed Volume
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
        When Search Fixed Volume
            And Delete the last Fixed Volume
        Then Verify that fixed volume was deleted

    Scenario: Delete a Fixed Volume with a PARTICIPANT
        Given FV_An ADMINISTRATOR
                And A Grower "77863223000107"
                And Xtend volume "10"
                And Intacta volume "0"
                And A headoffice with doc "77863223000107"
            When Create Fixed Volume
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
            And FV_An ADMINISTRATOR
        When Search Fixed Volume
        Given FV_A PARTICIPANT
        When Delete the last Fixed Volume
        Then Verify that fixed volume wasnt deleted

    Scenario: Edit a Fixed Volume with a PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And FV_An ADMINISTRATOR
        When Search Fixed Volume
        Given FV_A PARTICIPANT
            And Edit a Fixed Volume setting "15"
        Then Verify that the fixedVolume wasnt edited


    Scenario: Edit a Fixed Volume with negative volume with a PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And FV_An ADMINISTRATOR
        When Search Fixed Volume
        Given FV_A PARTICIPANT
            And Edit a Fixed Volume setting "-15"
        Then Verify that the fixedVolume wasnt edited

    Scenario: Edit a Fixed Volume with volume 0 with a PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And FV_An ADMINISTRATOR
        When Search Fixed Volume
        Given FV_A PARTICIPANT
            And Edit a Fixed Volume setting "0"
        Then Verify that the fixedVolume wasnt edited

    Scenario: Search a Fixed Volume by Headoffice, Technology, Identifier and Grower with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
            And Filter Identifier
            And Filter Grower "77863223000107"
        When Search Fixed Volume
        Then FV_Verify that the user dont have permissions to search



    Scenario: Search a Fixed Volume by Headoffice with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
        When Search Fixed Volume
        Then FV_Verify that the user dont have permissions to search



    Scenario: Search a Fixed Volume by Headoffice, Technology and Identifier with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
            And Filter Identifier
        When Search Fixed Volume
        Then FV_Verify that the user dont have permissions to search

    Scenario: Search a Fixed Volume by Headoffice and Technology with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
        When Search Fixed Volume
        Then FV_Verify that the user dont have permissions to search

    Scenario: Search a Fixed Volume by Headoffice and Technology and Grower with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
            And Filter Grower "77863223000107"
        When Search Fixed Volume
        Then FV_Verify that the user dont have permissions to search

    Scenario: Search a Fixed Volume by Headoffice and Identifier and Grower with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And Filter Grower "77863223000107"
        When Search Fixed Volume
        Then FV_Verify that the user dont have permissions to search






    Scenario: Add a new Fixed Volume with Xtend and Intacta with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given A headoffice with doc "77863223000107"
            And A Grower "77863223000107"
            And Xtend volume "10"
            And Intacta volume "20"
        When Create Fixed Volume
        Then Verify that fixed volume wasnt created

    Scenario: Add a new Fixed Volume with Intacta with a PARTICIPANT
        Given FV_A PARTICIPANT
        Given A headoffice with doc "77863223000107"
            And A Grower "77863223000107"
            And Xtend volume "0"
            And Intacta volume "20"
        When Create Fixed Volume
        Then Verify that fixed volume wasnt created



    Scenario: Verify the Fixed volume pagination with limit 5 with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
        When FV_Search Fixed Volume with limit "5"
        Then Verify that the Fixed Volume search doesnt bring more than "5" results

    Scenario: Verify the Fixed volume pagination with limit 10 with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
        When FV_Search Fixed Volume with limit "10"
        Then Verify that the Fixed Volume search doesnt bring more than "10" results

    Scenario: Verify the Fixed volume pagination with limit 25 with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
        When FV_Search Fixed Volume with limit "25"
        Then Verify that the Fixed Volume search doesnt bring more than "25" results

    Scenario: Verify the Fixed volume pagination with limit 50 with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
        When FV_Search Fixed Volume with limit "50"
        Then Verify that the Fixed Volume search doesnt bring more than "50" results



    Scenario: Edit a Fixed Volume with an Administrator
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And FV_An ADMINISTRATOR
        When Search Fixed Volume
            And Edit a Fixed Volume setting "15"
        Then Verify that the fixedVolume was edited


    Scenario: Edit a Fixed Volume with negative volume with an Administrator
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And FV_An ADMINISTRATOR
        When Search Fixed Volume
            And Edit a Fixed Volume setting "-15"
        Then Verify that the fixedVolume wasnt edited

    Scenario: Edit a Fixed Volume with volume 0 with an Administrator
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And FV_An ADMINISTRATOR
        When Search Fixed Volume
            And Edit a Fixed Volume setting "0"
        Then Verify that the fixedVolume wasnt edited

    Scenario: Search a Fixed Volume by Headoffice, Technology, Identifier and Grower with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
            And Filter Identifier
            And Filter Grower "77863223000107"
        When Search Fixed Volume
        Then Verify that the search brings results



    Scenario: Search a Fixed Volume by Headoffice with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
        When Search Fixed Volume
        Then Verify that the search brings results



    Scenario: Search a Fixed Volume by Headoffice, Technology and Identifier with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
            And Filter Identifier
        When Search Fixed Volume
        Then Verify that the search brings results

    # ESTE CASO VER DE METERLO A LA FUERZA
    Scenario: Search a Fixed Volume by Headoffice and Technology with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
        When Search Fixed Volume
        Then Verify that the search brings results

    # ESTE CASO VER DE METERLO A LA FUERZA
    Scenario: Search a Fixed Volume by Headoffice and Technology and Grower with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
            And Filter Technology "INTACTA"
            And Filter Grower "77863223000107"
        When Search Fixed Volume
        Then Verify that the search brings results

    Scenario: Search a Fixed Volume by Headoffice and Identifier and Grower with an Administrator
        Given FV_An ADMINISTRATOR
        Given Filter Headoffice by doc "77863223000107"
            And Filter Identifier
            And Filter Grower "77863223000107"
        When Search Fixed Volume
        Then Verify that the search brings results




    Scenario: Add a new Fixed Volume with Xtend with an Administrator
        Given FV_An ADMINISTRATOR
        Given A headoffice with doc "77863223000107"
            And A Grower "77863223000107"
            And Xtend volume "10"
            And Intacta volume "0"
        When Create Fixed Volume
        Then Verify that fixed volume was created


    Scenario: Add a new Fixed Volume with Xtend and Intacta with an Administrator
        Given FV_An ADMINISTRATOR
        Given A headoffice with doc "77863223000107"
            And A Grower "77863223000107"
            And Xtend volume "10"
            And Intacta volume "20"
        When Create Fixed Volume
        Then Verify that fixed volume was created

    Scenario: Add a new Fixed Volume with Intacta with an Administrator
        Given FV_An ADMINISTRATOR
        Given A headoffice with doc "77863223000107"
            And A Grower "77863223000107"
            And Xtend volume "0"
            And Intacta volume "20"
        When Create Fixed Volume
        Then Verify that fixed volume was created
