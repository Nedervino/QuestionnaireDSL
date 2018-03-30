/*
    Form where we compare strings
*/

form example {
  "Should store as foo"
    value: string = "foo"
  "Should store as bar"
    value2: string = "bar"
  "Should evaluate to false"
    result: boolean = value == value2
}