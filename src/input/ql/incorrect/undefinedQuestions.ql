/*
    Form referring to undefined questions
*/

form undefinedQuestions {
    "Name?" q1: string
    "Money?" q2: integer
    if(q5) {
        "This should fail:" q3: boolean
    }
}