/*
    Form where we compare date
*/

form example {
  "Should store as 31121999"
    value: date = 31-12-1999
  "Should store as 01012000"
    value2: date = 01-01-2000
  "Should evaluate to true"
    result: boolean = value <= value2
}