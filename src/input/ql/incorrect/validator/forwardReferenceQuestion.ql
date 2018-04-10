/*
    Form with forward reference
*/

form forward {

	"Variable based on second variable:" first: integer = (second + 2)
	"Second variable:" second: integer = (third + 2)
	"Third variable:" third: integer = (fourth + 2)
	"Fourth variable:" fourth: integer

}