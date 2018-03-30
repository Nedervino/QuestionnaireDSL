/*
    Form where we downcast a result from decimal to integer
*/

form test {
  "Should store as 3.75"
    value: decimal = 3.75
  "Should evaluate to 3"
    result: integer = value
}