Feature: Final Assignment
  @PrintWebTableValues
  Scenario Outline: Print All the values from WebTable
    Given Initialize The Driver
    When Navigate the WebPage to <URL>
    Then Print The Values from WebTable
    Examples:
      |URL                                                |
      | https://www.w3schools.com/html/html_tables.asp    |


  @StringSort
  Scenario Outline: Arranging the Given string in sorting order and removing duplicates
    Given Take <input> As a String
    When arrange the all characters of the string in sorting order
    Then Print the Sorted String in Console
    Examples:
      | input        |
      | techmahindra |


  @Working @DayOr @Not
  Scenario: Checking that if the Day is Working Day or not
    Given Get The Current Date
    When Check the day that if it is Working Day or Not
    Then Return The Day after checking


  @SelectCurrentorFutureDate
  Scenario Outline: Selecting the current or future date
    Given Accept <date> in YYYY/MM/DD format
    When check if given date is future date or current date
    And Navigate to <url>
    Then Switching to Frame
    And Selecting the given month
    And selecting date
    Examples:
      |date           |url                             |
      |2021-02-28     |https://jqueryui.com/datepicker/|

  @RandomDate
  Scenario Outline: Generating Random Date within the Specified Range
    Given Take <range> as input
    When Generate Random Date with in given Range
    Then Return The Random
    Examples:
      |range    |
      |2        |
  @RandomDatesWithOutWorkingDays
  Scenario Outline: Generate Random Date where it does not print current day and it should print weekday
    Given Accept <range> as input
    When Generate  Date with in given Range
    Then Return The RandomDate
    Examples:
      |range    |
      |2        |


  @PrintFirstGivenNNumber
  Scenario Outline: Printing the number which is multiple of 5 as Fizz and multiple of 7 as Buzz and multiple of both 5 and 7 as FizzBuzz
    Given Give <n> as input
    When printing Fizz or Buzz accordingly that if the number is multiple of 5 or 7
    Then print the values to the console
    Examples:
      |n|
      |50|