/*
    Form with cyclical dependencies
*/

form cyclical2 {
	"Variable based on second answer:" first: integer = (second + 2)
	"Variable based on third answer:" second: integer = (third - 2)
	"Variable based on first answer:" third: integer = (first - 2)

}