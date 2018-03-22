/*
    Form where we divide money variables
*/

form test {
  "Should store as 10,00"
    value: money = 10,00
  "Should store as 3"
    value2: integer = 3
  "Should evaluate to 3,33"
    result: money = value / value2
}