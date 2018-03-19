/*
    Form with cyclical dependencies
*/

form cyclical2 {
	"Variable based on second answer:" first: integer = (second + 2)
	"Variable based on first answer:" second: integer = (first - 2)
}