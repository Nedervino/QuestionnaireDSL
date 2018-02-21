/*
    Form with duplicate question IDs
*/

form duplicateQuestionIDs {
    "Enter an amount more than 0:" q1: money
    if(q1 > 0) {
        "This should give an error:" q1: boolean // expected: duplicate ID error
    }
}