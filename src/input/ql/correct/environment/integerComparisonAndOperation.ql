/*
    Form where we compare and add integers
*/

form example {
  "Should store as 20"
    value: integer = 20
  "Should store as negative 21"
    value2: integer = -21
  "Should store as negative 121"
    value3: integer = -121
  "Should store as 100"
    value4: integer = 100
  "Should evaluate to true"
    result: boolean = value > value2
  "Should evaluate to true"
    result2: boolean = (value3 + value4) >= value2
  "Should evaluate to true"
    result3: boolean = (value4 / value) == 5
}