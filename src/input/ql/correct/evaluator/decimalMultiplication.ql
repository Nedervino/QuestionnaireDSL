/*
    Form where we multiply decimal variables
*/

form test {
  "Should store as 3.25"
    value: decimal = 3.25
  "Should store as 4"
    value2: decimal = 4.0
  "Should evaluate to 13"
    result: decimal = value * value2
}