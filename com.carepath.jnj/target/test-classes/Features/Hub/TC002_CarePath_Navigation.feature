Feature: TC002_CarePath_Navigation.feature

#Background:
   
Scenario: Verify that user can create contacts 

  Given Test data loaded from file "src/test/java/TestData/UAT_DataSheet.csv" for the Case "TC002"
  When User navigates to Salesforce login page
  When User navigate to Others page
  When User navigates to Salesforce for contact creation
  
  #Then User creates a New contact
  #Then Contact should be created successfully
