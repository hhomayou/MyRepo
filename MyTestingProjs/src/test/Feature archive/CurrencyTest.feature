Feature: Testing currency rates in the RBC page

Scenario: Initial test
  Given Initial test

Scenario: Test all unit rates against US dollars for $100
  Then Test all rates

Scenario Outline: Test two sample currency conversions againtst the amount calculated in the web page  
  Given Page is loaded
  When User enters <currencyHave> <amountHave> <currencyWant> <amountWant>
  Examples:
    | currencyHave | amountHave | currencyWant | amountWant |
    |  "CAD"       |  200       |  "USD"       | 155.68     |
    |  "EUR"       |  300       |  "GBP"       | 258.77     |
    
Scenario: End test
  Then End test
 