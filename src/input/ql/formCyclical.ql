/*
    Form with cyclical dependencies
*/

form cyclical {
    "Variable based on second answer:" first: integer = (first + 2)
    "Variable based on first answer:" second: integer = (second - 2)
}