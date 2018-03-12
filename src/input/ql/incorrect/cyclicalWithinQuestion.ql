/*
    Form with cyclical dependencies
*/

form cyclical {
	"Variable based on itself:" first: integer = (first + 2)
}