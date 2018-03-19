/*
    Form referring to undefined questions
*/

form undefinedQuestion {
    "Name?" q1: string
    "Money?" q2: integer
    if(q5) {
        "This should fail:" q3: boolean
    }
}