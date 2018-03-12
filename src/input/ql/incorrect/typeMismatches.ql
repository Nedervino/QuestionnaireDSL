/*
    Form containing multiple type mismatches
*/

form typeMismatches {
    // Disallowed:
    "Test" q26: date = (12-12-2012 + 12-12-2012)


    // Allowed:
    "Test" q20: string = ("hi" + "hi")
    "Test" q22: money = (10,28 + 8,80)
}