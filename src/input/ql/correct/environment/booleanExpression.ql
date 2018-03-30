/*
    Form where we operate on booleans
*/

form example {
  "Should store as false"
    value: boolean = false
  "Should store as false"
    value2: boolean = false
  "Should store as false"
    value3: boolean = false
  "Should evaluate to true"
    result: boolean = (value || !value2) && !value3
}