/*
    Form with non-boolean conditional
*/

form nonBooleanConditional {
    "Name?" q1: string
    "Amount?" q2: integer
    if(q1) {
        "True?" q3: boolean
    }
}