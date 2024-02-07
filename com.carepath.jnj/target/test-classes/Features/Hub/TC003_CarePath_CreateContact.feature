Feature: TC003_CarePath_CreateContact.feature

#Background: 

Scenario: Verify that user can create contacts 

  Given Test data loaded from file "src/test/java/TestData/UAT_DataSheet.csv" for the Case "TC003"
  When User navigates to Salesforce login page
  When User navigate to Others page
  When User navigates to Salesforce for contact creation
