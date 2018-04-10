/*
    Form where we multiply money variables
*/

form test {
  "Should store as 3.25"
    value: money = 3,25
  "Should store as 4"
    value2: money = 4,00
  "Should evaluate to 0.8125"
    result: money = value / value2
}