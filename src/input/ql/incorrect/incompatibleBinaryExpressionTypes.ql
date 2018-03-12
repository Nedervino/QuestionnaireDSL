/*
    Form containing multiple incompatible types
*/

form incompatibleTypes {
    //DISALLOWED:

    //Integer combinations
    "Test" q1: string = (1242 + "hi")
    "Test" q2: string = (1242 + 12-12-2012)
    "Test" q3: string = (21 + true)
    "Test" q4: string = (234 + true)
    "Test" q5: string = (2 + 2,10)

    //Decimal combinations
    "Test" q6: string = (12.42 + "hi")
    "Test" q7: string = (12.42 + 12-12-2012)
    "Test" q8: string = (2.1 + true)
    "Test" q9: string = (2.1 + 2,10)

    //String combinations
    "Test" q10: string = ("hi" + true)
    "Test" q11: string = ("hi" + 12-12-2012)
    "Test" q12: string = ("hi" + 12,20)

    //Boolean combinations
    "Test" q13: string = (false + 12,20)
    "Test" q14: string = (false + 12-12-2012)


    //Date combinations
    "Test" q15: string = (12-12-2012 + 10,20)

    //Money combinations

    //ALLOWED:

    //different numeric operand combinations are allowed
    "Test" q17: decimal = (2.1 + 12321)
    "Test" q18: decimal = (12321 + 2.1)

    //Integer, decimal, and boolean same-types
    "Test" q18: integer = (2 + 2)
    "Test" q19: decimal = (2.1 + 2.1)
    //"Test" q20: string = ("hi" + "hi")         //string concatenation is checked by verifytype
    "Test" q21: boolean = (false || false)
    // "Test" q22: money = (10,28 + 8,80)        //money operations are checked by verifytype

}